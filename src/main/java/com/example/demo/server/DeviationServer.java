package com.example.demo.server;

import com.example.demo.entity.blackdevice;
import com.example.demo.entity.deviation;
import com.example.demo.entity.ktdevice;

import java.util.List;

public interface DeviationServer {

    List<deviation> selectByName(String id);


    List<deviation> selectAll();

    public int insertDeviation(deviation deviation);


    public int updateDeviation(deviation deviation);


    public int deleteDeviationById(String id);

    public int deleteDeviationByIds(String[] ids);
}
