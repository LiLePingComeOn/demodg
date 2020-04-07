package com.example.demo.mapper;

import com.example.demo.entity.blackdevice;
import com.example.demo.entity.deviation;
import com.example.demo.entity.ktdevice;

import java.util.List;

public interface KtDeviceMapper {

    List<ktdevice> selectByName(String id);

    List<ktdevice> selectAll();

    public int insertKtDevice(ktdevice ktdevice);

    public int updateKtDevice(ktdevice ktdevice);

    public int deleteKtDeviceById(String id);

    public int deleteKtDeviceByIds(String[] ids);

}