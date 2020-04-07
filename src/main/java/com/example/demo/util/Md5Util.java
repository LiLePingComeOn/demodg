package com.example.demo.util;

import java.security.MessageDigest;
import java.util.Random;

public class Md5Util {

    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public static char passCode(String code) {
        //System.out.println(code);
        Random c = new Random();
      char rc = 0;
        if(code.equals("0") ){
            rc = (char) (int) (Math.random() * 3 + 42);

        }
        else if (code.equals("1")) {
            // code = new Random()
            //   char rc = (char)(c.nextInt(3) + (int)'a');
             rc = (char) (int) (Math.random() * 3 + 35);
        } else if (code.equals("2")) {
              rc = (char) (int) (Math.random() * 3 + 97);

        } else if (code.equals("3")){
             rc = (char) (int) (Math.random() * 3 + 100);


        }else if(code.equals("4")){
              rc =(char)(int)(Math.random()*3+103);


        }
        else if(code.equals("5")){
             rc =(char)(int)(Math.random()*3+106);


        }
        else if(code.equals("6")){
             rc =(char)(int)(Math.random()*3+109);


        }
        else if(code.equals("7")){
              rc =(char)(int)(Math.random()*4+112);


        }
        else if(code.equals("8")){
             rc =(char)(int)(Math.random()*3+116);


        }
        else if(code.equals("9")){
               rc =(char)(int)(Math.random()*4+119);


        }else {
            rc = code.charAt(0);
        }
        return rc;

    }


    public static  void  main( String[]args){

        //  System.out.println(encrypt32("123456"));
//        String str = "34.40";
//        char a [] = new char[str.length()];
//        for(int i= 0; i<str.length(); i++){
//            a[i] = str.charAt(i);
//           // System.out.println(a[i]);
//            char rc =  passCode(String.valueOf(a[i]));
//            a[i] = rc;
//           // System.out.println(rc);
//        }
//        System.out.println(a);
//
//        char rc = passCode(String.valueOf('2'));
//        System.out.println(rc);
    }



}
