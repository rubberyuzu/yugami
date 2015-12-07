package com.example.rubberyuzu.yugamiapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;


public class Leave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_leave);
        final TextView timeView =(TextView) findViewById(R.id.timeView);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/digital-7.ttf");
        timeView.setTypeface(tf);



        final Handler mHandler = new Handler();
        Thread t = new Thread() {
            public void run() {
                try {
                    int i = 0;
                    while (true) {
                        Thread.sleep(1000);
                        Calendar c = Calendar.getInstance();
                        final String str = TimerYugami.loadDisplaytime(c, c, c);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                timeView.setText(str);
                            }
                        });
                        i++;
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();



        ImageButton leaveButton = (ImageButton)findViewById(R.id.leaveButton);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this, yugami.class);
                startActivity(intent);
            }
        });

    }




}
