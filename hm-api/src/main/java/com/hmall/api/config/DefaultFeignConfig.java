package com.hmall.api.config;

import com.hmall.api.fallback.ItemClientFallbackFactory;
import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return template -> {
            Long userId= UserContext.getUser();
            if(userId!=null){
                template.header("user-info",userId.toString());
            }
        };
    }
    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory() {
        return new ItemClientFallbackFactory();
    }
}