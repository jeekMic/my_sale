package com.example.administrator.mywm.utils;

public class Contant {
    public static final String BASEURL = "http://222.187.219.82:4406/TakeoutServiceVersion2/";
    public static final String HOME_URL = "home";

    /**
     * http://10.0.2.2:8080/TakeoutServiceVersion2/imgs/category/1.png
     */
    public static String change_url(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.replace(7,20,"222.187.219.82:4406");
        return stringBuilder.toString();
    }
}
