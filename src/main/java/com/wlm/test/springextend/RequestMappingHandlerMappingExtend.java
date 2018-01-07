package com.wlm.test.springextend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

/**
 * {@link RequestMapping} 方法扩展，自定义实现 {@link UrlPathHelper}, {@link PathMatcher}
 * <p>该实现主要目的为扩展 SpringMVC-4.0.3 以前的版本，4.0.3 以后的版本框架可通过 {@link WebMvcConfigurerAdapter} 自定义实现</p>
 *
 * @author wengliemiao
 */
//@Component
public class RequestMappingHandlerMappingExtend implements BeanPostProcessor{

    @Autowired
    private UrlPathHelperAdapter urlPathHelperAdapter;

    @Autowired
    private AntPathMatcherAdapter antPathMatcherAdapter;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerMapping) {
            RequestMappingHandlerMapping rmhm = (RequestMappingHandlerMapping) bean;
            rmhm.setUrlPathHelper(urlPathHelperAdapter);
            rmhm.setPathMatcher(antPathMatcherAdapter);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
