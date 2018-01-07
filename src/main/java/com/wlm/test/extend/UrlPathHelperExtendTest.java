package com.wlm.test.extend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import com.wlm.test.springextend.UrlPathHelperAdapter;

/**
 * @author wengliemiao
 */
//@Service
public class UrlPathHelperExtendTest extends AbstractHandlerMapping implements BeanPostProcessor, InitializingBean {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(bean.getClass().getSimpleName());
        System.out.println(beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(bean.getClass().getSimpleName());
//        System.out.println(beanName);
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UrlPathHelperExtend...2");
        setUrlPathHelper(new UrlPathHelperAdapter());
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        return null;
    }

}