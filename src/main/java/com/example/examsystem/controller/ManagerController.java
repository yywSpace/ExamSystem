package com.example.examsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {

    @RequestMapping("/background")
    public String  background(){
        return "manager/background";
    }
}
