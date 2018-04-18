package com.dong.music.utils;

import com.dong.music.beans.SongListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 董荣龙
 * @date 2018-04-17 16:44
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getString(final String key) {
        Object result = null;
        result=stringRedisTemplate.opsForValue().get(key);
        if(result==null){
            return null;
        }
        return result.toString();
    }

    /**
     * 得到一个list集合
     * @param key
     * @return
     */
    public List getList(final String key){
        List result = new ArrayList<>();
        stringRedisTemplate.opsForList().range(key,0,-1).forEach(value ->{
            result.add(value);
        });
        if(result==null){
            return null;
        }
        return result;
    }
    public List<SongListBean> getObbjectList(final String key){
        List<SongListBean> result = new ArrayList<>();
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result= (List<SongListBean>) operations.get(key);
        if(result==null){
            return null;
        }
        return result;
    }
    public Object getObject(String key){
        Object result=null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if(result==null){
            return null;
        }
        return result;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setString(final String key, String value) {
        boolean result = false;
        try {
//            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
            stringRedisTemplate.opsForValue().set(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加一个list集合
     * @param key
     * @param list
     * @return
     */
    public  boolean setList(final String key, List list){
        boolean result=false;
        try{
            stringRedisTemplate.opsForList().leftPushAll(key,list);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean setObjectList(final String key,List<?> list){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String,String> hmget(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
