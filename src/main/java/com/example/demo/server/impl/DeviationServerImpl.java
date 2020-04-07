package com.example.demo.server.impl;

import com.example.demo.entity.deviation;
import com.example.demo.mapper.DeviationMapper;
import com.example.demo.server.DeviationServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviationServerImpl implements DeviationServer {

    @Autowired
    private DeviationMapper deviationMapper;
    @Override
    public List<deviation> selectByName(String id) {
        return deviationMapper.selectByName(id);
    }

    @Override
    public List<deviation> selectAll() {
        return deviationMapper.selectAll();
    }

    @Override
    public int insertDeviation(deviation deviation) {
        return deviationMapper.insertDeviation(deviation);
    }

    @Override
    public int updateDeviation(deviation deviation) {
        return 0;
    }

    @Override
    public int deleteDeviationById(String id) {
        return 0;
    }

    @Override
    public int deleteDeviationByIds(String[] ids) {
        return 0;
    }
}
