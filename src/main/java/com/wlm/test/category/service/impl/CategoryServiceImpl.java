package com.wlm.test.category.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wlm.test.category.Category;
import com.wlm.test.category.service.CategoryService;

/**
 * @author wengliemiao
 */
@Service(value = "thisCategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Override
    public void save(Category category) {
        System.out.println("save category model...");
    }

    @Override
    public void save(Map<String, Object> cateMap) {
        System.out.println("save category map...");
    }

    @Override
    public void save(int cateId, String cateName) {
        System.out.println("save category info...");
    }
}
