package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.server.DeviationServer;
import com.example.demo.server.PlanServer;
import com.example.demo.server.PointTempServer;
import com.example.demo.util.GetUUIDString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.util.JAXBSource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PlanController {

    @Autowired
    private PlanServer planServer;

    @Autowired
    private PointTempServer pointTempServer;

    @GetMapping("/plan/getAll")
    public OvlsResult getAllPlan(){
            OvlsResult os = new OvlsResult();
            os.setStatus(1);
            os.setMsg("查询成功");
        List<plan> list = planServer.selectAll();
        for(int i = 0; i<list.size(); i++){
         plan  plan = list.get(i);
          List<pointtemp> list1 = pointTempServer.selectByPlan(plan.getId());
         plan.setList(list1);
        }
        os.setData(list);
            return os;
    }

    @PostMapping("/plan/insterPlan")
    public OvlsResult insterPlan(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        plan pa = new plan();
        pointtemp pt = new pointtemp();
        //获取统一方案id
        String planid = GetUUIDString.getUUID();
        JSONArray ja = new JSONArray();
         ja =  jsonObject.getJSONArray("domains");
         for(int i = 0; i<ja.size(); i++){
             pt.setId(GetUUIDString.getUUID());
             pt.setPlanid(planid);
             //获取json数组
             JSONObject jsonparm = JSON.parseObject(ja.getString(i));
             pt.setPointtemperature(jsonparm.getString("tp"));
            int check =  pointTempServer.insertpointtemp(pt);
         }
         //将数据加入plan
         pa.setId(planid);
         pa.setValue(jsonObject.getString("value"));
         pa.setArrangeValue(jsonObject.getString("arrangeValue"));
         pa.setDifferentValue(jsonObject.getString("differentValue"));
         pa.setCretime(new Date());
         pa.setFlow(jsonObject.getString("flow"));
         pa.setTestcount(jsonObject.getString("testcount"));
         pa.setTestinterval(jsonObject.getString("testinterval"));
        int check = planServer.insertPlan(pa);
        if(check == 1) {
            os.setStatus(check);
            os.setMsg("添加成功");
        }else{
            os.setStatus(0);
            os.setMsg("添加失败");
        }
        return os;
    }

    @PostMapping("/plan/deleteById")
    public OvlsResult deletepointtempByPlanId(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        int check = planServer.deletePlanById(jsonObject.getString("id"));
        int check2 = pointTempServer.deletepointtempByPlanId(jsonObject.getString("id"));
        if(check== 1 && check2==1){
            os.setStatus(1);
            os.setMsg("删除成功");
        }else{
         os.setStatus(0);
         os.setMsg("删除失败，请联系管理员");
        }
        return os;
    }
}
