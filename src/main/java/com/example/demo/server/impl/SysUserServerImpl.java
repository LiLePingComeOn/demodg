package com.example.demo.server.impl;

import com.example.demo.entity.sys_user;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.server.SysUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServerImpl implements SysUserServer {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public sys_user selectByUsernameAPassword(Map<String, Object> map) {
        return sysUserMapper.selectByUsernameAPassword(map);
    }

    @Override
    public List<sys_user> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public int insertUser(sys_user sys_user) {
        return sysUserMapper.insertUser(sys_user);
    }

    @Override
    public int updateUser(sys_user sys_user) {
        return sysUserMapper.updateUser(sys_user);
    }

    @Override
    public int deleteUserById(String id) {
        return sysUserMapper.deleteUserById(id);
    }

    @Override
    public int deleteUserByIds(String[] ids) {
        return sysUserMapper.deleteUserByIds(ids);
    }
}
