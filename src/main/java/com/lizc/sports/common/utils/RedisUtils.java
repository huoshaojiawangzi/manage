package com.lizc.sports.common.utils;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * redis缓存工具类
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-23 16:12
 **/
@Component
public class RedisUtils
{
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static RedisUtils jedisUtils;

    @PostConstruct
    public void init()
    {
        jedisUtils = this;
        jedisUtils.redisTemplate = this.redisTemplate;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public static String getOne(final String key)
    {
        return jedisUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public static boolean setOne(final String key, String value)
    {
        boolean result = false;
        try
        {
            jedisUtils.redisTemplate.opsForValue().set(key, value);
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("写入缓存异常:", e);
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public static boolean getAndSetOne(final String key, String value)
    {
        boolean result = false;
        try
        {
            jedisUtils.redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("更新缓存异常:", e);
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public static boolean delete(final String key)
    {
        boolean result = false;
        try
        {
            jedisUtils.redisTemplate.delete(key);
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("删除缓存异常:", e);
        }
        return result;
    }

    /**
     * 增加一个集合元素
     * @param key
     * @param object
     * @return
     */
    public static boolean addList(String key, Object object)
    {
        boolean result = false;
        try
        {
            ListOperations<String, String> listRedis = jedisUtils.redisTemplate.opsForList();
            String objectString = JSON.toJSONString(object);
            listRedis.leftPush(key, objectString);
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("增加缓存集合元素异常:", e);
        }
        return result;
    }

    /**
     * 增加一个集合
     * @param key
     * @param objects
     * @return
     */
    public static boolean saveList(String key, List objects)
    {
        boolean result = false;
        try
        {
            ListOperations<String, String> listRedis = jedisUtils.redisTemplate.opsForList();
            for(Object object :objects)
            {
                String objectString = JSON.toJSONString(object);
                listRedis.leftPush(key, objectString);
            }
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("增加缓存集合元素异常:", e);
        }
        return result;
    }

    public static List getList(String key,Class clazz)
    {
        ListOperations<String, String> listRedis = jedisUtils.redisTemplate.opsForList();
        List list = new ArrayList();
        List<String> stringList = listRedis.range(key, 0, -1);
        for(String s : stringList)
        {
            Object object = JSON.parseObject(s,clazz);
            list.add(object);
        }
        return list;
    }
}
