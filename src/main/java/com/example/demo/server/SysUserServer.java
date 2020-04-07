package com.example.demo.server;

import com.example.demo.entity.sys_user;

import java.util.List;
import java.util.Map;

public interface SysUserServer {

    sys_user selectByUsernameAPassword(Map<String,Object> map);

    List<sys_user> selectAll();

    public int insertUser(sys_user sys_user);

    public int updateUser(sys_user sys_user);

    public int deleteUserById(String id);

    public int deleteUserByIds(String[] ids);
}
