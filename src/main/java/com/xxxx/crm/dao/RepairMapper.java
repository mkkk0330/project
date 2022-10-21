package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Repair;

import java.util.List;

public interface RepairMapper extends BaseMapper<Repair,Integer> {

    List<Repair> queryRepairList();

    List<Repair> queryRepairListByParams(Repair repair);

    Integer deleteRepairByIds(Integer[] ids);

}