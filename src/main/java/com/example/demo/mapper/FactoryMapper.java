package com.example.demo.mapper;

import com.example.demo.entity.factory;
import com.example.demo.entity.report;

import java.util.List;
import java.util.Map;

public interface FactoryMapper {

    List<Map<String,Object>> selectAll();

    public int insertFactory(factory factory);

    public int deleteFactoryById(String id);

    public int deleteFactoryByIds(String [] ids);

    public int updateFactory(factory factory);

}
