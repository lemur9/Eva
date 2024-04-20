package com.lemur.eva.modules.eva.index.controller;

import com.lemur.eva.core.result.DataResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    @ResponseBody
    @GetMapping("/")
    public DataResult<String> index() {
        String tips = "你好，eva已启动，请启动eva-ui，才能访问页面！";
        return new DataResult<String>().ok(tips);
    }

    @GetMapping({"index/{pageId}"})
    public String index(@PathVariable("pageId") String pageId) {
        String str = pageId.replaceAll("-", "/");
        return "/index/model/" + str;
    }

    @ResponseBody
    @GetMapping({"goto/{pageId}"})
    public DataResult<Object> parsePage(@PathVariable("pageId") String pageId) {
        String str = pageId.replaceAll("-", "/");
        return new DataResult<>().ok(str);
    }
}
