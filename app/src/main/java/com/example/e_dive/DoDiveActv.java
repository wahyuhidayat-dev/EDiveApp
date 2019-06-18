package com.example.e_dive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DoDiveActv extends AppCompatActivity {

Button btn_buy_ticket;
LinearLayout btn_back;
DatabaseReference reference;
TextView title_dive,location_dive,accomodation,food,equipment,short_desc_trips;
ImageView url_thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_dive_actv);

        btn_buy_ticket= findViewById(R.id.btn_buy_ticket);
        btn_back= findViewById(R.id.btn_back);
        title_dive= findViewById(R.id.title_dive);
        location_dive= findViewById(R.id.location_dive);
        accomodation= findViewById(R.id.accomodation);
        food= findViewById(R.id.food);
        equipment= findViewById(R.id.equipment);
        short_desc_trips= findViewById(R.id.short_desc_trips);
        url_thumbnail= findViewById(R.id.url_thumbnail);

        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_dive_baru = bundle.getString("jenis_tiket");

        //mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Trip").child(jenis_dive_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title_dive.setText(dataSnapshot.child("nama_trip").getValue().toString());
                location_dive.setText(dataSnapshot.child("location").getValue().toString());
                accomodation.setText(dataSnapshot.child("acomodation").getValue().toString());
                food.setText(dataSnapshot.child("food_drink").getValue().toString());
                equipment.setText(dataSnapshot.child("equipment").getValue().toString());
                short_desc_trips.setText(dataSnapshot.child("short_desc").getValue().toString());
                Picasso.with(DoDiveActv.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(url_thumbnail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_buy_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DoDiveActv.this,CheckoutActv.class);
                gotocheckout.putExtra("jenis_tiket", jenis_dive_baru);
                startActivity(gotocheckout);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(DoDiveActv.this,HomeActv.class);
                startActivity(gotoback);
            }
        });
    }
}
