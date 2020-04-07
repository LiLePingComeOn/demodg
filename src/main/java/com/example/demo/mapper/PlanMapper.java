package com.example.demo.mapper;


import com.example.demo.entity.plan;

import java.util.List;

public interface PlanMapper {


    plan selectByid(String id);


    List<plan> selectAll();

    public int insertPlan(plan plan);

    public int updatePlan(plan plan);

    public int deletePlanById(String id);


    public int deletePlanByIds(String[] ids);
}
