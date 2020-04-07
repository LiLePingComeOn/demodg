package com.example.demo.server;

import com.example.demo.entity.report;

import java.util.List;
import java.util.Map;

public interface ReportServer {


    List<Map<String,Object>> selectAllBySn(Map<String,Object> map);

    public int insertReport(report report);

    public int updateReportStatus(Map<String,Object> map);


    public int deleteReportByCom(Map<String,Object> map);

    public int updateReportStatusByCom(Map<String,Object> map);

    public List<Map<String,Object>>selectKtDeviceByBlack(String devicefk);

    public int updateReport (report re);
}
