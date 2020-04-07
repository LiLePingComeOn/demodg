package com.example.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.OvlsResult;
import com.example.demo.entity.sys_user;
import com.example.demo.server.SysUserServer;
import com.example.demo.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserServer sysUserServer;

    @PostMapping("/user/login")
    public OvlsResult Login(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        OvlsResult os = new OvlsResult();
        String password = Md5Util.encrypt32(jsonObject.getString("password"));
        map.put("username",jsonObject.getString("username"));
        map.put("password",password);
        sys_user sys_user = sysUserServer.selectByUsernameAPassword(map);
        if(sys_user!=null){
            os.setStatus(1);
            os.setMsg("登陆成功");
            os.setData(sys_user);
        }else{
            os.setStatus(0);
            os.setMsg("登陆失败，请确认密码或用户名");
        }
        return os;
    }

    @GetMapping("/sysuer/getAll")
    public OvlsResult getAll(){
        List<sys_user> sys_user = sysUserServer.selectAll();
        OvlsResult os = new OvlsResult();
            if(sys_user.size()>0){
                os.setStatus(1);
                os.setMsg("查询成功");
                os.setData(sys_user);
            }else{
                os.setStatus(0);
                os.setMsg("无可用用户");
            }
            return os;
    }

}
