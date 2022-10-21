package com.xxxx.crm.controller;


import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.query.StudentinFormationQuery;
import com.xxxx.crm.service.StudentListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sal")
public class StudentLIstController extends BaseController {

        @Resource
        private StudentListService studentListService;

        @RequestMapping("list")
        @ResponseBody
        public Map<String,Object> querySaleChanceByParams(StudentinFormationQuery studentinFormationQuery, HttpServletRequest request, Integer flag){

            return studentListService.querystudentQueryByParams(studentinFormationQuery);
        }

}
