package com.wlm.test.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlm.test.category.Category;

/**
 * @author wengliemiao
 */
@Controller
@RequestMapping(value = "/category")
public class CategorySiteController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/save-by-model", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String saveByModel(@RequestBody Category category) {
        return category.toString();
    }

    @RequestMapping(value = "/save-by-model-2", method = RequestMethod.POST)
    @ResponseBody
    public Category saveByModel2(@RequestBody Category category) {
        return category;
    }

    @RequestMapping(value = "/save-by-map", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String saveByMap(@RequestBody Map<String, Object> valMap) {
        return valMap.toString();
    }

    @RequestMapping(value = "/save-by-map-2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveByMap2(@RequestBody Map<String, Object> valMap) {
        return valMap;
    }
}
