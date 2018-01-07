package com.wlm.test.category.service;

import java.util.Map;

import com.wlm.test.category.Category;

/**
 * 分类操作 service 接口
 *
 * @author wengliemiao
 */
public interface CategoryService {

    /**
     * 保存分类信息
     *
     * @param category 分类信息
     */
    void save(Category category);

    /**
     * 保存分类信息
     *
     * @param cateMap 分类信息
     */
    void save(Map<String, Object> cateMap);

    /**
     * 保存分类信息
     *
     * @param cateId 分类 ID
     * @param cateName 分类名称
     */
    void save(int cateId, String cateName);
}
