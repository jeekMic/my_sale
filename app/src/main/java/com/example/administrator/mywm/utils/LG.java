package com.example.administrator.mywm.utils;

import android.util.Log;

public class LG {
    public static boolean isOpen = true;
    public LG() {
    }
    public static void I(String str){
        if (isOpen){
            Log.i("hb------->",str);
        }
    }
    public static void E(String str){
        if (isOpen){
            Log.e("hb------->",str);
        }
    }
}
