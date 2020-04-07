package com.example.demo.util;



import com.example.demo.Controller.tempWebsocket;
import org.springframework.stereotype.Component;



/**
 * Created by LLP on 2019/3/17.
 */
@Component
public class DataThread implements Runnable {

    private Integer dataNumber;
    private Integer nowDataNumber;



    //线程是否运行
    public Boolean stopMe = true;



    public void stopMe(){
        stopMe =false;
    }

    public void run(){
        while (stopMe){
           // MyWebSocket webSocket = new MyWebSocket();
            tempWebsocket tempWebsocket = new tempWebsocket();
           // appointmentDataService = SpringContextUtil.getBean("appointmentDataService");

        }
    }
}
