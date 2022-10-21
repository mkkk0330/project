package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.DormitoryMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Dormitory;
import com.xxxx.crm.vo.Repair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mk
 */
@Service
public class DormitoryService extends BaseService<Dormitory,Integer> {

    @Resource
    private DormitoryMapper dormitoryMapper;

    public Map<String, Object> queryDormitoryListByParams(Dormitory dormitory) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(1,10);
        //得到分页对象
        PageInfo<Dormitory> pageInfo = new PageInfo<>(dormitoryMapper.queryRepairListByParams(dormitory));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDormitoryListByParams(Dormitory dormitory) {
        AssertUtil.isTrue(dormitory.getBuildingId() == null,"楼号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(dormitory.getName()), "宿舍号不能为空");
        AssertUtil.isTrue(dormitory.getType() == null,"宿舍类型不能为空");
        AssertUtil.isTrue(dormitory.getAvailable() == null,"宿舍剩余床位不能为空");
        //添加操作
        AssertUtil.isTrue(dormitoryMapper.insertSelective(dormitory)<1,"报修名单添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Dormitory dormitory) {
        AssertUtil.isTrue(dormitory.getId() == null,"待修改记录不存在");
        Dormitory temp = dormitoryMapper.selectByPrimaryKey(dormitory.getId());
        AssertUtil.isTrue(temp == null,"待修改记录不存在");
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(dormitory.getName()),"宿舍门牌不能为空");
        AssertUtil.isTrue(dormitory.getBuildingId() == null, "楼号不能为空");
        AssertUtil.isTrue(dormitory.getAvailable() == null,"剩余床位不能为空");

        AssertUtil.isTrue(dormitoryMapper.updateByPrimaryKeySelective(dormitory)!=1,"用户添加失败！");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer[] id) {
        AssertUtil.isTrue(id == null || id.length<1,"待删除记录不存在");

        AssertUtil.isTrue(dormitoryMapper.deleteBatch(id)!=id.length,"删除记录失败");
    }

    /*查询宿舍的所有名字*/
    public List<String> listDormitory(){
        List<String> strings=dormitoryMapper.listDormitory();
        strings.forEach(System.out::println);
        return strings;
    }

}
