package com.example.rubberyuzu.yugamiapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class clock extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
//        Typeface myTypeface = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/digital-7.ttf");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Enter relevant functionality for when the last widget is received
        String action = intent.getAction();
        Log.i("yuyu", "------coming to onReceive-------");
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
            Intent i = new Intent(context, clock.class); // ReceivedActivityを呼び出すインテントを作成
            i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);


            PendingIntent sender = PendingIntent.getBroadcast(context, 0, i, 0); // ブロードキャストを投げるPendingIntentの作成

            Calendar calendar = Calendar.getInstance(); // Calendar取得
            calendar.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
            calendar.add(Calendar.SECOND, 1); // 現時刻より5秒後を設定

//        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE); // AlramManager取得
            AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE); // AlramManager取得

            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

            ComponentName thisWidget = new ComponentName(context, clock.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);



            Calendar c = Calendar.getInstance();
            String widgetText = TimerYugami.loadDisplaytime(c,c,c);

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clock);

//            views.setTextViewText(R.id.appwidget_text, widgetText);
            Bitmap myBitmap = Bitmap.createBitmap(200, 50, Bitmap.Config.ARGB_4444);
            Canvas myCanvas = new Canvas(myBitmap);
            Paint paint = new Paint();
            Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/digital-7.ttf");
            paint.setAntiAlias(true);
            paint.setSubpixelText(true);
            paint.setTypeface(tf);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GREEN);
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.CENTER);
            myCanvas.drawText(widgetText, 100, 40, paint);

            views.setImageViewBitmap(R.id.appwidget_text, myBitmap);

            int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(thisWidget);

            // Instruct the widget manager to update the widget
            manager.updateAppWidget(appWidgetIds[0], views);


            Log.i("yuyu", "------coming to update-------");
        }
        super.onReceive(context, intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        CharSequence widgetText = TimerYugami.loadDisplaytime(c,c,c);



        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clock);

//        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}