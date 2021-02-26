package com.alivelife;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class YourService extends Service implements SensorEventListener{

    private static final int NOTIF_ID = 1;
    private SensorManager sensorManager;
    private ProximitySensorListener proximitySensorListener = null;
    private static boolean proximitySensorBool = false;
    private static int proximityCounter = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(getApplicationContext(),"This is a Service running in Background",
                Toast.LENGTH_SHORT).show();
        Sensor proximitySensor;

        // do your jobs here
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener((SensorEventListener) this, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        if (proximitySensor == null) {
            Toast.makeText(this, "No proximity sensor found in device.", Toast.LENGTH_SHORT).show();

        } else {
            // registering our sensor with sensor manager.
            sensorManager.registerListener(this,
                    proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        startForeground();

        return START_STICKY;


        
        //return super.onStartCommand(intent, flags, startId);
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, UserView.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        NotificationUtils mNotificationUtils = new NotificationUtils(this);
        Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification("Madaro", "Service running in background");
        mNotificationUtils.getManager().notify(101, nb.build());

        startForeground(NOTIF_ID, nb // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.drawable.notification_logo)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(proximitySensorListener);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long timestamp = event.timestamp;
        float value = event.values[0];

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if ( value <= 0) {
                // TODO: revisa cada medio segundo por lo que al estar un segundo encendido ya se activaría
                if ( !proximitySensorBool ) { // Last measure was false
                    if ( proximityCounter < 2 ) { // Times you want to get close to the phone -1

                        proximityCounter++;
                        Log.i("******", "Sensor touched "+ proximityCounter + " times");

                    } else {
                        // if sensor event return 0 then object is closed
                        // to sensor else object is away from sensor.

                        // Upload content
                        Log.d("*****", "Uploading content with proximity sensor");
                    }

                    proximitySensorBool = true;

                }
            } else {
                proximitySensorBool = false;
            }
        }
       // sensorManager.unregisterListener(this);
        // grab the values and timestamp -- off the main thread
        new SensorEventLoggerTask().execute(event);
        // stop the service
        //stopSelf();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
        Toast.makeText(getApplicationContext(),"The accuracy have changed",
                Toast.LENGTH_SHORT).show();
    }


    private class SensorEventLoggerTask extends
            AsyncTask<SensorEvent, Void, Void> {
        @Override
        protected Void doInBackground(SensorEvent... events) {
            SensorEvent event = events[0];
            // log the value
            Void vo = null;
            return vo;
        }
    }

    public static boolean isProximitySensorOn(){
        return proximitySensorBool;
    }
}