package com.example.demo.server.impl;

import com.example.demo.entity.plan;
import com.example.demo.mapper.PlanMapper;
import com.example.demo.server.PlanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServerImpl implements PlanServer {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public plan selectByid(String id) {
        return null;
    }

    @Override
    public List<plan> selectAll() {
        return planMapper.selectAll();
    }

    @Override
    public int insertPlan(plan plan) {
        return planMapper.insertPlan(plan);
    }

    @Override
    public int updatePlan(plan plan) {
        return 0;
    }

    @Override
    public int deletePlanById(String id) {
        return planMapper.deletePlanById(id);
    }

    @Override
    public int deletePlanByIds(String[] ids) {
        return 0;
    }
}
