package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckoutActv extends AppCompatActivity {

    Button btn_pay_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_actv);

        btn_pay_ticket = findViewById(R.id.btn_pay_ticket);
        btn_pay_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successorder =  new Intent(CheckoutActv.this,SuccessOrder.class);
                startActivity(successorder);
            }
        });
    }
}
