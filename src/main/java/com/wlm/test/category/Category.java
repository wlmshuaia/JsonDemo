package com.wlm.test.category;

/**
 * 分类实体类对象
 *
 * @author wengliemiao
 */
public class Category {

    private int cateId = 0;

    private String cateName = "";

    /**
     * 获取 cateId
     *
     * @return cateId
     */
    public int getCateId() {
        return cateId;
    }

    /**
     * 设置 cateId
     *
     * @param cateId
     */
    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    /**
     * 获取 cateName
     *
     * @return cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * 设置 cateName
     *
     * @param cateName
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                '}';
    }
}
