package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.QueryHouseparent;
import com.xxxx.crm.service.HouseparentService;
import com.xxxx.crm.vo.Houseparent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("house")
public class HouseparentController extends BaseController{

    @Resource
    private HouseparentService houseparentService;

    @RequestMapping("index")
    public String index(){
        return "houseparent/houseparent";
    }

    /*多条件查询*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(QueryHouseparent queryHouseparent){
        return houseparentService.queryHouseByParams(queryHouseparent);
    }

    /*打开添加，修改管理*/
    @RequestMapping("toUpdateAddPage")
    public String toUpdateAddPage(Integer id, HttpServletRequest request){
        if (id != null){
            Houseparent houseparent = houseparentService.queryHouseById(id);
            request.setAttribute("user",houseparent);
        }
        return "houseparent/add_update";
    }

    /*保存添加的管理*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveHouse(Houseparent houseparent){
        houseparentService.saveHouse(houseparent);
        return success("添加成功");
    }

    /*保存修改的管理*/
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Houseparent houseparent){
        houseparentService.updateHouse(houseparent);
        return success("修改成功");
    }

    /*批量删除*/
    @PostMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        houseparentService.deleteById(ids);
        return success("删除成功");
    }
}
