package com.wlm.test.springextend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.wlm.test.springextend.service.PathVariableService;

/**
 * ANT 格式地址正则匹配适配器类
 *
 * @author wengliemiao
 */
@Component
public class AntPathMatcherAdapter extends AntPathMatcher {

    @Autowired
    private PathVariableService pathVariableService;

    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        boolean isMatch = super.doMatch(pattern, path, fullMatch, uriTemplateVariables);
        if (!isMatch) {
            // 兼容未添加格式到 PathVariableService 中的数据
            if (pattern.endsWith(".*")) {
                pattern = pattern.split("\\.\\*")[0];
            }
            isMatch = pattern.equals(path);
        }

        // 获取 @PathVariable 格式参数
        if (uriTemplateVariables != null) {
            uriTemplateVariables.putAll(pathVariableService.getUriTemplateVariables(path));
        }

        return isMatch;
    }
}
