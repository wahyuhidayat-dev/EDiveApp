package com.example.e_dive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyProfileActv extends AppCompatActivity {

    LinearLayout item_my_ticket;
    Button edit_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_actv);

        item_my_ticket = findViewById(R.id.item_my_ticket);
        edit_profile = findViewById(R.id.edit_profile);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editprofile =  new Intent(MyProfileActv.this,EditProfileActv.class);
                startActivity(editprofile);
            }
        });

        item_my_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemticket =  new Intent(MyProfileActv.this,MyTicketDetailActv.class);
                startActivity(itemticket);
            }
        });
    }
}
