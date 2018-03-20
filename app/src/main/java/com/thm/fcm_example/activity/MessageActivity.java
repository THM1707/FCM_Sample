package com.thm.fcm_example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.thm.fcm_example.R;

public class MessageActivity extends AppCompatActivity {
    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mTextMessage = findViewById(R.id.tv_message);
        String message = getIntent().getStringExtra("message");
        mTextMessage.setText(message);
    }
}
