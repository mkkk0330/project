package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.StudentinFormationMapper;
import com.xxxx.crm.query.StudentinFormationQuery;
import com.xxxx.crm.vo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentListService extends BaseService<Student,Integer> {
    //分页查询学生信息
    @Resource
    private StudentinFormationMapper studentinFormationMapper;

    public Map<String,Object> querystudentQueryByParams(StudentinFormationQuery studentinFormationQuery) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(studentinFormationQuery.getPage(), studentinFormationQuery.getLimit());
        //得到分页对象
        PageInfo<Student> pageInfo = new PageInfo<>(studentinFormationMapper.selectByParams(studentinFormationQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

}
