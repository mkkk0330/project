package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.NoticeMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Notice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mk
 */
@Service
public class NoticeService extends BaseService<Notice,Integer> {

    @Autowired
    private NoticeMapper noticeMapper;

    public Map<String, Object> queryList() {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(1,10);
        //得到分页对象
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeMapper.queryNoticeList());

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addNotice(Notice notice) {
        AssertUtil.isTrue(StringUtils.isBlank(notice.getTitle()),"标题不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(notice.getText()),"通知信息不能为空");
        //添加操作
        notice.setCreateDate(new Date());
        AssertUtil.isTrue(noticeMapper.insertSelective(notice)<1,"报修名单添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteNotice(Integer id) {
        AssertUtil.isTrue(id == null ,"待删除通知不存在");

        AssertUtil.isTrue(noticeMapper.deleteByPrimaryKey(id) != 1,"删除通知失败");
    }

    public Map<String, Object> query(Notice notice) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(1,10);
        //得到分页对象
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeMapper.queryNoticeListByParams(notice));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;

    }
}
