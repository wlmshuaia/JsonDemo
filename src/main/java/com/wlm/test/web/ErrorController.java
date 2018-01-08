package com.wlm.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误处理控制器
 *
 * @author wengliemiao
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "exception/error";
    }

    @RequestMapping(value = "/miss")
    public String miss() {
        return "exception/miss";
    }
}
