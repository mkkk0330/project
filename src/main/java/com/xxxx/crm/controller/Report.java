package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class Report extends BaseController {

    @RequestMapping("index")
    public String listReport(){
        return "report/reporter";
    }
}
