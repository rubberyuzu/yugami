package com.example.rubberyuzu.yugamiapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class yugamiFragment extends Fragment {

    public yugamiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(20, 75, getContext());
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // バーの値が変更された時に呼び出される
                Log.d("yugami", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });

        View v = inflater.inflate(R.layout.fragment_yugami, container, false);
// add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) v.findViewById(R.id.main_layout);
        layout.addView(seekBar);
        return v;
    }
}
