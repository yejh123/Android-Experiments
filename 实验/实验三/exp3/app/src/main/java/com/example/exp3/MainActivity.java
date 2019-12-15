package com.example.exp3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Log.d("MainActivity", localBroadcastManager.toString());
        localBroadcastManager.sendBroadcast(intent);
    }
}
