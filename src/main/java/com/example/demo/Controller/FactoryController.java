package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.OvlsResult;
import com.example.demo.entity.factory;
import com.example.demo.server.FactoryServer;
import com.example.demo.util.GetUUIDString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class FactoryController {

    @Autowired
    private FactoryServer factoryServer;



    @GetMapping("/factory/getAll")
    public OvlsResult getAll(){
        OvlsResult os = new OvlsResult();
        List<Map<String,Object>> map = factoryServer.selectAll();
        if(map.size()>0){
            os.setStatus(1);
            os.setMsg("查询成功");
            os.setData(map);
        }else{
            os.setStatus(0);
            os.setMsg("查询失败");
        }
        return os;
    }

    @PostMapping("/factory/insertFactory")
    public OvlsResult insertFactory(@RequestBody factory factory){
        OvlsResult os = new OvlsResult();
        factory.setId(GetUUIDString.getUUID());
        int check = factoryServer.insertFactory(factory);
        if(check >=1){
            os.setStatus(1);
            os.setMsg("success");
        }else{
            os.setStatus(0);
            os.setMsg("fail");
        }
        return os;
    }

    @PostMapping("/factory/updateFactory")
    public OvlsResult updateFactory(@RequestBody factory factory){
        OvlsResult os = new OvlsResult();
        int check = factoryServer.updateFactory(factory);
        if(check >=1){
            os.setStatus(1);
            os.setMsg("成功");
        }else{
            os.setStatus(0);
            os.setMsg("失败");
        }
        return os;
    }

    @PostMapping("/factory/deleteFactory")
    public OvlsResult deleteFactory(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
     JSONArray ja = jsonObject.getJSONArray("ids");
     //System.out.println(ja);
     String [] ids = new String[ja.size()];
     for(int i = 0; i<ja.size(); i++){
       //  System.out.println(ja.getString(i));
         String data = ja.getString(i);
         ids[i] =  data;
     }
        int check = factoryServer.deleteFactoryByIds(ids);
     if(check >= 1){
         os.setStatus(1);
     }else{
         os.setStatus(0);
     }
     return os;
    }
}
