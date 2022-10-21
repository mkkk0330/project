package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.RepairMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Repair;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mk
 */
@Service
public class RepairService extends BaseService<Repair,Integer> {

    @Resource
    private RepairMapper repairMapper;

    /*public List<Repair> queryRepairList() {
        List<Repair> list = new ArrayList<>();
        list = repairMapper.queryRepairList();
        AssertUtil.isTrue(list.size()<1,"查询报修列表失败");
        return  list;
    }*/

    public Map<String,Object> queryRepairList() {

        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(1,10);
        //得到分页对象
        PageInfo<Repair> pageInfo = new PageInfo<>(repairMapper.queryRepairList());

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public Map<String, Object> queryRepairListByParams(Repair repair) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(1,10);
        //得到分页对象
        PageInfo<Repair> pageInfo = new PageInfo<>(repairMapper.queryRepairListByParams(repair));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRepair(Repair repair) {
        AssertUtil.isTrue(StringUtils.isBlank(repair.getBuildingId()),"楼号不能为空");
        AssertUtil.isTrue(repair.getName() == null, "宿舍号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(repair.getIllustrate()),"所需报修描述不能为空");
        //添加操作
        AssertUtil.isTrue(repairMapper.insertSelective(repair)<1,"报修名单添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRepair(Repair repair) {
        AssertUtil.isTrue(repair.getId() == null,"待修改记录不存在");
        Repair temp = repairMapper.selectByPrimaryKey(repair.getId());
        AssertUtil.isTrue(temp == null,"待修改记录不存在");
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(repair.getBuildingId()),"楼号不能为空");
        AssertUtil.isTrue(repair.getName() == null, "宿舍号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(repair.getIllustrate()),"所需报修描述不能为空");

        AssertUtil.isTrue(repairMapper.updateByPrimaryKeySelective(repair)!=1,"用户添加失败！");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRepair(Integer id) {
        AssertUtil.isTrue(id == null ,"待删除名单不存在");

        AssertUtil.isTrue(repairMapper.deleteByPrimaryKey(id) != 1,"删除用户失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRepairByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length<1,"待删除记录不存在");

        AssertUtil.isTrue(repairMapper.deleteRepairByIds(ids)!=ids.length,"删除报修记录失败");
    }
}
