package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.blackdevice;

public interface DeviceMapper {


    blackdevice selectByid(String bm);

    List<blackdevice> selectAll();

    public int insertDevice(blackdevice blackdevice);

    public int updateDevice(blackdevice blackdevice);

    public int deleteDeviceBySn(String sn);

    public int deleteDeviceByIds(String[] ids);

}