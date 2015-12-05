package com.example.rubberyuzu.yugamiapp.timer;

import java.lang.ref.SoftReference;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lanyingying on 15/12/05.
 */
public class Timer {
    public Calendar loadDisplaytime (Calendar s , Calendar e){
        Calendar t1 = Calendar.getInstance();
        t1.get(Calendar.HOUR_OF_DAY);
        Calendar t2 = Calendar.getInstance();
        t2.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.HOUR_OF_DAY), 18, 0, 0);
        int def=t1.compareTo(t2)
        def=def/60000;
        int def1=t1.compareTo(s);
        def1=def1/60000;
        int def2=t1.compareTo(e);
        def2=def2/60000;
        int def3=s.compareTo(t2);
        def3=def3/60000;
        int def4=t1.compareTo(e);
        def4=def4/60000;
        int def5=e.compareTo(s);
        def5=def5/60000;
        if (def1 < 0) {
            t2.add(Calendar.MINUTE, (int) (def * loadSR(s, e)));
            return t2;
        } else if (def1 > 0 && def2 < 0){
            t2.add(Calendar.MINUTE, (int)(loadSR(s, e) * def3) + (int)(1.2*def1));
        }else if (def2 > 0){
            t2.add (Calendar.MINUTE, (int)(loadSR(s, e) *def3) +(int)(loadSR(s, e) * def4)+ (int)(1.2*def5));return t2;
        }
        return t2;
    }

    private double loadSR(Calendar s, Calendar e){
        int def=e.compareTo(s);
        def=def/60000;
        return (16 * 60 - 1.2 * def) /(16 * 60 - def);
    }

}
