package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessOrder extends AppCompatActivity {

    Button mydashboard,view_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_order);

        mydashboard = findViewById(R.id.mydashboard);
        view_ticket = findViewById(R.id.view_ticket);
        mydashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home =  new Intent(SuccessOrder.this,HomeActv.class);
                startActivity(home);
            }
        });
        view_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprof=  new Intent(SuccessOrder.this,MyProfileActv.class);
                startActivity(gotoprof);
            }
        });

    }
}
