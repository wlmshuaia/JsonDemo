package com.wlm.test.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wengliemiao
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUrlPathHelper(new UrlPathHelper2());
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HandlerInterceptor());
        System.out.println("add Interceptors...");
    }
}
