package com.example.demo.util;

import java.util.UUID;

/**
 * Created by LLP on 2019/7/8.
 */
public class GetUUIDString {

    public static String getUUID(){
        String messageString = "";
        String replaceMessage = "";

        UUID uuid = UUID.randomUUID();
        messageString = uuid.toString();

        replaceMessage = messageString.replaceAll("-", "");
        return replaceMessage;
    }

}