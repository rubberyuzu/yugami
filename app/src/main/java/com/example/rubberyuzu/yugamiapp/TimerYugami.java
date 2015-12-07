package com.example.rubberyuzu.yugamiapp;

import android.text.method.HideReturnsTransformationMethod;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Lanyingying on 15/12/05.
 */
public class TimerYugami {
    public static String loadDisplaytime (Calendar s , Calendar e, Calendar t1){
        //Calendar t1 = Calendar.getInstance();
        t1.get(Calendar.HOUR_OF_DAY);
        Calendar t2 = Calendar.getInstance();
        Calendar min= Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        t2.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.DAY_OF_MONTH), 18, 0, 0);
        min.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.DAY_OF_MONTH), 19, 30, 0);
        max.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.DAY_OF_MONTH), 8, 30, 0);
        double def=(t1.getTimeInMillis()-t2.getTimeInMillis())/60000.0;
        double def5=loadSR(s, e)*(s.getTimeInMillis()-t2.getTimeInMillis())/60000.0;
        double def6=def5+1.2*(e.getTimeInMillis()-s.getTimeInMillis())/60000.0;
        if (t1.compareTo(min)<0 && t1.compareTo(max)>0){
            t2 = t1;
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
            return f.format(t2.getTime());
        }
        if (def < def5) {
            t2.add(Calendar.MINUTE, (int) (def/loadSR(s, e)));
        } else if (def > def5 && def < def6){
            t2.add(Calendar.MINUTE, (int)(def5/loadSR(s, e)) + (int)((def-def5)/1.2));
        }else if (def>def6){
            t2.add (Calendar.MINUTE, (int)(def5/loadSR(s, e)) +(int)((def-def6)/loadSR(s, e))+ (int)((def6-def5)/1.2));
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        return f.format(t2.getTime());
    }

    private static double loadSR(Calendar s, Calendar e){
        double def4=(e.getTimeInMillis()-s.getTimeInMillis())/60000.0;
        return (16 * 60 - 1.2 * def4) /(16 * 60 - def4);
    }

}