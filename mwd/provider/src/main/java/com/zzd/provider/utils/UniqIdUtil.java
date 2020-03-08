package com.zzd.provider.utils;

public class UniqIdUtil {

    private static synchronized String getTime(){
        long time = System.currentTimeMillis();
        return String.valueOf(time);
    }
    public static String getUniqId(){
        String time = getTime();
        if (time.length()>19){
            time = time.substring(0,18);
        }
        return time;
    }

}
