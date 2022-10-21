package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.service.DormitoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("reward")
public class ReporterDateController extends BaseController {
    @Resource
    private DormitoryService dormitoryService;



    @RequestMapping("reportDate")
    @ResponseBody
    public List<String> listDormitory(){
        //System.out.println(dormitoryService.listDormitory());
        return dormitoryService.listDormitory();
    }

}
