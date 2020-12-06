package com.lizc.manage.common.utils;

public class ColumnDefine {

    private String showName;

    private String fieldName;

    /**
     * 设置字典类型，用于导出时的键值对转换
     */
    private String dictType;

    /**
     * 设置日期格式
     */
    private String dateFormat;

    private int type = 0; // 0 默认格式 1 数值格式 2 金钱格式

    private int width = 3300; // 0 默认 -1 auto * 宽度

    public ColumnDefine(String showName) {
        this.showName = showName;
    }

    public ColumnDefine(String showName, String fieldName) {
        this.showName = showName;
        this.fieldName = fieldName;
    }

    public ColumnDefine(String showName, String fieldName, int type) {
        this.showName = showName;
        this.fieldName = fieldName;
        this.type = type;
    }

    public ColumnDefine(String showName, String fieldName, int type, int width) {
        this.showName = showName;
        this.fieldName = fieldName;
        this.type = type;
        this.width = width;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 返回一个设置了字典类型的ColumnDefine
     * @param dictType 字典的类型
     */
    public ColumnDefine transferDictTypeColumn(String dictType) {
        setDictType(dictType);
        return this;
    }

    /**
     * 设置日期数据的格式
     * @param pattern 格式值如："yyyy-MM-dd HH:mm:ss"
     */
    public ColumnDefine dateFormat(String pattern) {
        setDateFormat(pattern);
        return this;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
