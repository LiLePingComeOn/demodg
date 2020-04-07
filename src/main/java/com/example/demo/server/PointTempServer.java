package com.example.demo.server;

import com.example.demo.entity.pointtemp;

import java.util.List;

public interface PointTempServer {

    List<pointtemp> selectByPlan(String id);

    List<pointtemp> selectAll();

    public int insertpointtemp(pointtemp pointtemp);

    public int updatepointtemp(pointtemp pointtemp);

    public int deletepointtempById(String id);

    public int deletepointtempByIds(String[] ids);

    public int deletepointtempByPlanId(String planid);
}
