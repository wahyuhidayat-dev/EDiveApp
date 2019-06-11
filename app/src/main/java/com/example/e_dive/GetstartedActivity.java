package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GetstartedActivity extends AppCompatActivity {

    Button btnsignin,btnsignup;
    Animation ataskebawah,bawahkeatas;
    TextView subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        //load animation
        ataskebawah = AnimationUtils.loadAnimation(this,R.anim.ataskebawah);
        bawahkeatas = AnimationUtils.loadAnimation(this,R.anim.bawahkeatas);

        //load find element
        subtitle = findViewById(R.id.subtitle);
        btnsignup = findViewById(R.id.btnsignup);
        btnsignin = findViewById(R.id.btnsignin);

        //run animation
        subtitle.startAnimation(ataskebawah);
        btnsignin.startAnimation(bawahkeatas);
        btnsignup.startAnimation(bawahkeatas);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign =  new Intent(GetstartedActivity.this,SignInActv.class);
                startActivity(gotosign);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gotosignup =  new Intent(GetstartedActivity.this,RegisterOneActv.class);
            startActivity(gotosignup);
        }
    });
    }
}
