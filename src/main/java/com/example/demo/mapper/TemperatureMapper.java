package com.example.demo.mapper;


import com.example.demo.entity.temperature;
import javafx.beans.binding.ObjectExpression;

import java.util.List;
import java.util.Map;

public interface TemperatureMapper {


    List<Map<String,Object>> selectAllByDevice(Map<String,Object> map);

    public int insertTemperature(temperature temperature);

    List<Map<String,Object>> selectKtDeviceByBlack(String devicefk);

    List<Map<String,Object>> selectReportByDevicefk(Map map);

    public int updateTemp(Map<String,Object> map);

    public int updateTemperature(temperature temperature);


    public int deleteTempByTemp(Map<String,Object> map);


    public int deleteTemperatureByIds(String[] ids);


    public int updateTempStatusByCom(Map<String,Object> map);

    public List<Map<String,Object>>selectDeviceList();
}
