package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.RepairService;
import com.xxxx.crm.vo.Repair;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author mk
 */
@Controller
@RequestMapping("repair")
public class RepairController extends BaseController {

    @Resource
    private RepairService repairService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryRepairList() {
        return  repairService.queryRepairList();
    }

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> queryRepairListByParams(Repair repair) {
        return  repairService.queryRepairListByParams(repair);
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRepair(Repair repair) {
        repairService.addRepair(repair);
        return success("报修名单添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateRepair(Repair repair) {
        repairService.updateRepair(repair);
        return success("报修名单修改成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteRepair(Integer id) {
        repairService.deleteRepair(id);
        return success("报修名单删除成功");
    }

    @PostMapping("delete1")
    @ResponseBody
    public ResultInfo deleteRepairByIds(Integer[] ids) {
        repairService.deleteRepairByIds(ids);
        return success("报修名单删除成功");
    }

    @RequestMapping("index")
    public String index(){
        return "repair/index";
    }

    //进入报修名单添加界面
    @RequestMapping("toSaleChancePage")
    public String toSaleChancePage(Integer repairId, HttpServletRequest request){
        //判断saleChanceId是否为空
        if (repairId!=null) {
            //通过id查找数据，放入到请求域中，让前台接收
            Repair repair = repairService.selectByPrimaryKey(repairId);
            request.setAttribute("repair",repair);
        }
        return "repair/add_update";
    }

}
