package com.example.e_dive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyTicketDetailActv extends AppCompatActivity {

    TextView xnama_trip, xlocation, xdate_trip, xtime_trip,xketentuan;
    Button btn_pay_ticket;
    LinearLayout btn_back;


    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail_actv);

        xnama_trip = findViewById(R.id.xnama_trip);
        xdate_trip = findViewById(R.id.xdate_trip);
        xlocation = findViewById(R.id.xlocation);
        xtime_trip = findViewById(R.id.xtime_trip);
        xketentuan = findViewById(R.id.xketentuan);
        btn_pay_ticket = findViewById(R.id.btn_pay_ticket);
        btn_back = findViewById(R.id.btn_back);


        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String nama_trip_baru = bundle.getString("nama_trip");

        // mengambil data dari firebase

        reference = FirebaseDatabase.getInstance().getReference().child("Trip").child(nama_trip_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xnama_trip.setText(dataSnapshot.child("nama_trip").getValue().toString());
                xlocation.setText(dataSnapshot.child("location").getValue().toString());
                xdate_trip.setText(dataSnapshot.child("date_trip").getValue().toString());
                xtime_trip.setText(dataSnapshot.child("time_trip").getValue().toString());
                xketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoprof =  new Intent(MyTicketDetailActv.this,MyProfileActv.class);
                startActivity(backtoprof);
            }
        });

    }
}
