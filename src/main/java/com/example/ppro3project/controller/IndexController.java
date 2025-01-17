package com.example.ppro3project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/403")
    public String forbidden() {
        return "forbidden";
    }

    @GetMapping("/admin/")
    @ResponseBody
    public String admin() {
        return "<h1>Admin section</h1>";
    }

    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}
