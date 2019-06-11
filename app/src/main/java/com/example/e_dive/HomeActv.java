package com.example.e_dive;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.florent37.shapeofview.shapes.CircleView;

public class HomeActv extends AppCompatActivity {

    ConstraintLayout pramuka;
    CircleView btn_to_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actv);

        pramuka = findViewById(R.id.pramuka);
        btn_to_profile = findViewById(R.id.btn_to_profile);

        pramuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodive =  new Intent(HomeActv.this,DoDiveActv.class);
                startActivity(gotodive);
            }
        });

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile =  new Intent(HomeActv.this,MyProfileActv.class);
                startActivity(gotoprofile);
            }
        });
    }
}
