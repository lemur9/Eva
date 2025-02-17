package com.lemur.eva.modules.eva.index.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class I18nController {

    @GetMapping(value = "/locale")
    public String localeHandler(HttpServletRequest request) {
        return "redirect:" + request.getHeader("referer");
    }
}