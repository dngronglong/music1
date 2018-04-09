package com.dong.music.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * Created by tyf on 2018/3/20
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private BasePathInterceptor basePathInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(basePathInterceptor);
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("utf8");
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        registration.setName("characterEncodingFilter");
        return registration;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
