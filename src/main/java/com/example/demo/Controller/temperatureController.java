package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.server.*;
import com.example.demo.util.GetUUIDString;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.util.Md5Util.passCode;

@RestController
@CrossOrigin
public class temperatureController extends Thread{

    private Logger logger = LoggerFactory.getLogger(temperatureController.class);

    // 串口列表
    private List<String> mCommList = null;
    // 串口对象
    private SerialPort mSerialport;

    @Autowired
    private TemperatureServer temperatureServer;

    @Autowired
    private KtDeviceServer ktDeviceServer;

    @Autowired
    private DeviationServer deviationServer;

    @Autowired
    private ReportServer reportServer;

    private boolean flag1 =  true;



    @Override
    public void run() {
        try {
            while (flag1) {
                theWebsocket theWebsocket = new theWebsocket();
                JSONObject jsonObject = new JSONObject();
               // SerialPortManager.sendToPort(mSerialport, instruction.Check_Temperature.getBytes());//从设备中获取温度
               // String data = new String(SerialPortManager.readFromPort(mSerialport));
                String data = "34.34 ";
                jsonObject.put("temp", data);
                jsonObject.put("date", new Date());
                logger.info("从kt15中获取的温度:" + data);
                Thread.sleep(1000);
                //logger.info(jsonParam.getString("devicefk"));
                logger.info(new Date().toString());
                theWebsocket.sendInfo(jsonObject.toString());//往websocket推送数据
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }



    @PostMapping("/temp/startOrStopWork")
    public OvlsResult startOrStopWork(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject.getString("flag"));
        OvlsResult os = new OvlsResult();
        temperatureController td = new temperatureController();
        if(jsonObject.getString("flag").equals("true")){
                    td.start();
                    os.setStatus(1);
                    os.setMsg("开始工作");
        }else if(jsonObject.getString("flag").equals("false")){


                td.currentThread().interrupt();
                os.setStatus(0);
                os.setMsg("线程关闭");

        }
        return os;
    }

    /**
     * 开启串口
     * @param jsonParam
     * @return
     */
    @PostMapping ("/temp/openAStopTemp")
    public OvlsResult OpenTemp(@RequestBody JSONObject jsonParam){
        OvlsResult os = new OvlsResult();
        String port = jsonParam.getString("port");
        String burt = jsonParam.getString("burt");
        String flag = jsonParam.getString("flag");
        try {
            if(flag.equals("false")) {
                SerialPortManager.closePort(mSerialport);
                if(mSerialport == null) {
                    logger.info("串口已关闭");
                    os.setMsg("串口已关闭");
                    os.setStatus(0);
                }
            }else{
                mSerialport = SerialPortManager.openPort(port, Integer.parseInt(burt));
                if(mSerialport != null){
                    logger.info("串口已打开" );
                    SerialPortManager.sendToPort(mSerialport,instruction.Check_KtInfo.getBytes());
                    byte[] data = SerialPortManager.readFromPort(mSerialport);
                    SerialPortManager.sendToPort(mSerialport,instruction.Emissing_Info.getBytes());
                    byte[] emssing = SerialPortManager.readFromPort(mSerialport);
                    String arg = "";
                    Pattern p = Pattern.compile("\\d{5}");
                    TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
                    TimeZone.setDefault(time);
                    Matcher m = p.matcher(new String(data));
                    while(m.find()) {
                        System.out.println(m.group());
                        arg = m.group();
                    }
                    System.out.println(arg);
                    List<ktdevice> list = ktDeviceServer.selectByName(arg);
                    for(int i = 0; i<list.size(); i++){
                        ktdevice  ktdevice = list.get(i);
                        ktdevice.setEmissing(new String(emssing));
                        List<deviation> list1 = deviationServer.selectByName(ktdevice.getId());
                        ktdevice.setDeviations(list1);
                    }
                    os.setData(list);
                    os.setStatus(1);
                    os.setMsg("开启串口");
                }
            }

        }catch (PortInUseException e){
            os.setStatus(2);
            logger.info("串口已被占用");
            os.setMsg("串口已被占用");
        }
        return os;
    }

    /**
     * 关闭测试（不可用）
     * @param jsonObject
     * @return
     */
    @PostMapping("/temp/stoptest")
    public String StopTest(@RequestBody JSONObject jsonObject){
        if (jsonObject.getString("flag") == "false"){
            flag1 = false;
        }
        return "关闭";
    }

    /**
     * 获取当前本机的可用串口
     * @return
     */
    @GetMapping("/temp/getPortList")
    public OvlsResult getPortList(){
        mCommList = SerialPortManager.findPorts();
        OvlsResult os = new OvlsResult();
        if(mCommList.size()<0){
            os.setStatus(0);
            os.setMsg("没有可用串口");
        }else{
            os.setStatus(1);
            os.setMsg("查询成功");
            os.setData(mCommList);
        }
        return os;
    }

    /**
     * 获取固定波特率
     * @return
     */
    @GetMapping("/temp/getPortBurt")
    public OvlsResult getPortBurt(){
        OvlsResult os = new OvlsResult();
        List<String> list = new ArrayList<>();
        list.add(0,"9600");
        list.add(1,"19200");
        list.add(2,"38400");
        list.add(3,"57600");
        list.add(4,"115200");
        os.setMsg("查询成功");
        os.setStatus(1);
        os.setData(list);
        return os;
    }

    /**
     * 开启kt设备激光
     * @param jsonParam
     * @return
     */
    @PostMapping("/temp/openAStopLaser")
    public OvlsResult openAStopLaser(@RequestBody JSONObject jsonParam){
        String flag = jsonParam.getString("flag");
        OvlsResult os = new OvlsResult();
        if (mSerialport != null) {
            if (flag.equals("true")) {
                SerialPortManager.sendToPort( mSerialport,instruction.Open_Laser.getBytes());
                os.setMsg("开启激光");
                os.setStatus(1);
            }else{
                SerialPortManager.sendToPort(mSerialport,instruction.Close_Laser.getBytes());
                os.setMsg("关闭激光");
                os.setStatus(0);
            }
        }else{
            os.setStatus(2);
            os.setMsg("请先连接串口");
        }
        return  os;
    }

    /**
     * 开始测试
     * @param jsonParam
     * @return
     */
    @PostMapping("/temp/startTest")
    public OvlsResult startTest(@RequestBody JSONObject jsonParam){
        temperature temperature = new temperature();
        tempWebsocket tempWebsocket = new tempWebsocket();
        OvlsResult os = new OvlsResult();
        JSONObject jsonObject = new JSONObject();
        //flag1 = true;
        for(int i = 1; i <= Integer.parseInt(jsonParam.getString("testcount")); ++i) {
                try {
                   SerialPortManager.sendToPort(mSerialport, instruction.Check_Temperature.getBytes());//从设备中获取温度
                   String data = new String(SerialPortManager.readFromPort(mSerialport));
                    jsonObject.put("temp", data);
                    jsonObject.put("date", new Date());
                    logger.info("从kt15中获取的温度:"+data );
                    logger.info(jsonParam.getString("devicefk"));
                    logger.info(new Date().toString());
                    tempWebsocket.sendInfo(jsonObject.toString());//往websocket推送数据
                    Thread.sleep(Long.parseLong(jsonParam.getString("testinterval")));
                    if(!flag1){
                        break;
                    }
                } catch (IOException e) {
                    logger.info("io流出现异常："+e);
                } catch (InterruptedException p) {
                    logger.info("串口已被占用");
                    flag1 = false;
                    break;
                }
            }
        os.setMsg("本轮测试完成");
        os.setStatus(1);
        return os;
    }

    /**
     * 测试检测
     */
    @GetMapping("/testtest")
    public void testtest(){
        try {
            byte[] data = null;
            tempWebsocket tempWebsocket = new tempWebsocket();
            SerialPort mSerialport = SerialPortManager.openPort("COM4", 9600);
            mSerialport.setRTS(true);
            for(int i=1; i<10; i++) {
                SerialPortManager.sendToPort(mSerialport, instruction.Check_Temperature.getBytes());
                data = SerialPortManager.readFromPort(mSerialport);
                System.out.println(data.length);
                // 以字符串的形式接收数据

                System.out.println(new String(data) + "\r\n");
                tempWebsocket.sendInfo(new String(data));
                Thread.sleep(1000);
            }
            SerialPortManager.closePort(mSerialport);
        }catch (PortInUseException e){}catch (IOException e){

        }catch (InterruptedException p){}
    }

    /**
     * 根据温度删除某设备的数据
     * @param jsonObject
     * @return
     */
    @PostMapping("/temp/deleteData")
    public OvlsResult deleteData(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        OvlsResult os = new OvlsResult();
        map.put("devicefk",jsonObject.get("devicefk"));
        map.put("comtemperature",jsonObject.get("temperature"));
        int i = temperatureServer.deleteTempByTemp(map);
        int k = reportServer.deleteReportByCom(map);
        if(i >= 1){
            os.setStatus(1);
            os.setMsg("数据已删除");
        }else{
            os.setStatus(0);
            os.setMsg("删除失败");
        }
        return os;
    }

    /**
     * 根据固定测试温度查询某设备温度
     * @param jsonObject
     * @return
     */
    @PostMapping("/temp/getAllByTemp")
    public OvlsResult getAllByTemp(@RequestBody JSONObject jsonObject){
        Map<String, Object> map = new HashMap<>();
        OvlsResult os = new OvlsResult();
        map.put("devicefk",jsonObject.get("devicefk"));
        map.put("comtemperature",jsonObject.get("temperature"));
        List<Map<String,Object>> list = temperatureServer.selectAllByDevice(map);
        if(list.size()>0){
            os.setStatus(1);
            os.setMsg("查询成功");
            os.setData(list);
        }else{
            os.setStatus(0);
            os.setMsg("查询失败");
        }
        return  os;
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/Kt/insertDevice")
    public OvlsResult insertKtDevice(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        JSONArray ja = jsonObject.getJSONArray("domains");
        ktdevice kd = new ktdevice();
        String KtdeviceId = GetUUIDString.getUUID();
        deviation dev = new deviation();
        for(int i=0; i<ja.size(); i++){
            JSONObject jo = JSON.parseObject(ja.getString(i));
            dev.setId(GetUUIDString.getUUID());
            dev.setBlacktemp(jo.getString("value"));
            dev.setTestshow(jo.getString("kt_15"));
            TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
            TimeZone.setDefault(time);
            dev.setCretime(new Date());
            dev.setDevicename(KtdeviceId);
            int check =  deviationServer.insertDeviation(dev);
            if(check ==1){
                continue;
            }
        }
        kd.setId(KtdeviceId);
        kd.setEmissing(jsonObject.getString("GL"));
        if(mSerialport != null) {
            SerialPortManager.sendToPort(mSerialport, (instruction.Set_Emissing + jsonObject.getString("GL")).getBytes());
        }
        kd.setName(jsonObject.getString("name"));
        kd.setSpacing(jsonObject.getString("JJ"));
        int Check = ktDeviceServer.insertKtDevice(kd);
        if(Check ==1){
            os.setStatus(1);
            os.setMsg("数据保存成功");
        }else{
            os.setStatus(0);
            os.setMsg("数据保存失败");
        }
        return os;
    }

    /**
     * 获取kt信息
     * @return
     */
    @GetMapping("/ktDevice/getKtName")
    public OvlsResult getKtName(){
        OvlsResult os = new OvlsResult();
        if(mSerialport!= null){
            SerialPortManager.sendToPort(mSerialport,instruction.Check_KtInfo.getBytes());
            byte[] bytes = SerialPortManager.readFromPort(mSerialport);
            os.setStatus(0);
            os.setMsg("查询成功");
            os.setData(new String(bytes));
        }else{
            os.setStatus(1);
            os.setMsg("请先连接串口");
        }
        return os;
    }

    /**
     * 获取设备报告
     * @param jsonObject
     * @return
     */
    @PostMapping("/sys/report")
    public OvlsResult getReport(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        try {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = new HashMap<>();
            List<Map<String, Object>> parm = reportServer.selectKtDeviceByBlack(jsonObject.getString("devicefk"));
            String parmName = "" ;
            for(int i =0; i<parm.size(); i++){
                String parmCheck = parm.get(i).get("ktdevicefk").toString();
                if(parmCheck != null || parmCheck != ""){
                    parmName = parmCheck;
                }
            }
            if (parm != null) {
                logger.info(parm.toString());
                logger.info(parmName);
                List<deviation> list = deviationServer.selectByName(parmName);
               logger.info(list.toString());
                map1.put("devicefk",jsonObject.getString("devicefk"));
                map1.put("status",jsonObject.getString("status"));
                //List<Map<String, Object>> list1 = temperatureServer.selectReportByDevicefk(map1);
                List<Map<String, Object>> list1 = reportServer.selectAllBySn(map1);

                logger.info(list1.toString());
                ////开始加密
//                for(int i = 0; i<list1.size(); i++){
//                    String code = list1.get(i).get("temperature").toString();  //将温度转换位String
//                    String code1 = list1.get(i).get("comtemperature").toString();
//                  //  System.out.println(code1);
//                    Float tem = Float.parseFloat(code);//* instruction.PassCode; //有小数点，所以将String转float
//                  // Float tem1 = Float.parseFloat(code1);
//                  // int tem1 = Integer.parseInt(code1);//*instruction.PassCode;
//                    code = String.valueOf(tem);
//                   // code1 = String.valueOf(tem1);
//                 //   code1 = Integer.toString(tem1);
//                    char a [] = new char[code.length()];
//                    char b [] = new char[code1.length()];
//                    for(int j= 0; j<code.length(); j++){
//                            a[j] = code.charAt(j);
//                        char rc =  passCode(String.valueOf(a[j])); //加密
//                            a[j] = rc;
//                    }
//                    for(int k=0; k<code1.length(); k++){
//                        b[k] = code1.charAt(k);
//                        char bc =passCode(String.valueOf(b[k]));
//                        b[k] = bc;
//                    }
                ////将加密后的温度写回map里面
//                    list1.get(i).remove("temperature");
//                    list1.get(i).remove("comtemperature");
//                    list1.get(i).put("abcd",String.valueOf(a));
//                    list1.get(i).put("dcba",String.valueOf(b));
//                }
                if (list1.size() > 0 /*&& list.size() > 0*/) {
                    map.put("temperature", list1);
                  //  map.put("ktFZ", list);
                    os.setStatus(1);
                    os.setMsg("报告数据已生成");
                    os.setData(map);
                } else if (list.size() == 0) {
                    os.setStatus(0);
                    os.setMsg("数据获取失败");
                }
            } else {
                os.setStatus(0);
                os.setMsg("数据获取失败");
            }
        }catch (Exception e){
            logger.info(e.toString());
            os.setStatus(0);
            os.setMsg("该设备无任何测试数据，请重新检查测试流程");
        }
        return os;
    }

    /**
     * 暂存温度
     * @param jsonObject
     * @return
     */
    @PostMapping("/temp/insertTemp")
    public OvlsResult insertTemperature(@RequestBody JSONObject jsonObject){
        JSONArray ja = jsonObject.getJSONArray("list");
        JSONObject jm = jsonObject.getJSONObject("finallValue");
        report re = new report();
        int check = 0;
        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(time);
        OvlsResult os = new OvlsResult();
        re.setId(GetUUIDString.getUUID());
        re.setFluctucationrange(jm.getString("fluctuationRange"));
        re.setMinvalue(jm.getString("minValue"));
        re.setMaxvalue(jm.getString("maxValue"));
        re.setAbsolutedeviation(jm.getString("absoluteDeviation"));
        re.setMeanvalue(jm.getString("meanValue"));
        re.setCalibrationvalue(jm.getString("calibrationValue"));
        re.setEmssing(jsonObject.getString("emssing"));
        re.setKtdevicefk(jsonObject.getString("ktdevicefk"));
        re.setDevicefk(jsonObject.getString("devicefk"));
        re.setComtemperature(jsonObject.getString("comtemperature"));
        re.setCretime(new Date());
        re.setStatus(jsonObject.getString("status"));
        re.setVariancevalue(jm.getString("varianceValue"));
        re.setSpacing(jsonObject.getString("spacing"));
        re.setFactory(jsonObject.getString("factory"));
        System.out.println(re);
        reportServer.insertReport(re);
        for(int i = 0; i < ja.size(); ++i) {
            JSONObject jb = ja.getJSONObject(i);
            temperature tem = new temperature();
            tem.setId(GetUUIDString.getUUID());
            tem.setWettemp(jsonObject.getString("wettemp"));
            tem.setKtdevicefk(jsonObject.getString("ktdevicefk"));
            tem.setComtemperature(jsonObject.getString("comtemperature"));
            tem.setDevicefk(jsonObject.getString("devicefk"));
            tem.setSys_user(jsonObject.getString("sys_user"));
            tem.setStatus(jsonObject.getString("status"));
            tem.setTemperature(jb.getString("temperature"));
            tem.setEmssing(jsonObject.getString("emssing"));
            tem.setCretime(new Date());
            check = this.temperatureServer.insertTemperature(tem);
            if (check == 1) {
                continue;
            }
        }
        if (check == 1) {
            os.setStatus(check);
            os.setMsg("插入成功");
        } else {
            os.setStatus(check);
            os.setMsg("插入失败");
        }
        return os;
    }

    /**
     * 更新状态
     * @param jsonObject
     * @return
     */
    @PostMapping("/temp/updateStatus")
    public OvlsResult updateTemp(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        Map<String, Object> map = new HashMap<>();
        map.put("status",jsonObject.getString("status"));
        map.put("devicefk",jsonObject.getString("devicefk"));
        int check = temperatureServer.updateTemp(map);
        int checkreport = reportServer.updateReportStatus(map);
        if(check >= 1 && checkreport>=1){
            os.setStatus(1);
            os.setMsg("已同意保存");
        }else{
            os.setStatus(0);
            os.setMsg("保存失败");
        }
        return os;
    }

    /**
     * 设为基准温度
     * @param jsonObject
     * @return
     */
    @PostMapping("temp/updateStatusByComT")
    public OvlsResult updateTempStatusByCom(@RequestBody JSONObject jsonObject){
        OvlsResult os = new OvlsResult();
        Map<String,Object> map = new HashMap<>();
        map.put("devicefk",jsonObject.getString("devicefk"));
        map.put("status",jsonObject.getString("status"));
        map.put("comtemperature",jsonObject.getString("comtemperature"));
        int check = temperatureServer.updateTempStatusByCom(map);
        reportServer.updateReportStatusByCom(map);
        if(check >= 1){
            os.setStatus(1);
            os.setMsg("已同意保存校准");
        }else{
            os.setStatus(0);
            os.setMsg("保存失败");
        }
        return os;
    }

    @PostMapping("/temp/testinsert")
    public OvlsResult insertReport(@RequestBody JSONObject jsonObject) {
        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(time);
        OvlsResult os = new OvlsResult();
        report re = new report();
        re.setId(GetUUIDString.getUUID());
        re.setFluctucationrange(jsonObject.getString("fluctuationRange"));
        re.setMinvalue(jsonObject.getString("minValue"));
        re.setMaxvalue(jsonObject.getString("maxValue"));
        re.setAbsolutedeviation(jsonObject.getString("absoluteDeviation"));
        re.setMeanvalue(jsonObject.getString("meanValue"));
        re.setCalibrationvalue(jsonObject.getString("calibrationValue"));
        re.setEmssing(jsonObject.getString("emssing"));
        re.setKtdevicefk(jsonObject.getString("ktdevicefk"));
        re.setDevicefk(jsonObject.getString("devicefk"));
        re.setComtemperature(jsonObject.getString("comtemperature"));
        re.setCretime(new Date());
        re.setStatus(jsonObject.getString("status"));
        re.setVariancevalue(jsonObject.getString("varianceValue"));
        re.setSpacing(jsonObject.getString("spacing"));
        System.out.println(re);
        int check = reportServer.insertReport(re);
        if (check >= 1) {
            os.setStatus(1);
            os.setMsg("cg");
        } else {
            os.setStatus(0);
        }
        return os;
    }

    @PostMapping("/sys/reportAll")
    public OvlsResult getReportAll(@RequestBody JSONObject jsonObject) {
        OvlsResult os = new OvlsResult();
        try {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = new HashMap<>();
            List<Map<String, Object>> parm = temperatureServer.selectKtDeviceByBlack(jsonObject.getString("devicefk"));
            String parmName = "" ;
            for(int i =0; i<parm.size(); i++){
                String parmCheck = parm.get(i).get("ktdevicefk").toString();
                if(parmCheck != null){
                    parmName = parmCheck;
                }
            }
            if (parm != null) {
                logger.info(parm.toString());
                List<deviation> list = deviationServer.selectByName(parmName);
                logger.info(list.toString());
                map1.put("devicefk",jsonObject.getString("devicefk"));
                map1.put("status",jsonObject.getString("status"));
                List<Map<String, Object>> list1 = temperatureServer.selectReportByDevicefk(map1);
               // List<Map<String, Object>> list1 = reportServer.selectAllBySn(map1);
                logger.info(list1.toString());
//                for(int i = 0; i<list1.size(); i++){
//                    String code = list1.get(i).get("temperature").toString();
//                    String code1 = list1.get(i).get("comtemperature").toString();
//                  //  System.out.println(code1);
//                    Float tem = Float.parseFloat(code);//* instruction.PassCode;
//                  // Float tem1 = Float.parseFloat(code1);
//                  // int tem1 = Integer.parseInt(code1);//*instruction.PassCode;
//                    code = String.valueOf(tem);
//                   // code1 = String.valueOf(tem1);
//                 //   code1 = Integer.toString(tem1);
//                    char a [] = new char[code.length()];
//                    char b [] = new char[code1.length()];
//                    for(int j= 0; j<code.length(); j++){
//                            a[j] = code.charAt(j);
//                        char rc =  passCode(String.valueOf(a[j]));
//                            a[j] = rc;
//                    }
//                    for(int k=0; k<code1.length(); k++){
//                        b[k] = code1.charAt(k);
//                        char bc =passCode(String.valueOf(b[k]));
//                        b[k] = bc;
//                    }
//                    list1.get(i).remove("temperature");
//                    list1.get(i).remove("comtemperature");
//                    list1.get(i).put("abcd",String.valueOf(a));
//                    list1.get(i).put("dcba",String.valueOf(b));
//                }
                if (list1.size() > 0 && list.size() > 0) {
                    map.put("temperature", list1);
                    map.put("ktFZ", list);
                    map.put("ktDevicefk",parmName);
                    os.setStatus(1);
                    os.setMsg("报告数据已生成");
                    os.setData(map);
                } else if (list.size() == 0) {
                    os.setStatus(0);
                    os.setMsg("数据获取失败");
                }
            } else {
                os.setStatus(0);
                os.setMsg("数据获取失败");
            }
        }catch (Exception e){
            logger.info(e.toString());
            os.setStatus(0);
            os.setMsg("该设备无任何测试数据，请重新检查测试流程");
        }
        return os;
    }

    @GetMapping("/temp/getDevicefkList")
    public OvlsResult getdevicefkList(){
        OvlsResult os = new OvlsResult();
        List<Map<String,Object>> list = temperatureServer.selectDeviceList();
        if(list.size() >0){
            os.setStatus(1);
            os.setMsg("cg" );
            os.setData(list);
        }else{
            os.setStatus(0);
            os.setMsg("sb");
        }
        return os;
    }
    /**
     * 更新报告
     * @param re
     * @return
     */
    @PostMapping("/sys/updateReport")
    public OvlsResult updeateReport(@RequestBody report re){
        OvlsResult os = new OvlsResult();
        int checkUpdate = reportServer.updateReport(re);
        if(checkUpdate >= 1){
            os.setStatus(1);
            os.setMsg("报告已更改");
        }else{
            os.setStatus(0);
            os.setMsg("修改失败");
        }
        return os;
    }
}
