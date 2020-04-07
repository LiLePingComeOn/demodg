package com.example.demo.util;

import com.example.demo.Controller.tempWebsocket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by LLP on 2019/3/16.
 */
public class WebSocketMapUtil {

    public static ConcurrentMap<String, tempWebsocket> webSocketMap = new ConcurrentHashMap<>();
    public static void put(String key, tempWebsocket myWebSocket){
        webSocketMap.put(key, myWebSocket);
    }

    public static tempWebsocket get(String key){
        return webSocketMap.get(key);
    }

    public static void remove(String key){
        webSocketMap.remove(key);
    }

    public static Collection<tempWebsocket> getValues(){
        return webSocketMap.values();
    }

}
