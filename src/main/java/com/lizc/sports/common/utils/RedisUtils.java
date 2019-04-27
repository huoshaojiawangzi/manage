package com.lizc.sports.common.utils;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * redis缓存工具类
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2009-04-23 06:02
 **/
@Component
public class RedisUtils
{
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private JedisPool jedisPool;

    private static RedisUtils jedisUtils;

    @PostConstruct
    public void init()
    {
        jedisUtils = this;
        jedisUtils.jedisPool = this.jedisPool;
    }

    private static Jedis getJedis()
    {
        return jedisUtils.jedisPool.getResource();
    }

    /**
     * 删除stirng缓存
     * 
     * @param key
     *            缓存标识
     * @param index
     *            库标识，输入值范围在0-05
     * @return 成功返回true；失败返回false
     */
    public static boolean del(final String key, int index)
    {
        try (Jedis jedis = getJedis())
        {
            jedis.select(index);
            jedis.del(key);
            return true;
        }
        catch (Exception e)
        {
            logger.error("删除缓存异常", e);
        }
        return false;
    }

    /**
     * 删除stirng缓存(默认选择一个库)
     * 
     * @param key
     *            缓存标识
     * @return 成功返回true；失败返回false
     */
    public static boolean del(final String key)
    {
        return del(key, 0);
    }

    /**
     * 读取stirng缓存
     *
     * @param key
     *            缓存标识
     * @return 返回value，如果不存在该key则返回null
     */
    public static String getOne(final String key, int index)
    {
        if(jedisUtils == null)
        {
            return null;
        }
        try (Jedis jedis = getJedis())
        {
            jedis.select(index);
            return jedis.get(key);
        }
        catch (Exception e)
        {
            logger.error("查询缓存异常", e);
        }
        return null;
    }

    /**
     * 读取stirng缓存(默认读取一个库)
     * 
     * @param key
     *            缓存标识
     * @return 返回value，如果不存在该key则返回null
     */
    public static String getOne(final String key)
    {
        return getOne(key, 0);
    }

    /**
     * 增加缓存
     * 
     * @param key
     *            缓存标识
     * @param value
     *            缓存值
     * @param index
     *            库标识，输入值范围在0-05
     * @return 成功返回true；失败返回false
     */
    public static boolean setOne(final String key, String value, int index)
    {
        try (Jedis jedis = getJedis())
        {
            jedis.select(index);
            jedis.set(key, value);
            return true;
        }
        catch (Exception e)
        {
            logger.debug("写入缓存异常:", e);
        }
        return false;
    }

    /**
     * 增加缓存(默认缓存到标识为0的库中)
     * 
     * @param key
     *            缓存标识
     * @param value
     *            缓存值
     * @return 成功返回true；失败返回false
     */
    public static boolean setOne(final String key, String value)
    {
        return setOne(key, value, 0);
    }

    /**
     * 增加list集合缓存
     * 
     * @param key
     *            缓存标识
     * @param list
     *            缓存的list
     * @param index
     *            库标识，输入值范围在0-05
     * @return 成功返回true；失败返回false
     */
    public static boolean setList(final String key, List list, int index)
    {
        String listString = JSON.toJSONString(list);
        return setOne(key, listString, index);
    }

    /**
     * 增加list集合缓存(默认缓存到标识为0的库中)
     * 
     * @param key
     *            缓存标识
     * @param list
     *            缓存的list
     * @return 成功返回true；失败返回false
     */
    public static boolean setList(final String key, List list)
    {
        return setList(key, list, 0);
    }

    /**
     * 获取一个list对象集合
     * 
     * @param key
     *            缓存标识
     * @param index
     *            库标识
     * @param clazz
     *            返回的集合元素类型
     * @return list集合
     */
    public static List getList(final String key, int index, Class clazz)
    {
        String listString = getOne(key);
        return JSON.parseArray(listString, clazz);
    }

    /**
     * 获取一个list对象集合(默认缓存到标识为0的库中)
     * 
     * @param key
     *            缓存标识
     * @param clazz
     *            返回的集合元素类型
     * @return list集合
     */
    public static List getList(final String key, Class clazz)
    {
        return getList(key, 0, clazz);
    }

}
