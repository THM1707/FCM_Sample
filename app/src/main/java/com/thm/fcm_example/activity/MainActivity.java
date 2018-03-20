package com.thm.fcm_example.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.thm.fcm_example.R;
import com.thm.fcm_example.app.Constant;
import com.thm.fcm_example.model.ResponseMessage;
import com.thm.fcm_example.service.AppServiceClient;
import com.thm.fcm_example.util.NotificationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView mTextMessage;
    private EditText mEditMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.tv_push_message);
        mEditMessage = findViewById(R.id.et_message);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null) {
                    switch (intent.getAction()) {
                        case Constant.REGISTRATION_COMPLETE:
                            // auto subscribe to global for all device
                            FirebaseMessaging.getInstance().subscribeToTopic(Constant.TOPIC_GLOBAL);
                            break;
                        case Constant.PUSH_NOTIFICATION:
                            String message = intent.getStringExtra("message");
                            Toast.makeText(getApplicationContext(),
                                "Push notification: " + message,
                                Toast.LENGTH_LONG).show();
                            mTextMessage.setText(message);
                    }
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
            new IntentFilter(Constant.REGISTRATION_COMPLETE));
        // register notify notification arrive to activity
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
            new IntentFilter(Constant.PUSH_NOTIFICATION));
        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    public void sendNotification(View view) {
        String topic = "global", title = "My device";
        String body = mEditMessage.getText().toString();
        AppServiceClient.getMyApiInstance(this)
            .sendTopicMessage(topic, body, title)
            .enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call,
                                       Response<ResponseMessage> response) {
                    if (response.body() == null) {
                        Toast.makeText(MainActivity.this, "Wrong response",
                            Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Oops something happened",
                        Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void sendData(View view){
        String topic = "global", title = "My device";
        String imageUrl = "http://kb4images.com/images/random-image/37670495-random-image.jpg";
        Long timestamp = System.currentTimeMillis()/1000;
        String message = mEditMessage.getText().toString();
        AppServiceClient.getMyApiInstance(this)
            .sendData(topic, message, title, imageUrl, timestamp)
            .enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call,
                                       Response<ResponseMessage> response) {
                    if (response.body() == null) {
                        Toast.makeText(MainActivity.this, "Wrong response",
                            Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Oops something happened",
                        Toast.LENGTH_SHORT).show();
                }
            });
    }
}