package com.example.rubberyuzu.yugamiapp;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class yugamiFragment extends Fragment {

    public yugamiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_yugami, container, false);

        TextView startTime = (TextView) v.findViewById(R.id.start_time);
        TextView endTime = (TextView) v.findViewById(R.id.end_time);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/digital-7.ttf");
        startTime.setTypeface(tf);
        endTime.setTypeface(tf);

        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(0, 100, getContext());
        seekBar.setTranslationY(600);
        seekBar.setNormalizedMinValue(0.09);
        seekBar.setNormalizedMaxValue(0.91);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                double min = (double)minValue / 100.0;
                double max = (double)maxValue / 100.0;

                HashMap<String, String> map = new HashMap<>();
                int minMinute = (int) ( 960 * min);
                int maxMinute = (int) ( 960 * max);

                Calendar calenadar = Calendar.getInstance();
                calenadar.set(calenadar.get(Calendar.YEAR), calenadar.get(Calendar.MONTH), calenadar.get((Calendar.DAY_OF_MONTH)), 18, 0);

                Calendar calenadar2 = Calendar.getInstance();
                calenadar2.set(calenadar.get(Calendar.YEAR), calenadar.get(Calendar.MONTH), calenadar.get((Calendar.DAY_OF_MONTH)), 18, 0);

                SimpleDateFormat f = new SimpleDateFormat("HH:mm");

                calenadar.add(Calendar.MINUTE, minMinute);
                calenadar2.add(Calendar.MINUTE, maxMinute);

                map.put("min", f.format(calenadar.getTime()));
                map.put("max", f.format(calenadar2.getTime()));

                Log.d("yugami", " min:" + map.get("min") + "max:" + map.get("max"));
                Log.d("yugami", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);

                TextView startTime = (TextView) v.findViewById(R.id.start_time);
                TextView endTime = (TextView) v.findViewById(R.id.end_time);
                startTime.setText(map.get("min"));
                endTime.setText(map.get("max"));

            }
        });


// add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) v.findViewById(R.id.main_layout);
        layout.addView(seekBar);
        return v;
    }
}