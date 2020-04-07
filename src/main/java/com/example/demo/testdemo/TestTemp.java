package com.example.demo.testdemo;

import com.example.demo.Controller.tempWebsocket;
import com.example.demo.entity.instruction;
import com.example.demo.server.SerialPortManager;
import com.example.demo.util.ByteUtils;
import com.example.demo.util.ShowUtils;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;


public class TestTemp {



    public static void main(String [] args){
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

        }catch (InterruptedException p){}}


}
