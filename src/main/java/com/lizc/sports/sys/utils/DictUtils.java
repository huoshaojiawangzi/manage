package com.lizc.sports.sys.utils;


import com.lizc.sports.sys.entity.Dictionary;
import com.lizc.sports.sys.sevice.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class DictUtils
{
    @Autowired
    private DictionaryService dictionaryService;

    private static DictUtils dictUtils;

    private DictUtils()
    {}

    @PostConstruct
    public void init()
    {
        dictUtils = this;
        dictUtils.dictionaryService = this.dictionaryService;
    }

    public static List<Dictionary> findAll()
    {
        return dictUtils.dictionaryService.findAllEnable();
    }

    public static List<Dictionary> findByType(String type)
    {
        List<Dictionary> resultList = new ArrayList<Dictionary>();
        List<Dictionary> dictionaries = findAll();
        for (Dictionary d : dictionaries)
        {
            if (type.equals(d.getType()))
            {
                resultList.add(d);
            }
        }
        return resultList;
    }

    /**
     * 通过type以及value来获取字典的label值
     * 
     * @param type
     *            字典类型
     * @param value
     *            字典value
     * @param defaultLabel
     *            默认的label，如果没有根据type以及value找到label，则返回该值
     * @return 字典类的label值
     */
    public static String getDictLabel(String type, String value, String defaultLabel)
    {
        List<Dictionary> dictionaries = findAll();
        for (Dictionary d : dictionaries)
        {
            if (type.equals(d.getType()) && value.equals(d.getValue()))
            {
                return d.getLabel();
            }
        }
        return defaultLabel;
    }

    public static String getDictLabel(String type, String value)
    {
        return getDictLabel(type, value, null);
    }

    /**
     * 通过type以及vlabel来获取字典的value值
     * 
     * @param type
     *            字典类型
     * @param label
     *            字典label
     * @param defaultValue
     *            默认的value，如果没有根据type以及label找到value，则返回该值
     * @return 字典类的value值
     */
    public static String getDictValue(String type, String label, String defaultValue)
    {
        List<Dictionary> dictionaries = findAll();
        for (Dictionary d : findAll())
        {
            if (type.equals(d.getType()) && label.equals(d.getLabel()))
            {
                return d.getValue();
            }
        }
        return defaultValue;
    }

    public static String getDictValue(String type, String label)
    {
        return getDictValue(type, label, null);
    }

}