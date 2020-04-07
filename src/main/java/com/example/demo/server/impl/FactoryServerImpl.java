package com.example.demo.server.impl;

import com.example.demo.entity.factory;
import com.example.demo.mapper.FactoryMapper;
import com.example.demo.server.FactoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FactoryServerImpl implements FactoryServer {

    @Autowired
    private FactoryMapper factoryMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        return factoryMapper.selectAll();
    }

    @Override
    public int insertFactory(factory factory) {
        return factoryMapper.insertFactory(factory);
    }

    @Override
    public int deleteFactoryById(String id) {
        return factoryMapper.deleteFactoryById(id);
    }

    @Override
    public int deleteFactoryByIds(String[] ids) {
        return factoryMapper.deleteFactoryByIds(ids);
    }

    @Override
    public int updateFactory(factory factory) {
        return factoryMapper.updateFactory(factory);
    }
}
