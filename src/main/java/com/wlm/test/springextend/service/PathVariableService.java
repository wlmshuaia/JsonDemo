package com.wlm.test.springextend.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * {@link PathVariable} 数据处理 service 接口
 *
 * @author wengliemiao
 */
@Service
public class PathVariableService {

    /**
     * 保存 {@link PathVariable} 格式数据
     */
    private static final Map<String, String> URI_TEMPLATE_VARIABLES = new LinkedHashMap<>();

    /**
     * 判断 "{}" 格式路径正则表达式
     */
    private static final Pattern PATH_VARI_PATTERN = Pattern.compile("\\{[^/]+?\\}");

    private static PathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 保存 {@link PathVariable} 数据，并返回转换后的 uri 路径
     *
     * @param uriPath {@link PathVariable} 格式 uri 路径
     * @return 转换后的 uri 路径
     */
    @SuppressWarnings("unchecked")
    public String convertPathVariable(String uriPath) {
        Map<String, Object> paramInfoMap = getParamInfoMap(uriPath);
        if (!paramInfoMap.isEmpty()) {
            String prefix = (String) paramInfoMap.get("prefix");
            List<ParamInfo> paramInfoList = (List<ParamInfo>) paramInfoMap.get("paramInfoList");
            String[] pathVariArr = (String[]) paramInfoMap.get("pathVariArr");
            if (pathVariArr.length > 0 && pathVariArr.length == paramInfoList.size()) {
                StringBuilder resUriPath = new StringBuilder(prefix);

                for (int i = 0; i < pathVariArr.length; i++) {
                    String pathVari = pathVariArr[i];
                    ParamInfo paramInfo = paramInfoList.get(i);

                    if (paramInfo.isPathVariable()) {
                        URI_TEMPLATE_VARIABLES.put(paramInfo.getName(), pathVari);
                        resUriPath.append("{").append(paramInfo.getName()).append("}/");
                    } else {
                        resUriPath.append(pathVari).append("/");
                    }
                }

                return resUriPath.substring(0, resUriPath.length() - 1);
            }
        }
        return uriPath;
    }

    /**
     * 获取保存的 @PathVariable 数据，如无则返回 ""
     *
     * @param uriPath uri 路径, {contextPath} 格式
     * @return @PathVariable 数据
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getUriTemplateVariables(String uriPath) {
        Map<String, String> resMap = new HashMap<>();
        Map<String, Object> paramInfoMap = getParamInfoMap(uriPath);
        if (!paramInfoMap.isEmpty()) {
            List<ParamInfo> paramInfoList = (List<ParamInfo>) paramInfoMap.get("paramInfoList");

            for (ParamInfo paramInfo : paramInfoList) {
                if (URI_TEMPLATE_VARIABLES.containsKey(paramInfo.getName())) {
                    resMap.put(paramInfo.getName(), URI_TEMPLATE_VARIABLES.get(paramInfo.getName()));
                }
            }
        }
        return resMap;
    }

    /**
     * 获取 {@link PathVariable} 解析后的信息 map, 如匹配多个, 则取匹配度最高的一个
     * <p>匹配逻辑为: 前缀相同，且以 "/" 分割后的路径列表与 {@link PathVariableParamInfoEnum} 中配置的参数信息相比较，格式匹配或完全相同，则算匹配
     *
     * <p>key 值:
     * <li>prefix: 配置的路径前缀
     * <li>paramInfoList: 配置的 {@link PathVariable} 信息类列表
     * <li>pathVariArr: 根据 "/" 分隔后的的路径数组
     *
     * @param uriPath uri 路径
     * @return  {@link PathVariable} 解析后的信息 map
     */
    public Map<String, Object> getParamInfoMap(String uriPath) {
        Map<String, Object> resMap = new HashMap<>();

        List<Map<String, Object>> matchList = new ArrayList<>();

        for (String prefix : PathVariableParamInfoEnum.getPrefixList()) {
            if (uriPath.startsWith(prefix)) {
                List<List<ParamInfo>> pathVariInfoList = PathVariableParamInfoEnum.getList(prefix);

                // 去除前缀部分
                String tmpPath = uriPath.substring(prefix.length(), uriPath.length());
                String[] pathVariArr = tmpPath.split("/");

                for (List<ParamInfo> paramInfoList : pathVariInfoList) {
                    if (pathVariArr.length > 0 && pathVariArr.length == paramInfoList.size()) {
                        // 判断路径是否包含 "{}"
                        Matcher matcher = PATH_VARI_PATTERN.matcher(tmpPath);
                        boolean isPattern = matcher.find();

                        // 是否有匹配的模式
                        boolean isMatch = true;
                        for (int i = 0; i < pathVariArr.length; i ++) {
                            String pathVari = pathVariArr[i];
                            ParamInfo paramInfo = paramInfoList.get((i));

                            if (paramInfo.isPathVariable()) {
                                if (isPattern) {
                                    // "{}" 格式字符串
                                    isMatch = pathVari.equals("{" + paramInfo.getName() + "}");
                                } else {
                                    // 正则判断有效性
                                    isMatch = PathVariableFormatFactory.isPathVariable(pathVari, paramInfo.getClassz());
                                }
                            } else {
                                // 普通字符串
                                isMatch = pathVari.equals(paramInfo.getName());
                            }

                            if (!isMatch) {
                                break;
                            }
                        }

                        if (isMatch) {
                            Map<String, Object> matchMap = new HashMap<>();
                            matchMap.put("prefix", prefix);
                            matchMap.put("paramInfoList", paramInfoList);
                            matchMap.put("pathVariArr", pathVariArr);
                            matchList.add(matchMap);
                        }
                    }
                }
            }
        }

        // 匹配到多个配置，则排序后取最优
        if (matchList.size() > 1) {
            Comparator<Map<String, Object>> patternComparator = new MatchMapComparator(PATH_MATCHER.getPatternComparator(uriPath));
            matchList.sort(patternComparator);

            return matchList.get(0);
        }

        return matchList.isEmpty() ? resMap : matchList.get(0);
    }

    /**
     * 根据 {@link AntPathMatcher.AntPatternComparator} 排序规则进行排序
     */
    private class MatchMapComparator implements Comparator<Map<String, Object>> {

        private final Comparator<String> comparator;

        public MatchMapComparator(Comparator<String> comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
            return this.comparator.compare((String) o1.get("prefix"), (String) o2.get("prefix"));
        }
    }

    public static void main(String[] args) {
        String uriPath = "/test/path-test-2/m-aaa";
        PathVariableService pathVariableService = new PathVariableService();
        Map<String, Object> resMap = pathVariableService.getParamInfoMap(uriPath);
        System.out.println(resMap);
    }

}
