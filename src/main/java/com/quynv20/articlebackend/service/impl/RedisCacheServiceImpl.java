//package com.quynv20.articlebackend.service.impl;
//
//import com.quynv20.articlebackend.Utils.ValidateUtil;
//import com.quynv20.articlebackend.service.RedisCacheService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.BoundValueOperations;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service(value = "redisCacheService")
//public class RedisCacheServiceImpl implements RedisCacheService {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//    @Override
//    public String getStringValueForKey(String key) {
//        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
//        // If value is empty string, it's still a value.
//        return ops.get() != null ? ops.get() : null;
//    }
//
//    @Override
//    public void setStringValue(String key, String value, int timeoutInSeconds) {
//        if (ValidateUtil.isNotNullOrEmpty(key) && ValidateUtil.isNotNullOrEmpty(value)) {
//            redisTemplate.boundValueOps(key).set(value, timeoutInSeconds, TimeUnit.SECONDS);
//        }
//
//    }
//}
