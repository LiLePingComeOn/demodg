package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.OvlsResult;
import com.example.demo.entity.blackplan;
import com.example.demo.server.BlackPlanServer;
import com.example.demo.util.GetUUIDString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BlackPlanController {

    @Autowired
    private BlackPlanServer blackPlanServer;

    @GetMapping("bplan/getAllBPlan")
    public OvlsResult getAllBlackPlan(){
        OvlsResult os = new OvlsResult();
        List<Map<String,Object>> list = blackPlanServer.selectAll();
        if(list.size()>0){
            os.setStatus(1);
            os.setMsg("查询成功");
            os.setData(list);
        }else {
            os.setStatus(0);
            os.setMsg("查询失败，或无数据");
        }
        return os;
    }

    @PostMapping("bplan/insertBplan")
    public OvlsResult inseertBlackPlan(@RequestBody blackplan blackplan){
        OvlsResult os = new OvlsResult();
        blackplan.setId(GetUUIDString.getUUID());
        blackplan.setCretime(new Date());
        int check =  blackPlanServer.insertPlan(blackplan);
        if(check == 1){
            os.setStatus(check);
            os.setMsg("插入成功给");
        }else{
            os.setStatus(check);
            os.setMsg("插入失败");
        }
        return os;
    }

    @PostMapping("/bplan/updateBplan")
    public OvlsResult updateBlackPlan(@RequestBody blackplan blackplan){
        OvlsResult os = new OvlsResult();
        blackplan.setId(GetUUIDString.getUUID());
        blackplan.setCretime(new Date());
        int check =  blackPlanServer.updatePlan(blackplan);
        if(check == 1){
            os.setStatus(check);
            os.setMsg("更新成功给");
        }else{
            os.setStatus(check);
            os.setMsg("更新失败");
        }
        return os;
    }

    @PostMapping("/bplan/deleteBplan")
    public OvlsResult deteleBlackPlan(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        int check =  blackPlanServer.deletePlanById(jsonObject.getString("id"));
        if(check == 1){
            os.setStatus(check);
            os.setMsg("删除成功给");
        }else{
            os.setStatus(check);
            os.setMsg("删除失败");
        }
        return os;
    }
}
