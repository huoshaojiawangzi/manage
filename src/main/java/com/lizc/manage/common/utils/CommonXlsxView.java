package com.lizc.manage.common.utils;

import com.lizc.manage.sys.utils.DictUtils;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommonXlsxView extends AbstractXlsxView {

    private Map<String, XSSFCellStyle> styles = new HashMap<>();
    private List<ColumnDefine> columns = new ArrayList<>();
    private List<String> diffColumns = new ArrayList<>();

    public CommonXlsxView(List<ColumnDefine> columns) {
        this.columns = columns;
    }

    public void createDiffColumns(List<String> diffColumns) {

    }

    /**
     * <p>
     * Object转成String类型，便于填充单元格
     * </P>
     */
    public static String objectToString(Object object) {
        String str = "";
        if (object == null) {
        } else if (object instanceof Date) {
            DateFormat from_type = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) object;
            str = from_type.format(date);
        } else if (object instanceof String) {
            str = (String) object;
        } else if (object instanceof Integer) {
            str = ((Integer) object).intValue() + "";
        } else if (object instanceof Double) {
            str = ((Double) object).doubleValue() + "";
        } else if (object instanceof Long) {
            str = Long.toString(((Long) object).longValue());
        } else if (object instanceof Float) {
            str = Float.toString(((Float) object).floatValue());
        } else if (object instanceof Boolean) {
            str = Boolean.toString((Boolean) object);
        } else if (object instanceof Short) {
            str = Short.toString((Short) object);
        } else if(object instanceof LocalDateTime) {
            str = object.toString().replaceAll("T"," ");
        }else {
            str = object.toString();
        }
        return str;
    }

    public XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {
        if (styles.get("title") != null) {
            return styles.get("title");
        }
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("黑体");
        font.setColor(IndexedColors.BLUE_GREY.getIndex());
        font.setFontHeightInPoints((short) 16);

        XSSFCellStyle format = workbook.createCellStyle();
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.CENTER);
        format.setFont(font);
        styles.put("title", format);
        return format;
    }
    public XSSFCellStyle getNextTitleStyle(XSSFWorkbook workbook) {
        if (styles.get("nextTitle") != null) {
            return styles.get("nextTitle");
        }
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontName("微软雅黑");
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeightInPoints((short) 11);
        XSSFCellStyle format = workbook.createCellStyle();
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.CENTER);
        format.setFont(font);
        format.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("nextTitle", format);
        return format;
    }

    public XSSFCellStyle getFirstRowStyle(XSSFWorkbook workbook) {
        if (styles.get("firstrow") != null) {
            return styles.get("firstrow");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.CENTER);
        format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        format.setFillForegroundColor(HSSFColorPredefined.DARK_BLUE.getIndex());
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("firstrow", format);
        return format;
    }

    public XSSFCellStyle getFirstColumnStyle(XSSFWorkbook workbook) {
        if (styles.get("firstcolumn") != null) {
            return styles.get("firstcolumn");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setBold(true);
        font.setColor(IndexedColors.YELLOW.getIndex());
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.CENTER);
        format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        format.setFillForegroundColor(HSSFColorPredefined.BLUE_GREY.getIndex());
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("firstcolumn", format);
        return format;
    }

    public XSSFCellStyle getContextStyle0(XSSFWorkbook workbook) {
        if (styles.get("content0") != null) {
            return styles.get("content0");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
//		format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		format.setFillForegroundColor(HSSFColorPredefined.LIGHT_BLUE.getIndex());
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.LEFT);
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("content0", format);
        return format;
    }

    public XSSFCellStyle getContextStyle1(XSSFWorkbook workbook) {
        if (styles.get("content1") != null) {
            return styles.get("content1");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        format.setFillForegroundColor(HSSFColorPredefined.LIGHT_BLUE.getIndex());
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.LEFT);
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("content1", format);
        return format;
    }

    public XSSFCellStyle getContextStyle2(XSSFWorkbook workbook) {
        if (styles.get("content2") != null) {
            return styles.get("content2");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        format.setFillForegroundColor(HSSFColorPredefined.LIGHT_GREEN.getIndex());
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.LEFT);
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("content2", format);
        return format;
    }

    public XSSFCellStyle getNumberContextStyle(XSSFWorkbook workbook) {
        if (styles.get("numbercontent") != null) {
            return styles.get("numbercontent");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.RIGHT);
        format.setFont(font);
        this.setBorderThin(format);
        styles.put("numbercontent", format);
        return format;
    }

    public XSSFCellStyle getMoneyContextStyle(XSSFWorkbook workbook) {
        if (styles.get("moneycontent") != null) {
            return styles.get("moneycontent");
        }
        XSSFCellStyle format = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        format.setVerticalAlignment(VerticalAlignment.CENTER);
        format.setAlignment(HorizontalAlignment.RIGHT);
        format.setFont(font);
        XSSFDataFormat df = workbook.createDataFormat();
        format.setDataFormat(df.getFormat("#,##0.00"));
        this.setBorderThin(format);
        styles.put("moneycontent", format);
        return format;
    }

    public void setBorderThin(XSSFCellStyle format) {
        format.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
        format.setBorderTop(BorderStyle.THIN);

        format.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
        format.setBorderLeft(BorderStyle.THIN);

        format.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
        format.setBorderBottom(BorderStyle.THIN);

        format.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
        format.setBorderRight(BorderStyle.THIN);
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        OutputStream os = null;
        try {
            String title = (String) model.get("title");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(title + "-" + LocalDate.now() + ".xlsx", "UTF-8"));
            os = response.getOutputStream();
            XSSFWorkbook wb = (XSSFWorkbook) workbook;
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet(title);
            String nextTitle = null;
            if(model.get("title") != null) {
                nextTitle= (String) model.get("nextTitle");
            }
            this.addTitle(sheet, title + " " + LocalDate.now(), nextTitle, wb);
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) model.get("items");
            boolean hasNextTitle = model.get("nextTitle") != null;
            this.addContextByList(sheet, list, getFirstColumnStyle(wb),
                    new XSSFCellStyle[]{getContextStyle0(wb), getContextStyle1(wb), getContextStyle2(wb)},
                    getNumberContextStyle(wb), getMoneyContextStyle(wb), true, diffColumns,hasNextTitle);

            workbook.write(os);
        } catch (Throwable e) {
            logger.error("信息导出出错：" + e.getMessage(), e);
        } finally {
            try {
                os.flush();
                os.close();
            } catch (Throwable e) {
                logger.error("信息导出Excel出错：" + e.getMessage(), e);
            }
        }
    }

    /**
     * 添加列表信息 sheet excelSheet list 导出主要信息 fieldName 属性名称>数组对于表头 扩展属性格式extra.key
     * contextStyle 内容样式 isHaveSerial 是否添加序号
     */
    public <T> void addContextByList(XSSFSheet sheet, List<T> list, XSSFCellStyle indexStyle,
                                     XSSFCellStyle[] contextStyles, XSSFCellStyle numContextStyle, XSSFCellStyle moneyContextStyle,
                                     boolean isHaveSerial, List<String> diffColumns, boolean hasNextTitle) {

        try {
            int cur = 0;
            XSSFCellStyle contextStyle = contextStyles[0];
            String last = null;

            XSSFRow row = null;
            XSSFCell cell = null;
            if (list != null) {
                List<T> tList = (List<T>) list;
                T t = null;
                String value = "";
                int rowCount = 2;
                if(hasNextTitle) {
                    rowCount = 3;
                }
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + rowCount);
                    if (isHaveSerial) {
                        cell = row.createCell(0);
                        cell.setCellValue("" + (i + 1));
                        cell.setCellStyle(indexStyle);
                    }
                    if (diffColumns.size() > 0) {
                        for (String key : diffColumns) {
                            value += objectToString(getFieldValueByName(key, tList.get(i)));
                        }
                        if (!value.equals(last)) {
                            cur++;
                            contextStyle = contextStyles[cur % contextStyles.length];
                            last = value;
                        }
                    }
                    for (int j = 0; j < this.columns.size() - 1; j++) {
                        int curColumn = isHaveSerial ? j + 1 : j;
                        ColumnDefine def = this.columns.get(curColumn);
                        t = tList.get(i);
                        Object o = getFieldValueByName(def.getFieldName(), t);
                        if(def.getDateFormat() != null) {
                            if(getFieldValueByName(def.getFieldName(), t) instanceof LocalDateTime) {
                                LocalDateTime dateTime = (LocalDateTime) getFieldValueByName(def.getFieldName(), t);
                                value = dateTime.format(DateTimeFormatter.ofPattern(def.getDateFormat()));
                            } else{
                                LocalDate date = (LocalDate) getFieldValueByName(def.getFieldName(), t);
                                value = date.format(DateTimeFormatter.ofPattern(def.getDateFormat()));
                            }
                        }else {
                            value = objectToString(getFieldValueByName(def.getFieldName(), t));
                        }
                        if (def.getDictType() != null) {
                            value = DictUtils.getDictLabel(def.getDictType(), value);
                        }
                        if (isHaveSerial) {
                            cell = row.createCell(j + 1);
                        } else {
                            cell = row.createCell(j);
                        }
                        if (def.getType() == 1) {
                            try {
                                cell.setCellValue(Integer.parseInt(value));
                            } catch (Exception e) {
                            }
                            cell.setCellStyle(numContextStyle);
                        } else if (def.getType() == 2) {
                            try {
                                cell.setCellValue(Double.parseDouble(value));
                            } catch (Exception e) {
                            }
                            cell.setCellStyle(moneyContextStyle);
                        } else {
                            //设置属性value
                            cell.setCellValue(value);
                            cell.setCellStyle(contextStyle);
                        }
                        if (def.getWidth() == -1) {
                            sheet.autoSizeColumn(curColumn);
                        } else if (def.getWidth() == 0) {
                        } else {
                            sheet.setColumnWidth(curColumn, def.getWidth());
                        }

                    }
                }
            } else {
                row = sheet.createRow(2);
                cell = row.createCell(0);
            }
        } catch (Throwable e) {
            logger.error("填充内容出现错误：" + e.getMessage(), e);
        }
    }

    public CommonXlsxView() {
        super();
    }

    /**
     * 添加标题(第一行)与表头(第二行)
     *
     * @param sheet excelSheet assettitle 表头>数组 titleName 标题 headerStyle 标题样式
     *              contextStyle 表头样式
     */
    public void addTitle(XSSFSheet sheet, String titleName,String nextTitle, XSSFWorkbook wb) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, this.columns.size() - 1));
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(titleName);
        cell.setCellStyle(getTitleStyle(wb));
        int rowNum = 1;
        if(nextTitle != null) {
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, this.columns.size() - 1));
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue(nextTitle);
            cell.setCellStyle(getNextTitleStyle(wb));
            rowNum++;
        }
        row = sheet.createRow(rowNum);
        for (int i = 0; i < this.columns.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(this.columns.get(i).getShowName());
            cell.setCellStyle(getFirstRowStyle(wb));
        }
    }

    /**
     * <p>
     * 根据属性名获取属性值
     * </p>
     * fieldName 属性名 object 属性所属对象 支持Map扩展属性, 不支持List类型属性， return 属性值
     */
    @SuppressWarnings("unchecked")
    public Object getFieldValueByName(String fieldName, Object object) {
        try {
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                return map.get(fieldName);
            }
            Object fieldValue = object;
            if (StringUtils.hasLength(fieldName) && object != null) {
                String[] filedNames = fieldName.split("\\.");
                for(String filed : filedNames) {
                    if (filed!=null && fieldValue != null) {
                        PropertyDescriptor pd = new PropertyDescriptor(filed, fieldValue.getClass());
                        Method getMethod = pd.getReadMethod();
                        fieldValue = getMethod.invoke(fieldValue);
                    }
                }
            }
            return fieldValue;
        } catch (Throwable e) {
            logger.error("获取属性值出现异常：" + e.getMessage(), e);
            return null;
        }
    }

}