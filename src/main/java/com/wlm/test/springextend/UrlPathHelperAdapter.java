package com.wlm.test.springextend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.wlm.test.springextend.service.PathVariableService;

/**
 * URL 地址匹配适配器类
 *
 * @author wengliemiao
 */
@Component
public class UrlPathHelperAdapter extends UrlPathHelper {

    private static final String SUFFIX = ".htm";

    @Autowired
    private PathVariableService pathVariableService;

    @Override
    public String getLookupPathForRequest(HttpServletRequest request) {
        String lookUpPath = super.getLookupPathForRequest(request);

        // 去除请求路径后缀
        if (lookUpPath.contains(SUFFIX)) {
            lookUpPath = lookUpPath.split("\\.")[0];
        }

        // 转换 @PathVariable 格式路径，并保存参数值
        lookUpPath = pathVariableService.convertPathVariable(lookUpPath);

        return lookUpPath;
    }

}
