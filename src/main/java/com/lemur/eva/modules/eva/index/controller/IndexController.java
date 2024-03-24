package com.lemur.eva.modules.eva.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping({"/{pageId}", "index/{pageId}"})
    public String index(@PathVariable("pageId") String pageId) {
        String str = pageId.replaceAll("-", "/");
        return "/index/model/" + str;
    }
}
