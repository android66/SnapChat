package com.tom.snapchat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ChatActivity extends AppCompatActivity implements ServiceConnection {
    ChatService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void send(View v){
        EditText ed = (EditText)findViewById(R.id.ed_message);
        String msg = ed.getText().toString();
        if (mService!=null){
            mService.sendMessage(msg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ChatService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name,
                                   IBinder service) {
        ChatService.ChatBinder binder =
                (ChatService.ChatBinder) service;
        mService = binder.getService();
        Log.d("ChatActivity", "CharService binded");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        Log.d("ChatActivity", "CharService unbinded");
    }
}
