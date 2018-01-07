package com.wlm.test.extend;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.util.StringValueResolver;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

/**
 * spring 4.x 可通过添加 {@link StringValueResolver} 在初始化时修改 {@link AbstractHandlerMethodMapping#urlMap}
 *
 * @author wengliemiao
 */
//@Service
public class ProcessPropertiesExtendPostProcessor extends PlaceholderConfigurerSupport implements BeanFactoryPostProcessor {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        System.out.println("custom processProperties...");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("custom postProcessBeanFactory");
        this.doProcessProperties(beanFactory, new StringValueResolver() {
            @Override
            public String resolveStringValue(String strVal) {
//                System.out.println("custom string resolver: " + strVal);
                if (!strVal.contains("*") && strVal.endsWith(".htm")) {
                    return strVal.split(".htm")[0];
                }
                return strVal;
            }
        });
    }
}
