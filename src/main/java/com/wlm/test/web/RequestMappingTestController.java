package com.wlm.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @RequestMapping 测试控制器
 *
 * @author wengliemiao
 */
@Controller
@RequestMapping("/rq-test")
public class RequestMappingTestController {

    @RequestMapping(value = "/info-0")
    public String info() {
        return "rqtest/info";
    }

    @RequestMapping(value = "/info-1")
    public String info1() {
        return "rqtest/info";
    }

    @RequestMapping(value = "/info-2")
    public String info2() {
        return "rqtest/info";
    }

    @RequestMapping(value = "/info-3")
    public String info3() {
        return "rqtest/info";
    }

    @RequestMapping(value = "/info-4")
    public String info4() {
        return "rqtest/info";
    }

    @RequestMapping(value = "/info-5")
    public String info5() {
        return "rqtest/info";
    }

}
