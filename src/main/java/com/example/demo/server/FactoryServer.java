package com.example.demo.server;

import com.example.demo.entity.factory;

import java.util.List;
import java.util.Map;

public interface FactoryServer {

    List<Map<String,Object>> selectAll();

    public int insertFactory(factory factory);

    public int deleteFactoryById(String id);

    public int deleteFactoryByIds(String [] ids);

    public int updateFactory(factory factory);
}
