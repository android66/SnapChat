package com.tom.snapchat;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * Created by tom on 2016/4/30.
 */
public class HelloIntentService extends IntentService{
    public static final String PARAM_MSG = "message";

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg = intent.getStringExtra(PARAM_MSG);
        Log.d("HelloIntentService", "onHandleIntent");
        SystemClock.sleep(3000);
        String debug = DateFormat.format(
                "hh:mm:ss", System.currentTimeMillis())
                + "\t" + msg;
        Log.d("HelloIntentService", debug);
    }
}
