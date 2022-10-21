package com.xxxx.crm.controller;


import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.AddStudentService;
import com.xxxx.crm.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/add")
public class AddStudentController extends BaseController {
    @Resource
    private AddStudentService addStudentService;

    @RequestMapping("/addstudent")
    @ResponseBody
    public ResultInfo Add(Student student){
        addStudentService.addStudent(student);
        return success("学生添加成功");
    }
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo updateSaleChance(HttpServletRequest request, Student student){
        addStudentService.updateStudent(student);
        return success("学生信息更新成功！");
    }
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance (Integer[] ids) {
        // 删除营销机会的数据
        addStudentService.deleteBatch(ids);
        return success("学生数据删除成功！");
    }


}


