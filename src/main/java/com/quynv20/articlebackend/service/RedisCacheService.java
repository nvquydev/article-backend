package com.quynv20.articlebackend.service;

public interface RedisCacheService {
    String getStringValueForKey(String key);

    void setStringValue(String key, String value, int timeoutInSeconds);
}
