package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterTwoActv extends AppCompatActivity {

    LinearLayout btn_back;
    Button btncontinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two_actv);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoprev =  new Intent(RegisterTwoActv.this,RegisterOneActv.class);
                startActivity(backtoprev);
            }
        });

        btncontinue = findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosuccess =  new Intent(RegisterTwoActv.this,SuccessActv.class);
                startActivity(gotosuccess);
            }
        });
    }
}
