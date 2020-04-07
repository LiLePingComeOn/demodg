package com.example.demo.server;

import com.example.demo.entity.temperature;

import java.util.List;
import java.util.Map;

public interface TemperatureServer {


    public int insertTemperature(temperature temperature);

    public int deleteTempByTemp(Map<String,Object> map);

    List<Map<String,Object>> selectAllByDevice(Map<String,Object> map);

    List<Map<String,Object>> selectKtDeviceByBlack(String devicefk);

    List<Map<String,Object>> selectReportByDevicefk(Map map);

    public int updateTemp(Map<String,Object> map);

    public int updateTempStatusByCom(Map<String,Object> map);

    public List<Map<String,Object>>selectDeviceList();
}
