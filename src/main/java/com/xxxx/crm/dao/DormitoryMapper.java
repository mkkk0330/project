package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Dormitory;

import java.util.List;
import java.util.Map;

public interface DormitoryMapper extends BaseMapper<Dormitory,Integer> {


    List<Dormitory> queryRepairListByParams(Dormitory dormiory);

    /*查询所有的宿舍名字*/
    List<String> listDormitory();

}