package com.wlm.test.springextend.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * {@link PathVariable} 格式判断工厂类
 *
 * @author wengliemiao
 */
public class PathVariableFormatFactory {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^[1-9]\\d*$");

    /**
     * 判断是否是 {@link PathVariable} 格式
     *
     * @param value 字符串内容
     * @param classz 字符串类型
     * @return 是否是 {@link PathVariable} 格式
     */
    public static boolean isPathVariable(String value, Class<?> classz) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        if (classz == Integer.class || classz == Long.class) {
            Matcher matcher = INTEGER_PATTERN.matcher(value);
            return matcher.matches();
        }

        // String 类型
        return true;
    }
}
