package com.xdclass.online_xdclass.utils;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BaseCache {

    /**
     * 十分钟缓存
     */
    private Cache<String, Object> tenMinuteCache = CacheBuilder.newBuilder()
            //设置缓存初始大小
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //缓存过期时间，写入10分钟过期
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();



    /**
     * 一小时缓存
     */
    private Cache<String, Object> oneHourCache = CacheBuilder.newBuilder()
            //设置缓存初始大小
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //缓存过期时间，写入1小时过期
            .expireAfterWrite(3600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();


    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
    public Cache<String, Object> getOneHourCache() {
        return oneHourCache;
    }

    public void setOneHourCache(Cache<String, Object> oneHourCache) {
        this.oneHourCache = oneHourCache;
    }
}
