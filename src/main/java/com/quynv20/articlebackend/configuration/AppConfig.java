package com.quynv20.articlebackend.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    protected static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${pullToRefresh.timeout}")
    private int pullToRefreshTimeout;

    public int getPullToRefreshTimeout() {
        return pullToRefreshTimeout;
    }

    // You can remove the setter unless you specifically need it for something else
    public void setPullToRefreshTimeout(int pullToRefreshTimeout) {
        this.pullToRefreshTimeout = pullToRefreshTimeout;
    }
}
