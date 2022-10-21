package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.NoticeService;
import com.xxxx.crm.vo.Notice;
import com.xxxx.crm.vo.Repair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mkNoticeController
 */
@Controller
@RequestMapping("notice")
public class NoticeController extends BaseController {

    @Resource
    private NoticeService noticeService;


    @RequestMapping("toPage")
    public String toSaleChancePage(Integer noticeId, HttpServletRequest request){
        //判断saleChanceId是否为空
        if (noticeId!=null) {
            //通过id查找数据，放入到请求域中，让前台接收
            Notice notice = noticeService.selectByPrimaryKey(noticeId);
            request.setAttribute("notice",notice);
        }
        return "notice/add";
    }

    @RequestMapping("index")
    public String index() {
        return "notice/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryList() {
        return  noticeService.queryList();
    }

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> query(Notice notice) {
        return  noticeService.query(notice);
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addNotice(Notice notice) {
        noticeService.addNotice(notice);
        return success("通知添加成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteNotice(Integer id) {
        noticeService.deleteNotice(id);
        return success("通知删除成功");
    }

}
