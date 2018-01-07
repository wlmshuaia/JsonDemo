package com.wlm.test.springextend.service;

import java.util.*;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * {@link PathVariable} 类型数据常量枚举类
 *
 * @author wengliemiao
 */
public class PathVariableParamInfoEnum {

    /**
     * key 为 uri 路径前缀，value 为 {@link PathVariable} 信息类列表 {@link ParamInfo}，如果有多个则按出现顺序排列
     */
    private static final Map<String, List<List<ParamInfo>>> PATH_PREFIX_MAP;


    static {
        PATH_PREFIX_MAP = new HashMap<>();

        // for test 1: 前缀相同情况
        PATH_PREFIX_MAP.put("/test/path-test/", Arrays.asList(
                Arrays.asList(new ParamInfo("StringValue1", Integer.class)),
                Arrays.asList(
                    new ParamInfo("contextPath", Integer.class),
                    new ParamInfo("termValue", Integer.class)
                ),
                Arrays.asList(
                    new ParamInfo("contextPath", Integer.class),
                    new ParamInfo("aaa", String.class, false),
                    new ParamInfo("termValue", Integer.class),
                    new ParamInfo("info", String.class, false)
                )
        ));

        // for test 2: 前缀重合情况
        PATH_PREFIX_MAP.put("/test/path-test-2/", Arrays.asList(Arrays.asList(new ParamInfo("StringValue1", String.class))));
        PATH_PREFIX_MAP.put("/test/path-test-2/m-", Arrays.asList(Arrays.asList(new ParamInfo("StringValue1", String.class))));
    }

    /**
     * 根据 pathPrefix 获取 {@link #PATH_PREFIX_MAP} 中数据列表
     *
     * @param pathPrefix 路径前缀
     * @return 数据列表
     */
    public static List<List<ParamInfo>> getList(String pathPrefix) {
        if (PATH_PREFIX_MAP.containsKey(pathPrefix)) {
            return PATH_PREFIX_MAP.get(pathPrefix);
        }
        return new ArrayList<>();
    }

    /**
     * 获取 {@link #PATH_PREFIX_MAP} 配置的键值列表
     *
     * @return 键值列表
     */
    public static Set<String> getPrefixList() {
        return PATH_PREFIX_MAP.keySet();
    }

}
