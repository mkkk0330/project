package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.StudentQuery;
import com.xxxx.crm.service.StudentService;
import com.xxxx.crm.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("student")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;

    //查询所有的学生信息
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryStudents(StudentQuery studentQuery){

        return studentService.queryStudents(studentQuery);
    }


    //添加学生信息
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addStudent(Student student){
        studentService.addStudent(student);
        return success("学生添加成功!");
    }
    //删除学生信息
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleStudent(Integer[] studentId){
        studentService.deletuStudentId(studentId);
        return success("学生删除成功!");
    }
    //更新学生信息
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateStudent(Student student){
        studentService.updateSyudentSelective(student);
        return success("学生信息更新成功!");
    }
    //进入学生信息页面
    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "noticeStu/sale_chance";
    }

    @RequestMapping("addye")
    //进入添加学生页面
    public String addye(Integer id,HttpServletRequest request){
        //判断学生id是否为空
        System.out.println("测试");
        if (id!=null){
            //通过ID查询学生信息
            Student student=studentService.selectByPrimaryKey(id);
            //将数据设置到请求域中
            request.setAttribute("student",student);
        }
        return "noticeStu/add_update";
    }

}
