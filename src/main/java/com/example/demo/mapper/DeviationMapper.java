package com.example.demo.mapper;

import com.example.demo.entity.blackdevice;
import com.example.demo.entity.deviation;
import com.example.demo.entity.ktdevice;

import java.util.List;

public interface DeviationMapper {

    /**
     * 获取校准点位
     * @param id
     * @return
     */
     List<deviation> selectByName(String id);

    List<deviation> selectAll();

    public int insertDeviation(deviation deviation);

    public int updateDeviation(deviation ktdevice);

    public int deleteDeviationById(String id);

    public int deleteDeviationByIds(String[] ids);

}