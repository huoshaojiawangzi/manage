package com.lizc.sports.sys.sevice;

import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.repository.CommonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**系统用户业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 14:53
 **/
@Service
public class CommonUserService extends BaseService<CommonUser,String,CommonUserRepository>
{

    private final CommonUserRepository commonUserRepository;

    @Autowired
    public CommonUserService(CommonUserRepository commonUserRepository) {
        this.commonUserRepository = commonUserRepository;
    }

    public CommonUser findByUserNameAndPassword(String userName, String password)
    {
        CommonUser commonUser = findByUserName(userName);
        if(commonUser == null||!password.equals(commonUser.getPassword()))
        {
            return null;
        }
        else
        {
            return commonUser;
        }
    }

    public CommonUser findByUserName(String userName)
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setUserName(userName);
        Example<CommonUser> example = Example.of(commonUser);
        return commonUserRepository.findOne(example).orElse(null);
    }

    /**
     * 通过用户名进行逻辑删除
     * @param userName
     */
    public void deleteByUserName(String userName)
    {
        delete(findByUserName(userName));
    }
}
