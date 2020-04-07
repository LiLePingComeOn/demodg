package com.example.demo.server.impl;

import com.example.demo.entity.pointtemp;
import com.example.demo.mapper.PointTempMapper;
import com.example.demo.server.PointTempServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointTempServerImpl implements PointTempServer {
    @Autowired
    private PointTempMapper pointTempMapper;

    @Override
    public List<pointtemp> selectByPlan(String id) {
        return pointTempMapper.selectByPlan(id);
    }

    @Override
    public List<pointtemp> selectAll() {
        return pointTempMapper.selectAll();
    }

    @Override
    public int insertpointtemp(pointtemp pointtemp) {
        return pointTempMapper.insertpointtemp(pointtemp);
    }

    @Override
    public int updatepointtemp(pointtemp pointtemp) {
        return 0;
    }

    @Override
    public int deletepointtempById(String id) {
        return 0;
    }

    @Override
    public int deletepointtempByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deletepointtempByPlanId(String planid) {
        return pointTempMapper.deletepointtempByPlanId(planid);
    }
}
