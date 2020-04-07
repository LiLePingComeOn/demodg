package com.example.demo.server.impl;

import com.example.demo.entity.temperature;
import com.example.demo.mapper.TemperatureMapper;
import com.example.demo.server.TemperatureServer;
import com.example.demo.util.GetUUIDString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TemperatureServerImpl implements TemperatureServer
{

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Override
    public int insertTemperature(temperature temperature) {
        temperature.setId(GetUUIDString.getUUID());
        int result = temperatureMapper.insertTemperature(temperature);

        return result;
    }

    @Override
    public int deleteTempByTemp(Map<String, Object> map) {
        return temperatureMapper.deleteTempByTemp(map);
    }

    @Override
    public List<Map<String,Object>> selectAllByDevice(Map<String, Object> map) {
        return temperatureMapper.selectAllByDevice(map);
    }

    @Override
    public List<Map<String,Object>> selectKtDeviceByBlack(String devicefk) {
        return temperatureMapper.selectKtDeviceByBlack(devicefk);
    }

    @Override
    public List<Map<String, Object>> selectReportByDevicefk(Map map) {
        return temperatureMapper.selectReportByDevicefk(map);
    }

    @Override
    public int updateTemp(Map<String,Object> map) {
        return temperatureMapper.updateTemp(map);
    }

    @Override
    public int updateTempStatusByCom(Map<String, Object> map) {
        return temperatureMapper.updateTempStatusByCom(map);
    }

    @Override
    public List<Map<String, Object>> selectDeviceList() {
        return temperatureMapper.selectDeviceList();
    }


}
