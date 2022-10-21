package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.StudentMapper;
import com.xxxx.crm.exceptions.ParamsException;
import com.xxxx.crm.query.StudentQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService extends BaseService<Student,Integer> {
    @Resource
    private StudentMapper studentMapper;

    //查询所有的学生信息
    public Map<String,Object> queryStudents(StudentQuery studentQuery) {
        Map<String,Object> map=new HashMap<>();

        //开启分页
        PageHelper.startPage(studentQuery.getPage(),studentQuery.getLimit());
        //得到对应的分页对象
        PageInfo<Student> pageInfo=new PageInfo<>(studentMapper.selectByParams(studentQuery));

        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }


    //添加学生
    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent(Student student) {

        //1.参数校验
        checkLoginParams(student.getName(),student.getGender());
        //2.设置参数的默认值
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = format.format(new Date());
        student.setCreateDate(datestr);
        //执行添加操作,判断受影响的行数
        AssertUtil.isTrue(studentMapper.insertSelective(student)!=1,"学生添加失败!");
    }

    //参数校验
    private void checkLoginParams(String studentName, String studentGender) {
        //判断学生姓名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(studentName),"学生名称不能为空!");
        //判断学生性别是否为空
        AssertUtil.isTrue(StringUtils.isBlank(studentGender),"性别不能为空!");

        if (!"男".equals(studentGender)&&!"女".equals(studentGender)&&!"人妖".equals(studentGender)){
            throw  new ParamsException();
        }
    }

    //删除学生
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletuStudentId(Integer[] studentId) {
        //判断stedentId是否为空,长度是否不大于0
        AssertUtil.isTrue(studentId==null,"待删除记录不存在!");
        //执行删除操作,判断受影响的行数
        AssertUtil.isTrue(studentMapper.deleteBatch(studentId)!=studentId.length,"删除失败!");
    }

    //更新学生信息
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSyudentSelective(Student student) {
        //1.参数校验
        //判断学生ID是否为空,且数据存在
        AssertUtil.isTrue(null==student.getId(),"待更新记录不存在!");
        //通过id查询数据
        Student student1=studentMapper.selectByPrimaryKey(student.getId());
        //判断是否存在
        AssertUtil.isTrue(null==student1,"待更新记录不存在!");

        //参数校验
        checkLoginParams(student.getName(),student.getGender());

        //2.设置默认值
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = format.format(new Date());
        student.setCreateDate(datestr);

        AssertUtil.isTrue( studentMapper.updateByPrimaryKeySelective(student)!=1,"学生信息更新失败!");
    }
}
