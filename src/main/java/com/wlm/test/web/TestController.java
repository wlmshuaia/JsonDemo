package com.wlm.test.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengliemiao
 */
@Controller(value = "thisIsTestController")
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/index")
    public String index() {
        return "test/index";
    }

    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(value = "testId", defaultValue = "-1") int testId) {
        return "test/detail";
    }

    @RequestMapping(value = "/path-test/{StringValue1}")
    @ResponseBody
    public String pathTest(@PathVariable("StringValue1") int IntegerValue) {
        return "pathTest: " + IntegerValue;
    }

    @RequestMapping(value = "/path-test-2/{StringValue1}")
    @ResponseBody
    public String pathTest0(@PathVariable("StringValue1") String StringValue1) {
        return "pathTest0: " + StringValue1;
    }

    @RequestMapping(value = "/path-test-2/m-{StringValue1}")
    @ResponseBody
    public String pathTest7(@PathVariable("StringValue1") String StringValue1) {
        return "pathTest7: m-" + StringValue1;
    }

    // 非法用法: 名称不同，类型不同
//    @RequestMapping(value = "/path-test/{IntegerValue}")
//    @ResponseBody
//    public String pathTest4(@PathVariable("IntegerValue") int IntegerValue) {
//        return "pathTest4: " + IntegerValue + "";
//    }

    // 非法用法: 名称不同，类型相同
//    @RequestMapping(value = "/path-test/{StringValue2}")
//    @ResponseBody
//    public String pathTest5(@PathVariable("StringValue2") String StringValue2) {
//        return "pathTest5: " + StringValue2 + "";
//    }

    // 非法用法: 名称相同，类型相同
//    @RequestMapping(value = "/path-test/{contextPath}")
//    @ResponseBody
//    public String pathTest6(@PathVariable("contextPath") String pathValue2,
//                            @RequestParam(value = "aaa", defaultValue = "") String aaa) {
//        return "pathTest6: " + pathValue2 + "";
//    }

    @RequestMapping(value = "/path-test/{contextPath}/{termValue}")
    @ResponseBody
    public String pathTest2(@PathVariable("contextPath") String pathValue,
                           @PathVariable("termValue") String termValue) {
        return pathValue + ", " + termValue;
    }

    @RequestMapping(value = "/path-test/{contextPath}/aaa/{termValue}/info")
    @ResponseBody
    public String pathTest3(@PathVariable("contextPath") String pathValue,
                            @PathVariable("termValue") String termValue) {
        return pathValue + ", " + termValue;
    }

//    @RequestMapping(value = "/params-test.htm")
//    @ResponseBody
//    public String paramsTest0(@RequestParam(value = "name", defaultValue = "") String name,
//                             @RequestParam(value = "value", defaultValue = "") String value,
//                             @RequestBody Map<String, Object> paramMap) {
//        System.out.println(name);
//        System.out.println(value);
//        System.out.println(paramMap);
//        return "";
//    }

    @RequestMapping(value = "/params-test")
    @ResponseBody
    public String paramsTest1(@RequestParam(value = "", defaultValue = "") String name,
                             @RequestParam(value = "", defaultValue = "") String value) {
        return name + ": " + value;
    }

    @RequestMapping(value = "/params-test", params = {"name=1", "value=2"})
    @ResponseBody
    public String paramsTest2(@RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "value", defaultValue = "") String value,
                              @RequestBody Map<String, Object> paramMap) {
        System.out.println(name);
        System.out.println(value);
        System.out.println(paramMap);
        return name + ": " + value;
    }

    @RequestMapping(value = "/exception-test")
    @ResponseBody
    public String exceptionTest(@RequestParam(value = "name", defaultValue = "") String name) {
        int res = Integer.parseInt(name);
        return "success: " + res;
    }

    @ExceptionHandler(Exception.class)
    public String handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        System.out.println("handlerException...");
        return "redirect:/error.do";
    }
}
