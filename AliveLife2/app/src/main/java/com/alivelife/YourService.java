package com.alivelife;


import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class YourService extends IntentService {
    private NotificationUtils mNotificationUtils;
    static int count;
    private String etiqueta= " I'm in: ";

    /**
     * A constructor is required, and must call the super
     * <code>
     *     <a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a>
     * </code>
     * constructor with a name for the worker thread.
     */
    public YourService() {
        super("YourService");
        Log.d("Estoy en: ", "He entrado");
        count = 0;
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        System.out.println("*****************************************************************************************");
        System.out.println("********** ON HANDLE *********");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count++;
        Log.d(etiqueta, String.valueOf(count));
        System.out.println("*****************************************************************************************");
        System.out.println("********** ON START COMMAND *********");
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        Log.d("Estamos en: ", "On start after Toast");
        return super.onStartCommand(intent,flags,startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

/* private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

@Override
    public int onStartCommand(Intent intent, int flags, int startId){

        // do your jobs here

        startForeground();

        return super.onStartCommand(intent, flags, startId);
    }

    public static void startForeground() {
        Intent notificationIntent = new Intent(this, UserView.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                //.setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }*/