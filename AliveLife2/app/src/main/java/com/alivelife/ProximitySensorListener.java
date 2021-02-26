package com.alivelife;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class ProximitySensorListener implements SensorEventListener {
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO        
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long timestamp = event.timestamp;
        float value = event.values[0];


    }

}