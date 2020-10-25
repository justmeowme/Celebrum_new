package com.iisdf.celebrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (String.valueOf(MainActivity.getDefaults("visited", SplashActivity.this))=="null" && String.valueOf(MainActivity.getDefaults("login", SplashActivity.this))=="null"){
                    MainActivity.setDefaults("visited", "visited", SplashActivity.this);
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        }, 1200);

    }
}