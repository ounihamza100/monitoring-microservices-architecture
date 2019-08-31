package com.dashboard.utils;

public class Utility {

    public static Boolean isNotEmptyAndNotNull(String str){
        return ((str==null || str.equals("") ? Boolean.FALSE : Boolean.TRUE));
    }
}
