package com.xxxx.crm.service;


import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.StudentinFormationMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AddStudentService extends BaseService<Student,Integer> {
    @Resource
    private StudentinFormationMapper studentinFormationMapper;
    //添加学生
    //判断非空
    public void addStudent(Student student){
        //1.数据校验
        checkParams(student.getName(), student.getGender(), student.getNumber());
        //2.获取当前时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        student.setCreateDate(date);
        // 3.执行添加 判断结果
        AssertUtil.isTrue(studentinFormationMapper.insertSelective(student) < 1, "学生信息添加失败！");
    }
    private void checkParams(String name, String gender, String number) {
        AssertUtil.isTrue(StringUtils.isBlank(name), "请输入学生姓名！");
        AssertUtil.isTrue(StringUtils.isBlank(gender), "请输入学生性别！");
        AssertUtil.isTrue(StringUtils.isBlank(number), "请输入学号！");

    }
    //更新学生信息
    public void updateStudent (Student student) {
        //1.
        Student temp = (Student) (studentinFormationMapper.selectByPrimaryKey(student.getId()));
        // 判断是否为空
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");
        checkParams(student.getName(), student.getGender(), student.getNumber());
        AssertUtil.isTrue(studentinFormationMapper.updateByPrimaryKeySelective(student) < 1, "学生信息更新失败！");
    }

    //删除学生
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance (Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的学生！");
        // 删除数据
        AssertUtil.isTrue(studentinFormationMapper.deleteBatch(ids) < 0, "学生数据删除失败！");
    }
}
