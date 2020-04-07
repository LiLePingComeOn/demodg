package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.OvlsResult;
import com.example.demo.entity.blackdevice;
import com.example.demo.entity.blackplan;
import com.example.demo.server.BlackDeviceServer;
import com.example.demo.server.BlackPlanServer;
import com.example.demo.util.GetUUIDString;
import com.sun.jna.Library;
import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
public class BlackDeviceController {

    @Autowired
    private BlackDeviceServer blackDeviceServer;

    @Autowired
    private BlackPlanServer blackPlanServer;

    @PostMapping("/sys/public")
    public OvlsResult PublicCode(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        JSONArray ja = jsonObject.getJSONArray("sn");
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM年dd ");
        String time = df.format(new Date());
        blackdevice blackdevice = new blackdevice();
        blackplan blackplan = blackPlanServer.selectOneById(jsonObject.getString("blackplanfk"));
        for (int i = 0; i < ja.size(); i++) {
            JSONObject ob = ja.getJSONObject(i);
            blackDeviceServer.deleteDeviceBySn(ob.getString("sn"));
            blackdevice.setId(GetUUIDString.getUUID());
            blackdevice.setBm(ob.getString("sn"));
            // System.out.println(jsonObject.getString("blackplanfk"));
            blackdevice.setModelname(blackplan.getModelname());
            blackdevice.setName(blackplan.getName());
            blackdevice.setModel(blackplan.getModel());
            blackdevice.setElection(blackplan.getElection());
            blackdevice.setPower(blackplan.getPower());
            blackdevice.setWeight(blackplan.getWeight());
            blackdevice.setSize(blackplan.getSize());
            blackdevice.setArea(blackplan.getArea());
            blackdevice.setPath(jsonObject.getString("blackplanfk"));
            blackdevice.setPid(jsonObject.getString("pid"));
            blackdevice.setShowtime(time);
            int check = blackDeviceServer.insertDevice(blackdevice);
            String qrCode = "http://support.zkisi.com/#/report?SN=" + jsonObject.getString("sn");
            TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
            TscLibDll.INSTANCE.setup("70", "40", "5", "15", "0", "2.2", "0");
            TscLibDll.INSTANCE.clearbuffer();
            String command = "QRCODE 190,230,L,10,A,0,M2,S3,\"" + qrCode + "\"";  //打印二维码的参数和内容
            TscLibDll.INSTANCE.sendcommand(command); //传送指令
            TscLibDll.INSTANCE.windowsfont(1250, 850, 70, 180, 2, 0, "黑体", "广东中科四创科技有限公司");
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1550, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1500, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1600, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(580, 680, 50, 180, 0, 0, "黑体", "扫一扫获取电子信息");
            TscLibDll.INSTANCE.windowsfont(1600, 680, 70, 180, 0, 0, "黑体", "名称：" + blackplan.getModelname());
            TscLibDll.INSTANCE.windowsfont(1600, 580, 70, 180, 0, 0, "黑体", "型号：" + blackplan.getModel());
            TscLibDll.INSTANCE.windowsfont(1600, 480, 70, 180, 0, 0, "黑体", "输入：" + blackplan.getElection());
            TscLibDll.INSTANCE.windowsfont(950, 480, 70, 180, 0, 0, "黑体", "功率：" + blackplan.getPower());
            TscLibDll.INSTANCE.windowsfont(1600, 380, 70, 180, 0, 0, "黑体", "尺寸：" + blackplan.getSize());
            TscLibDll.INSTANCE.windowsfont(1600, 280, 70, 180, 0, 0, "黑体", "重量：" + blackplan.getWeight());
            TscLibDll.INSTANCE.windowsfont(1050, 280, 70, 180, 0, 0, "黑体", "产地：" + blackplan.getArea());
            TscLibDll.INSTANCE.windowsfont(1600, 180, 70, 180, 0, 0, "黑体", "生产日期：" + time);
            TscLibDll.INSTANCE.windowsfont(700, 180, 70, 180, 0, 0, "黑体", "S/N：" + ob.getString("sn"));
            TscLibDll.INSTANCE.printlabel("1", "1");
            TscLibDll.INSTANCE.closeport();
        }
        return os;
    }

    @PostMapping("/sys/publicAgain")
    public OvlsResult PublicCodeAgain(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        JSONArray ja = jsonObject.getJSONArray("sn");
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 ");
        String time = df.format(new Date());
        for (int i = 0; i < ja.size(); i++) {
            JSONObject ob = ja.getJSONObject(i);
            blackDeviceServer.deleteDeviceBySn(ob.getString("sn"));
            blackdevice bk = new blackdevice();
            bk.setId(GetUUIDString.getUUID());
            bk.setBm(ob.getString("sn"));
            bk.setName(jsonObject.getString("name"));
            bk.setModelname(jsonObject.getString("modelname"));
            bk.setModel(jsonObject.getString("model"));
            bk.setElection(jsonObject.getString("election"));
            bk.setSize(jsonObject.getString("size"));
            bk.setPower(jsonObject.getString("power"));
            bk.setWeight(jsonObject.getString("weight"));
            bk.setArea(jsonObject.getString("area"));
            bk.setShowtime(jsonObject.getString("showtime"));
            blackDeviceServer.insertDevice(bk);
            String qrCode = "http://support.zkisi.com/#/report?SN=" + ob.getString("sn");
            TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
            TscLibDll.INSTANCE.setup("70", "40", "5", "15", "0", "2.2", "0");
            TscLibDll.INSTANCE.clearbuffer();
            String command = "QRCODE 190,230,L,10,A,0,M2,S3,\"" + qrCode + "\"";  //打印二维码的参数和内容
            TscLibDll.INSTANCE.sendcommand(command); //传送指令
            TscLibDll.INSTANCE.windowsfont(1250, 850, 70, 180, 2, 0, "黑体", jsonObject.getString("name"));
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1550, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1500, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(1600, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
            TscLibDll.INSTANCE.windowsfont(580, 680, 50, 180, 0, 0, "黑体", "扫一扫获取电子信息");
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 680, 70, 180, 0, 0, "黑体", "名称：" + jsonObject.getString("modelname"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 580, 70, 180, 0, 0, "黑体", "型号：" + jsonObject.getString("model"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 480, 70, 180, 0, 0, "黑体", "输入：" + jsonObject.getString("election"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(950, 480, 70, 180, 0, 0, "黑体", "功率：" + jsonObject.getString("power"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 380, 70, 180, 0, 0, "黑体", "尺寸：" + jsonObject.getString("size"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 280, 70, 180, 0, 0, "黑体", "重量：" + jsonObject.getString("weight"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1050, 280, 70, 180, 0, 0, "黑体", "产地：" + jsonObject.getString("area"));
            BlackDeviceController.TscLibDll.INSTANCE.windowsfont(1600, 180, 70, 180, 0, 0, "黑体", "生产日期：" + jsonObject.getString("showtime"));
            TscLibDll.INSTANCE.windowsfont(700, 180, 70, 180, 0, 0, "黑体", "S/N：" + ob.getString("sn"));
            TscLibDll.INSTANCE.printlabel("1", "1");
            TscLibDll.INSTANCE.closeport();
        }
        return os;
    }


    @PostMapping("/sys/publicBox")
    public OvlsResult PublicCodeBox(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        blackdevice bk = blackDeviceServer.selectByid(jsonObject.getString("sn"));
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd ");
        String time = df.format(new Date());
        String qrCode = "http://support.zkisi.com/#/report?SN=" + jsonObject.getString("sn");
        TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
        TscLibDll.INSTANCE.setup("70", "40", "5", "15", "0", "2.2", "0");
        TscLibDll.INSTANCE.clearbuffer();
        String command = "QRCODE 190,230,L,10,A,0,M2,S3,\"" + qrCode + "\"";  //打印二维码的参数和内容
        TscLibDll.INSTANCE.sendcommand(command); //传送指令
        TscLibDll.INSTANCE.windowsfont(1250, 850, 70, 180, 2, 0, "黑体", bk.getName());
        TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(1650, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(1550, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(1500, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(1600, 780, 70, 180, 2, 0, "黑体", "一一一一一一一一一一一一一一一一一一一一一");
        TscLibDll.INSTANCE.windowsfont(580, 680, 50, 180, 0, 0, "黑体", "扫一扫获取电子信息");
        TscLibDll.INSTANCE.windowsfont(1600, 680, 70, 180, 0, 0, "黑体", "名称：" + bk.getModelname());
        TscLibDll.INSTANCE.windowsfont(1600, 580, 70, 180, 0, 0, "黑体", "型号：" + bk.getModel());
        TscLibDll.INSTANCE.windowsfont(1600, 480, 70, 180, 0, 0, "黑体", "输入：" + bk.getElection());
        TscLibDll.INSTANCE.windowsfont(950, 480, 70, 180, 0, 0, "黑体", "功率：" + bk.getPower());
        TscLibDll.INSTANCE.windowsfont(1600, 380, 70, 180, 0, 0, "黑体", "尺寸：" + bk.getSize());
        TscLibDll.INSTANCE.windowsfont(1600, 280, 70, 180, 0, 0, "黑体", "重量：" + bk.getWeight());
        TscLibDll.INSTANCE.windowsfont(1050, 280, 70, 180, 0, 0, "黑体", "产地：" + bk.getArea());
        TscLibDll.INSTANCE.windowsfont(1600, 180, 70, 180, 0, 0, "黑体", "生产日期：" + bk.getShowtime());
        TscLibDll.INSTANCE.windowsfont(700, 180, 70, 180, 0, 0, "黑体", "S/N：" + jsonObject.getString("sn"));
        TscLibDll.INSTANCE.printlabel("1", "1");
        TscLibDll.INSTANCE.closeport();
        return os;
    }

    public interface TscLibDll extends Library {
        TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary("D:\\TSCLIB", TscLibDll.class);

        int about();

        int openport(String pirnterName);

        int closeport();

        int sendcommand(String printerCommand);

        int setup(String width, String height, String speed, String density, String sensor, String vertical, String offset);

        int downloadpcx(String filename, String image_name);

        int barcode(String x, String y, String type, String height, String readable, String rotation, String narrow, String wide, String code);

        int printerfont(String x, String y, String fonttype, String rotation, String xmul, String ymul, String text);

        int clearbuffer();

        int printlabel(String set, String copy);

        int formfeed();

        int nobackfeed();

        int windowsfont(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);
    }

    @PostMapping("/sys/publishIntel")
    public OvlsResult publishIntel(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
                if(jsonObject.getString("identification").equals("1")){
                    publishSmallC(jsonObject);
                }else if(jsonObject.getString("identification").equals("3")){
                    publishSmallE(jsonObject);
                }
                return  os;
    }

    @PostMapping("/sys/publishIntelBig")
    public OvlsResult publishIntelBig(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        blackdevice bk = blackDeviceServer.selectByid(jsonObject.getString("sn"));

        if(bk.getIdentification().equals("1")){
            publicBigC(jsonObject);
        }else if(bk.getIdentification().equals("3")){
            publicBigE(jsonObject);
        }
        return os ;
    }

    public void publicBigC(JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        blackdevice bk = blackDeviceServer.selectByid(jsonObject.getString("sn"));
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd ");
        String time = df.format(new Date());
        TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
        TscLibDll.INSTANCE.setup("60","40","5","15","0","2.2","0");
        TscLibDll.INSTANCE.clearbuffer();
        TscLibDll.INSTANCE.windowsfont(1350, 940, 120, 180, 2, 0, "思源黑体 CN 半细体", bk.getModelname());
        TscLibDll.INSTANCE. barcode("200","120","128","200","0","0","8","4",jsonObject.getString("sn"));
        TscLibDll.INSTANCE.windowsfont(1350, 820, 90, 180, 2, 0, "思源黑体 CN 半细体", "型号："+bk.getModel());
        TscLibDll.INSTANCE.windowsfont(1350, 720, 90, 180, 2, 0, "思源黑体 CN 半细体", "温度："+bk.getPower());
        TscLibDll.INSTANCE.windowsfont(1350, 620, 90, 180, 2, 0, "思源黑体 CN 半细体", "辐射面："+bk.getSize());
        TscLibDll.INSTANCE.windowsfont(1350, 520, 90, 180, 2, 0, "思源黑体 CN 半细体", "电源："+bk.getElection());
        TscLibDll.INSTANCE.windowsfont(1350, 420, 90, 180, 2, 0, "思源黑体 CN 半细体", "料号："+bk.getArea());
        TscLibDll.INSTANCE.windowsfont(1350, 120, 70, 180, 2, 0, "思源黑体 CN 半细体", "S/N："+jsonObject.getString("sn"));
        TscLibDll.INSTANCE.printlabel("1", "1");
        TscLibDll.INSTANCE.closeport();

    }

    public void publicBigE(JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        blackdevice bk = blackDeviceServer.selectByid(jsonObject.getString("sn"));
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd ");
        String time = df.format(new Date());
        TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
        TscLibDll.INSTANCE.setup("60","40","5","15","0","2.2","0");
        TscLibDll.INSTANCE.clearbuffer();
        TscLibDll.INSTANCE.windowsfont(1400, 940, 120, 180, 2, 0, "思源黑体 CN 半细体", bk.getModelname());
        TscLibDll.INSTANCE. barcode("200","120","128","200","0","0","8","4",jsonObject.getString("sn"));
        TscLibDll.INSTANCE.windowsfont(1400, 820, 80, 180, 2, 0, "思源黑体 CN 半细体", "Model："+bk.getModel());
        TscLibDll.INSTANCE.windowsfont(1400, 720, 80, 180, 2, 0, "思源黑体 CN 半细体", "Temperature："+bk.getPower());
        TscLibDll.INSTANCE.windowsfont(1400, 620, 80, 180, 2, 0, "思源黑体 CN 半细体", "Radiation Surface："+bk.getSize());
        TscLibDll.INSTANCE.windowsfont(1400, 520, 80, 180, 2, 0, "思源黑体 CN 半细体", "Power Supply："+bk.getElection());
        TscLibDll.INSTANCE.windowsfont(1400, 420, 80, 180, 2, 0, "思源黑体 CN 半细体", "Material Number："+bk.getArea());
        TscLibDll.INSTANCE.windowsfont(1400, 120, 70, 180, 2, 0, "思源黑体 CN 半细体", "S/N："+jsonObject.getString("sn"));
        TscLibDll.INSTANCE.printlabel("1", "1");
        TscLibDll.INSTANCE.closeport();
        TscLibDll.INSTANCE.closeport();

    }

    public void publishSmallC(JSONObject jsonObject) {
        JSONArray ja = jsonObject.getJSONArray("sn");
        // JSONArray jb = jsonObject.getJSONArray("sns")
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        blackplan blackplan = blackPlanServer.selectOneById(jsonObject.getString("blackplanfk"));
        // String qrCode = "http://support.zkisi.com/#/report?SN=102200314001A";
      //  for (int i = 0; i < ja.size() - 2; i += 2) {
            for(int i= 0; i<ja.size(); i++){
            JSONObject ob = ja.getJSONObject(i);
          //  JSONObject jb = ja.getJSONObject((i + 1));
                blackDeviceServer.deleteDeviceBySn(ob.getString("sn"));
            blackdevice bk = new blackdevice();
            bk.setId(GetUUIDString.getUUID());
            bk.setBm(ob.getString("sn"));
            bk.setModelname(blackplan.getModelname());
            bk.setModel(blackplan.getModel());
            bk.setElection(blackplan.getElection());
            bk.setSize(blackplan.getSize());
            bk.setArea(blackplan.getArea());
            bk.setIdentification(blackplan.getIdentification());
            blackDeviceServer.insertDevice(bk);
//            bk.setId(GetUUIDString.getUUID());
//            bk.setBm(jb.getString("sn"));
//            bk.setModelname(blackplan.getModelname());
//            bk.setModel(blackplan.getModel());
//            bk.setElection(blackplan.getElection());
//            bk.setSize(blackplan.getSize());
//            bk.setArea(blackplan.getArea());
//            bk.setIdentification(blackplan.getIdentification());
//            blackDeviceServer.insertDevice(bk);
                TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
            TscLibDll.INSTANCE.setup("80", "26", "5", "15", "0", "2.2", "0");
            TscLibDll.INSTANCE.clearbuffer();
            TscLibDll.INSTANCE.windowsfont(1780, 600, 70, 180, 2, 0, "思源黑体 CN 半细体", blackplan.getModelname());
            TscLibDll.INSTANCE.barcode("1200", "120", "128", "100", "0", "0", "4", "2", ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(1780, 500, 50, 180, 2, 0, "思源黑体 CN 半细体", "型号：" + blackplan.getModel());
            TscLibDll.INSTANCE.windowsfont(1780, 450, 50, 180, 2, 0, "思源黑体 CN 半细体", "温度：" + blackplan.getPower());
            TscLibDll.INSTANCE.windowsfont(1780, 400, 50, 180, 2, 0, "思源黑体 CN 半细体", "辐射面：" + blackplan.getSize());
            TscLibDll.INSTANCE.windowsfont(1780, 350, 50, 180, 2, 0, "思源黑体 CN 半细体", "电源：" + blackplan.getElection());
            TscLibDll.INSTANCE.windowsfont(1780, 300, 50, 180, 2, 0, "思源黑体 CN 半细体", "料号：" + blackplan.getArea());
            TscLibDll.INSTANCE.windowsfont(1780, 90, 40, 180, 2, 0, "思源黑体 CN 半细体", "S/N：" + ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(880, 600, 70, 180, 2, 0, "思源黑体 CN 半细体", blackplan.getModelname());
            TscLibDll.INSTANCE. barcode("300","120","128","100","0","0","4","2",ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(880, 500, 50, 180, 2, 0, "思源黑体 CN 半细体", "型号：" + blackplan.getModel());
            TscLibDll.INSTANCE.windowsfont(880, 450, 50, 180, 2, 0, "思源黑体 CN 半细体", "温度：" + blackplan.getPower());
            TscLibDll.INSTANCE.windowsfont(880, 400, 50, 180, 2, 0, "思源黑体 CN 半细体", "辐射面：" + blackplan.getSize());
            TscLibDll.INSTANCE.windowsfont(880, 350, 50, 180, 2, 0, "思源黑体 CN 半细体", "电源：" + blackplan.getElection());
            TscLibDll.INSTANCE.windowsfont(880, 300, 50, 180, 2, 0, "思源黑体 CN 半细体", "料号：" + blackplan.getArea());
            TscLibDll.INSTANCE.windowsfont(880, 90, 40, 180, 2, 0, "思源黑体 CN 半细体", "S/N：" + ob.getString("sn"));
            TscLibDll.INSTANCE.printlabel("1", "1");
            TscLibDll.INSTANCE.closeport();
        }
    }

    public void publishSmallE(JSONObject jsonObject) {
        JSONArray ja = jsonObject.getJSONArray("sn");
        // JSONArray jb = jsonObject.getJSONArray("sns")
        System.setProperty("jna.encoding", "GBK");// 支持中文
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        blackplan blackplan = blackPlanServer.selectOneById(jsonObject.getString("blackplanfk"));
//        for (int i = 0; i <= ja.size() - 2; i += 2) {
        for(int i= 0; i<ja.size(); i++){
            JSONObject ob = ja.getJSONObject(i);
          //  JSONObject jb = ja.getJSONObject((i + 1));
            blackDeviceServer.deleteDeviceBySn(ob.getString("sn"));
            blackdevice bk = new blackdevice();
            bk.setId(GetUUIDString.getUUID());
            bk.setBm(ob.getString("sn"));
            bk.setModelname(blackplan.getModelname());
            bk.setModel(blackplan.getModel());
            bk.setElection(blackplan.getElection());
            bk.setSize(blackplan.getSize());
            bk.setArea(blackplan.getArea());
            bk.setPower(blackplan.getPower());
            bk.setIdentification(blackplan.getIdentification());
            blackDeviceServer.insertDevice(bk);
//            bk.setId(GetUUIDString.getUUID());
//            bk.setBm(jb.getString("sn"));
//            bk.setModelname(blackplan.getModelname());
//            bk.setModel(blackplan.getModel());
//            bk.setElection(blackplan.getElection());
//            bk.setSize(blackplan.getSize());
//            bk.setPower(blackplan.getPower());
//            bk.setArea(blackplan.getArea());
//            bk.setIdentification(blackplan.getIdentification());
//            blackDeviceServer.insertDevice(bk);
            TscLibDll.INSTANCE.openport(jsonObject.getString("publishName"));
            TscLibDll.INSTANCE.setup("80", "26", "5", "15", "0", "2.2", "0");
            TscLibDll.INSTANCE.clearbuffer();
            TscLibDll.INSTANCE.windowsfont(1780, 600, 70, 180, 2, 0, "思源黑体 CN 半细体", blackplan.getModelname());
            TscLibDll.INSTANCE.barcode("1200", "120", "128", "100", "0", "0", "4", "2",  ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(1780, 500, 50, 180, 2, 0, "思源黑体 CN 半细体", "Model："+ blackplan.getModel());
            TscLibDll.INSTANCE.windowsfont(1780, 450, 50, 180, 2, 0, "思源黑体 CN 半细体", "Temperature："+ blackplan.getPower());
            TscLibDll.INSTANCE.windowsfont(1780, 400, 50, 180, 2, 0, "思源黑体 CN 半细体", "Radiation Surface："+ blackplan.getSize());
            TscLibDll.INSTANCE.windowsfont(1780, 350, 50, 180, 2, 0, "思源黑体 CN 半细体", "Power Supply："+ blackplan.getElection());
            TscLibDll.INSTANCE.windowsfont(1780, 300, 50, 180, 2, 0, "思源黑体 CN 半细体", "Material Number："+ blackplan.getArea());
            TscLibDll.INSTANCE.windowsfont(1780, 90, 40, 180, 2, 0, "思源黑体 CN 半细体", "S/N："+ ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(900, 600, 70, 180, 2, 0, "思源黑体 CN 半细体", blackplan.getModelname());
            TscLibDll.INSTANCE. barcode("300","120","128","100","0","0","4","2",ob.getString("sn"));
            TscLibDll.INSTANCE.windowsfont(900, 500, 50, 180, 2, 0, "思源黑体 CN 半细体", "Model："+ blackplan.getModel());
            TscLibDll.INSTANCE.windowsfont(900, 450, 50, 180, 2, 0, "思源黑体 CN 半细体", "Temperature："+ blackplan.getPower());
            TscLibDll.INSTANCE.windowsfont(900, 400, 50, 180, 2, 0, "思源黑体 CN 半细体", "Radiation Surface："+ blackplan.getSize());
            TscLibDll.INSTANCE.windowsfont(900, 350, 50, 180, 2, 0, "思源黑体 CN 半细体", "Power Supply："+ blackplan.getElection());
            TscLibDll.INSTANCE.windowsfont(900, 300, 50, 180, 2, 0, "思源黑体 CN 半细体", "Material Number："+ blackplan.getArea());
            TscLibDll.INSTANCE.windowsfont(900, 90, 40, 180, 2, 0, "思源黑体 CN 半细体", "S/N："+ob.getString("sn"));
            TscLibDll.INSTANCE.printlabel("1", "1");
            TscLibDll.INSTANCE.closeport();
        }

    }

    @GetMapping("/sys/publishList")
    public OvlsResult testPublishList() {
        OvlsResult os = new OvlsResult();
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
        PrintService[] service = PrintServiceLookup.lookupPrintServices(flavor, pras);
        String [] list = new String[service.length] ;
        for (int i = 0; i < service.length; i++) {
            list[i] = service[i].getName();
        }
        os.setStatus(1);
        os.setData(list);
        return os;
    }
}