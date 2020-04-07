package com.example.demo.server;

import com.example.demo.entity.blackplan;

import java.util.List;
import java.util.Map;

public interface BlackPlanServer {
    public List<Map<String,Object>> selectAll();

    public int insertPlan(blackplan blackplan);

    public int updatePlan(blackplan blackplan);

    public int deletePlanById(String id);

    public blackplan selectOneById(String id);
}
