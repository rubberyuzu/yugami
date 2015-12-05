package com.example.rubberyuzu.yugamiapp;

/**
 * Created by reoy on 15/12/05.
 */
public class TimerTest {
    
}

public class Timer {
    public Calendar loadDisplaytime (Calendar s , Calendar e){
        Calendar t1 = Calendar.getInstance();
        t1.get(Calendar.HOUR_OF_DAY);
        int def=t1.compareTo(s);
        def=def/60000;
        if (def < 0) {
            Calendar t2 = Calendar.getInstance();
            t2.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.HOUR_OF_DAY), 18, 0, 0);
            t2.add(Calendar.MINUTE,((int)(double)def)*loadSR(s,e)));
        }

    }

    private double loadSR(Calendar s, Calendar e){
        int def=e.compareTo(s);
        def=def/60000;
        return (16 * 60 - 1.2 * def) /16 * 60 - def;
    }
}