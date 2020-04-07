package com.example.demo.testdemo;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class testPublishList {

    public static void main(String [] args) {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
        PrintService[] service = PrintServiceLookup.lookupPrintServices(flavor, pras);
        for (int i = 0; i < service.length; i++) {
            System.out.println(service[i].getName());
        }
    }
}
