package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Notice;
import com.xxxx.crm.vo.Repair;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice,Integer> {

    List<Notice> queryNoticeList();

    List<Notice> queryNoticeListByParams(Notice notice);
}