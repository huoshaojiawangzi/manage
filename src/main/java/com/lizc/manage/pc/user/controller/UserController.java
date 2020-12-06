package com.lizc.manage.pc.user.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lizc.manage.common.controller.BaseController;
import com.lizc.manage.common.utils.ColumnDefine;
import com.lizc.manage.common.utils.CommonXlsxView;
import com.lizc.manage.common.utils.ExcelUtil;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.service.OfficeService;
import com.lizc.manage.pc.user.service.UserService;
import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.sevice.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/home/user")
public class UserController extends BaseController<User, UserService>
{
    private final RoleService roleService;

    @Autowired
    private OfficeService officeService;

    private List<ColumnDefine> columns = new ArrayList<>();

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        columns.add(new ColumnDefine("序号"));
        columns.add(new ColumnDefine("标志", "id"));
        columns.add(new ColumnDefine("上级机构", "staff.department.parent.name"));
        columns.add(new ColumnDefine("机构", "staff.department.name"));
        columns.add(new ColumnDefine("姓名", "staff.name"));
        columns.add(new ColumnDefine("考勤日期", "date"));
        columns.add(new ColumnDefine("考勤类型", "type").transferDictTypeColumn("考勤类型"));
    }
    // 导出
    @PostMapping("/export")
    public ModelAndView exportExcel(@RequestParam("data") String str) {
        JSONObject query = JSON.parseObject(str);
        Map<String, Object> md = new HashMap<>();
        md.put("title", "考勤记录列表");
        md.put("items", service.findAll());
        if(StringUtils.isNotBlank(query.getString("dateHead")) && StringUtils.isNotBlank(query.getString("dateTail"))){
            md.put("nextTitle","统计日期：" + query.getString("dateHead") + "至" + query.getString("dateTail"));
        }
        return new ModelAndView(new CommonXlsxView(this.columns), md);
    }
    @PostMapping("/import")
    public void importExcel(MultipartFile file) throws IOException {
        if(file == null){
            throw new IllegalArgumentException("导入文件为空!");
        }
        String fileName = file.getOriginalFilename();
        if(fileName == null || !fileName.contains(".")){
            throw new IllegalArgumentException("导入文件类型不是xls或者xlsx!");
        }
        List<Map<String, String>> excelColumnList = ExcelUtil.getExcelMap(file.getInputStream(), fileName.substring(fileName.lastIndexOf(".")).toLowerCase());
        for (Map<String, String> excelColumn : excelColumnList) {
            User user = new User();
            CommonUser commonUser = new CommonUser();
            String rowValue = excelColumn.get("姓名");
            commonUser.setName(rowValue);
            rowValue = excelColumn.get("机构编号");
            officeService.findByCode(rowValue);
            rowValue = excelColumn.get("手机");
            user.setPhone(rowValue);
            rowValue = excelColumn.get("登录名");
            commonUser.setUserName(rowValue);
            rowValue = excelColumn.get("角色");
            String[] rolesStrings = rowValue.split(",");
            List<Role> roleList = new ArrayList<>();
            for(String roleString : rolesStrings){
                Role role = roleService.findByName(roleString);
                roleList.add(role);
            }
            commonUser.setRoles(roleList);
            user.setCommonUser(commonUser);
            save(user);
        }
    }

}
