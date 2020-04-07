package com.example.demo.server.impl;

import com.example.demo.entity.blackdevice;
import com.example.demo.entity.deviation;
import com.example.demo.entity.ktdevice;
import com.example.demo.mapper.KtDeviceMapper;
import com.example.demo.server.KtDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtDeviceServerImpl implements KtDeviceServer {
    @Autowired
    private KtDeviceMapper ktDeviceMapper;
    @Override
    public List<ktdevice> selectByName(String id) {
        return ktDeviceMapper.selectByName(id);
    }

    @Override
    public List<ktdevice> selectAll() {
        return ktDeviceMapper.selectAll();
    }

    @Override
    public int insertKtDevice(ktdevice ktdevice) {
        return ktDeviceMapper.insertKtDevice(ktdevice);
    }

    @Override
    public int updateKtDevice(ktdevice ktdevice) {
        return 0;
    }

    @Override
    public int deleteKtDeviceById(String id) {
        return 0;
    }

    @Override
    public int deleteKtDeviceByIds(String[] ids) {
        return 0;
    }
}
