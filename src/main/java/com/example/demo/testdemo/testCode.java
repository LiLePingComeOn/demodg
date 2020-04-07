//
//package com.example.demo.testdemo;
//
//import com.swetake.util.Qrcode;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//
//public class testCode {
//    public void encoderQRCode(String content,String imgPath){
//        try{
//            Qrcode qrcodeHandler = new Qrcode();
//            qrcodeHandler.setQrcodeErrorCorrect('M');
//            qrcodeHandler.setQrcodeEncodeMode('B');
//            qrcodeHandler.setQrcodeVersion(5);
//            byte [] contentBytes = content.getBytes("utf-8");
//            BufferedImage bufImg = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
//            Graphics2D gs  = bufImg.createGraphics();
//            gs.setBackground(Color.white);
//            gs.clearRect(0,0,600,600);
//            gs.setColor(Color.BLACK);
//            int poxoff = 2 ;
//            if(contentBytes.length >0 && contentBytes.length <800){
//                boolean [][] codeOut = qrcodeHandler.calQrcode(contentBytes);
//                for(int i = 0; i<codeOut.length; i++){
//                    for(int j = 0; j<codeOut.length; j++){
//                        if(codeOut[j][i]){
//                            gs.fillRect(j * 16 + poxoff, i*16+poxoff,16,16);
//                        }
//                    }
//                }
//            }else{
//
//            }
//            gs.dispose();
//            bufImg.flush();
//            File imgFile = new File(imgPath);
//            ImageIO.write(bufImg,"png",imgFile);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public  static void main(String [] args){
////        String imgPath = "D:\\" + "123"+".png";
////        String content = "";
////        testCode tc = new testCode();
////        tc.encoderQRCode(content,imgPath);
////        System.out.println("imgPath:"+imgPath);
////        String arg = "";
////        String content="INFO KT15.82 DET A SN 10967  -30 1000 C\r";
////      //  List<String> strs = new ArrayList<String>();
////        Pattern p = Pattern.compile("\\d{5}");
////        Matcher m = p.matcher(content);
////        while(m.find()) {
////           // strs.add(m.group());
////        System.out.println(m.group());
////arg = m.group();
////            //创建Pattern对象
////            // Pattern p=Pattern.compile();
////            //  Matcher m=p.matcher(content);
////            //   System.out.println(m.group());
////        }
////        System.out.println(arg);
//    }
//}
//
