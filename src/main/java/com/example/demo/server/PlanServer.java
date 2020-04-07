package com.example.demo.server;

import com.example.demo.entity.plan;

import java.util.List;

public interface PlanServer {

    plan selectByid(String id);


    List<plan> selectAll();

    public int insertPlan(plan plan);

    public int updatePlan(plan plan);


    public int deletePlanById(String id);


    public int deletePlanByIds(String[] ids);
}
