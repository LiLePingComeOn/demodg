package com.example.demo.server.impl;

import com.example.demo.entity.report;
import com.example.demo.mapper.ReportMapper;
import com.example.demo.server.ReportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServerImpl implements ReportServer {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Map<String,Object>> selectAllBySn(Map<String,Object> map) {
        return reportMapper.selectAllBySn(map);
    }

    @Override
    public int insertReport(report report) {
        return reportMapper.insertReport(report);
    }

    @Override
    public int updateReportStatus(Map<String, Object> map) {
        return reportMapper.updateReportStatus(map);
    }

    @Override
    public int deleteReportByCom(Map<String, Object> map) {
        return reportMapper.deleteReportByCom(map);
    }

    @Override
    public int updateReportStatusByCom(Map<String, Object> map) {
        return reportMapper.updateReportStatusByCom(map);
    }

    @Override
    public List<Map<String,Object>>selectKtDeviceByBlack(String devicefk) {
        return reportMapper.selectKtDeviceByBlack(devicefk);
    }

    @Override
    public int updateReport(report re) {
        return reportMapper.updateReport(re);
    }
}
