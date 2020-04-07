package com.example.demo.server.impl;

import com.example.demo.entity.blackdevice;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.server.BlackDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackDeviceServerImpl implements BlackDeviceServer {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public blackdevice selectByid(String id) {
        return deviceMapper.selectByid(id);
    }

    @Override
    public List<blackdevice> selectAll() {
        return null;
    }

    @Override
    public int insertDevice(blackdevice blackdevice) {
        return deviceMapper.insertDevice(blackdevice);
    }

    @Override
    public int updateDevice(blackdevice blackdevice) {
        return 0;
    }

    @Override
    public int deleteDeviceBySn(String sn) {
        return deviceMapper.deleteDeviceBySn(sn);
    }

    @Override
    public int deleteDeviceByIds(String[] ids) {
        return 0;
    }
}
