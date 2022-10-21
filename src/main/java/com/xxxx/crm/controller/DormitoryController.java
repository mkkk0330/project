package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.DormitoryService;
import com.xxxx.crm.vo.Dormitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mk
 */

@Controller
@RequestMapping("dormitory")
public class DormitoryController extends BaseController {

    @Autowired
    private DormitoryService dormitoryService;

    @RequestMapping("index")
    public String index(){
        return "dormitory/index";
    }

    @RequestMapping("toSaleChancePage")
    public String toSaleChancePage(Integer dormitoryId, HttpServletRequest request){
        //判断saleChanceId是否为空
        if (dormitoryId!=null) {
            //通过id查找数据，放入到请求域中，让前台接收
            Dormitory dormitory = dormitoryService.selectByPrimaryKey(dormitoryId);
            request.setAttribute("dormitory",dormitory);
        }
        return "dormitory/add_update";
    }

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> queryDormitoryListByParams(Dormitory dormitory) {
        return  dormitoryService.queryDormitoryListByParams(dormitory);
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addDormitoryListByParams(Dormitory dormitory) {
        dormitoryService.addDormitoryListByParams(dormitory);
        return  success("宿舍添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Dormitory dormitory) {
        dormitoryService.update(dormitory);
        return  success("宿舍信息更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] id) {
        dormitoryService.delete(id);
        return  success("宿舍信息删除成功");
    }

}
