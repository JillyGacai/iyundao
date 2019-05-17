package com.ayundao.service;

/**
 * @ClassName: RedisServcie
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/17 17:21
 * @Description: 服务-redis
 * @Version: V1.0
 */
public interface RedisServcie {

    /**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * get获取数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     * @param key
     * @return
     */
    boolean remove(String key);

}
