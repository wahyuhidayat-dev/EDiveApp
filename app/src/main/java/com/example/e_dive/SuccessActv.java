package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessActv extends AppCompatActivity {

    Button btn_explore;
    Animation ataskebawah,bawahkeatas;
    ImageView icon_success;
    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_actv);

        //load animation
        ataskebawah = AnimationUtils.loadAnimation(this,R.anim.ataskebawah);
        bawahkeatas = AnimationUtils.loadAnimation(this,R.anim.bawahkeatas);


        //load find element
        icon_success = findViewById(R.id.icon_success);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        btn_explore = findViewById(R.id.btn_explore);


        //run animation
        icon_success.startAnimation(ataskebawah);
        textView.startAnimation(bawahkeatas);
        textView2.startAnimation(bawahkeatas);
        btn_explore.startAnimation(bawahkeatas);


        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome =  new Intent(SuccessActv.this,HomeActv.class);
                startActivity(gotohome);
            }
        });
    }
}
