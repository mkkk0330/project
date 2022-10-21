package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Student;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface StudentMapper extends BaseMapper<Student,Integer> {
    //查询所有的学生信息



    //学生添加
    Student addStudent(Student student);

    List<Student> query(Student student);



    //根据name查询宿舍号
    public String selectByPrimaryKey2(String name) throws DataAccessException;
}