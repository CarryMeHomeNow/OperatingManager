package com.tcl.uf.version.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

	@RequestMapping(value = "/swagger")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
