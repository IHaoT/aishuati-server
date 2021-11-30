package com.example.aishuatiserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SpringSessionConfig {
    public SpringSessionConfig() {
    }

    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 取消仅限同一站点设置
        cookieSerializer.setSameSite("None");
        cookieSerializer.setUseSecureCookie(true);
        return cookieSerializer;
    }
}