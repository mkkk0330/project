package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.dao.SelMapper;
import com.xxxx.crm.dao.StudentinFormationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author mk
 */
@Controller
@RequestMapping("q")
public class SelController extends BaseController {

    @Resource
    private StudentinFormationMapper studentinFormationMapper;

    @PostMapping("a")
    @ResponseBody
    public List<Map<String,Object>> queryAll(){
        List<Map<String,Object>> a = studentinFormationMapper.queryAll();
        System.out.println(a);
        return a;
    }

}
