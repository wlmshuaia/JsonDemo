package com.wlm.test.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wlm.test.category.Category;
import com.wlm.test.category.service.CategoryService;

/**
 * @author wengliemiao
 */
@Controller
@RequestMapping(value = "/category")
public class CategorySiteController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(@RequestParam("cateId") int cateId,
                       @RequestParam(value = "cateName", defaultValue = "") String cateName) {
        this.categoryService.save(cateId, cateName);
        return cateId + ", " + cateName;
    }

    @RequestMapping(value = "/save-by-map", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String saveByMap(@RequestBody Map<String, Object> valMap) {
        categoryService.save(valMap);
        return valMap.toString();
    }

    @RequestMapping(value = "/save-by-map-2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveByMap2(@RequestBody Map<String, Object> valMap) {
        categoryService.save(valMap);
        return valMap;
    }

    @RequestMapping(value = "/save-by-model", method = RequestMethod.POST)
    @ResponseBody
    public String saveByModel(@RequestBody Category category) {
        categoryService.save(category);
        return category.toString();
    }

    @RequestMapping(value = "/save-by-model-2", method = RequestMethod.POST)
    @ResponseBody
    public Category saveByModel2(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }
}
