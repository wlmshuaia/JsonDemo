package com.wlm.test.springextend;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * ANT 格式地址正则匹配适配器类
 *
 * @author wengliemiao
 */
@Component
public class AntPathMatcherAdapter extends AntPathMatcher {

    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        if (pattern.endsWith(".*")) {
            return false;
        }
        return super.doMatch(pattern, path, fullMatch, uriTemplateVariables);
    }

}
