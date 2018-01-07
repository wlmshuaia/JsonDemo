package com.wlm.test.springextend.service;

/**
 * 参数信息内部类
 *
 * @author wengliemiao
 */
public class ParamInfo {

    // 是否是 @PathVariable 类型参数，默认为 true
    private boolean isPathVariable;

    // @PathVariable 名称
    private String name;

    // @PathVariable 类型
    private Class<?> classz;

    public ParamInfo(String name, Class<?> classz) {
        this(name, classz, true);
    }

    public ParamInfo(String name, Class<?> classz, boolean isPathVariable) {
        this.name = name;
        this.classz = classz;
        this.isPathVariable = isPathVariable;
    }

    /**
     * 获取 @PathVariable 名称
     *
     * @return @PathVariable 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 @PathVariable 名称
     *
     * @param name @PathVariable 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取@PathVariable 类型
     *
     * @return @PathVariable 类型
     */
    public Class<?> getClassz() {
        return classz;
    }

    /**
     * 设置 @PathVariable 类型
     *
     * @param classz @PathVariable 类型
     */
    public void setClassz(Class<?> classz) {
        this.classz = classz;
    }

    /**
     * 设置是否是 @PathVariable 类型参数
     *
     * @param isPathVariable 是否是 @PathVariable 类型参数
     */
    public void setPathVariable(boolean isPathVariable) {
        this.isPathVariable = isPathVariable;
    }

    /**
     * 判断是否是 @PathVariable 类型参数
     *
     * @return 是否是 @PathVariable 类型参数
     */
    public boolean isPathVariable() {
        return isPathVariable;
    }

    @Override
    public String toString() {
        return "ParamInfo{" +
                "isPathVariable=" + isPathVariable +
                ", name='" + name + '\'' +
                ", classz=" + classz +
                '}';
    }
}
