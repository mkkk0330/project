package com.xxxx.crm.controller;


import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.dao.StudentinFormationMapper;
import com.xxxx.crm.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/studentf")
public class StudentStayController extends BaseController {
    @Resource
    private StudentinFormationMapper studentinFormationMapper;
    @RequestMapping("/index")
    public String index(){
        return "student/studentStay";
    }
    @RequestMapping("/addOrUpdateSaleChancePage")
    public String toSaleChancePage(Integer id, HttpServletRequest request){
        if (id!=null) {
            //通过id查找数据，放入到请求域中，让前台接收
            Student student = (Student) (studentinFormationMapper.selectByPrimaryKey(id));
            request.setAttribute("student",student);
        }
        return "sal/addstudent";
    }
}
