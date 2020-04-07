package com.example.demo.server.impl;

import com.example.demo.entity.blackplan;
import com.example.demo.mapper.BlackPlanMapper;
import com.example.demo.server.BlackPlanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlackPlanServerImpl implements BlackPlanServer {

    @Autowired
    private BlackPlanMapper blackPlanMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        return blackPlanMapper.selectAll();
    }

    @Override
    public int insertPlan(blackplan blackplan) {
        return blackPlanMapper.insertPlan(blackplan);
    }

    @Override
    public int updatePlan(blackplan blackplan) {
        return blackPlanMapper.updatePlan(blackplan);
    }

    @Override
    public int deletePlanById(String id) {
        return blackPlanMapper.deletePlanById(id);
    }

    @Override
    public blackplan selectOneById(String id) {
        return blackPlanMapper.selectOneById(id);
    }
}
