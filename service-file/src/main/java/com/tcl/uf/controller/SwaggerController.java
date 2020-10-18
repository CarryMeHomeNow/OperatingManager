package com.tcl.uf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping(value = "/swagger")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
