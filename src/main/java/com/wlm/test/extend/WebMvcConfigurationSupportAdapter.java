package com.wlm.test.extend;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author wengliemiao
 */
public class WebMvcConfigurationSupportAdapter extends WebMvcConfigurationSupport {

    public Object[] getInterceptorArray() {
//        TestHandlerMapping testHandlerMapping = new TestHandlerMapping();
//        testHandlerMapping.setOrder(-1);
//        testHandlerMapping.setInterceptors(getInterceptors());

//        UrlPathHelperAdapter urlPathHelperAdapter = new UrlPathHelperAdapter();
//        testHandlerMapping.setUrlPathHelper(urlPathHelperAdapter);
//        testHandlerMapping.setPathMatcher(new AntPathMatcherAdapter(urlPathHelperAdapter));
//        return testHandlerMapping;
        return super.getInterceptors();
    }
}
