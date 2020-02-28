package com.example.test_myapplication.noti;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class NotificaClass extends Application {
    public static final String show_1_ID = "channel_1";
    public static final String show_2_ID = "channel_2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel sh_channel1 = new NotificationChannel(show_1_ID
                    ,"Channel_1"
                    , NotificationManager.IMPORTANCE_HIGH);
            sh_channel1.setDescription("This is card_1");
            sh_channel1.setLightColor(Color.YELLOW);

            NotificationChannel sh_channel2 = new NotificationChannel(show_2_ID
                    ,"Channel_2"
                    , NotificationManager.IMPORTANCE_LOW);
            sh_channel2.setDescription("This is card_2");
            sh_channel2.setLightColor(Color.MAGENTA);

            NotificationManager notifi_mana = getSystemService
                    (NotificationManager.class);
            notifi_mana.createNotificationChannel(sh_channel1);
            notifi_mana.createNotificationChannel(sh_channel2);

        }
    }
}
