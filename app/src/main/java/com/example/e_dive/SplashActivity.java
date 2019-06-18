package com.example.e_dive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation splashactv;
    TextView app_title;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation
        splashactv = AnimationUtils.loadAnimation(this,R.anim.splashactv);

        //load element
        app_title = findViewById(R.id.app_title);

        //run animation
        app_title.startAnimation(splashactv);

        getUsernameLocal();

    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()){
            // setting timer untuk 2 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){

                @Override
                public void run() {
                    // merubah ke activity lain
                    Intent gotoGetstarted = new Intent(SplashActivity.this, GetstartedActivity.class);
                    startActivity(gotoGetstarted);
                    finish();
                }
            }, 2000); // delay 2s

        }else {
            // setting timer untuk 2 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){

                @Override
                public void run() {
                    // merubah ke activity lain
                    Intent gotoHome = new Intent(SplashActivity.this, HomeActv.class);
                    startActivity(gotoHome);
                    finish();
                }
            }, 2000); // delay 2s
        }
    }
}
