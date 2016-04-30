package com.tom.snapchat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeNotification();
        startActivity(new Intent(this, ChatActivity.class));
    }

    public void hello(View v){
        Intent hello = new Intent(this, HelloIntentService.class);
        hello.putExtra(HelloIntentService.PARAM_MSG, "TEST1");
        startService(hello);
        hello.putExtra(HelloIntentService.PARAM_MSG, "TEST2");
        startService(hello);
    }
    public void startMyService(View v){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
    public void stopMyService(View v){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void makeNotification() {
        Bitmap bmp = BitmapFactory
                .decodeResource(getResources(), R.drawable.pig64);
        Notification.BigPictureStyle big =
                new Notification.BigPictureStyle();
        big.bigPicture(
                BitmapFactory.decodeResource(getResources(), R.drawable.pig256))
                .setSummaryText("bla bla bla");
        Intent intent = new Intent(this, ChatActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);
        /*
        PendingIntent pendingIntent =
                PendingIntent.getActivity( this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
                */
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.pig32)
                .setContentTitle("This is Title")
                .setContentText("This is Text")
                .setContentInfo("This is Info")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
