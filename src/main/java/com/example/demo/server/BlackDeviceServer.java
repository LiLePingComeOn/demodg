package com.example.demo.server;

import com.example.demo.entity.blackdevice;

import java.util.List;

public interface BlackDeviceServer {
    blackdevice selectByid(String bm);



    List<blackdevice> selectAll();

    public int insertDevice(blackdevice blackdevice);


    public int updateDevice(blackdevice blackdevice);


    public int deleteDeviceBySn(String sn);

    public int deleteDeviceByIds(String[] ids);
}
