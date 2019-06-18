package com.example.e_dive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActv extends AppCompatActivity {

    LinearLayout do_dive,do_rent,do_course;
    ConstraintLayout pramuka, bira, raja;
    CircleView btn_to_profile;
    ImageView photo_home_user;
    TextView nama_lengkap, bio;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actv);

        getUsernameLocal();

        do_dive = findViewById(R.id.do_dive);
        do_rent = findViewById(R.id.do_rent);
        do_course = findViewById(R.id.do_course);
        pramuka = findViewById(R.id.pramuka);
        bira = findViewById(R.id.bira);
        raja = findViewById(R.id.raja);
        btn_to_profile = findViewById(R.id.btn_to_profile);
        photo_home_user = findViewById(R.id.photo_home_user);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                Picasso.with(HomeActv.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        do_dive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodive =  new Intent(HomeActv.this,MyTicketDetailActv.class);
                gotodive.putExtra("jenis_tiket", "Dive");
                startActivity(gotodive);
            }
        });

        pramuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodivepramuka =  new Intent(HomeActv.this,DoDiveActv.class);
                //meletakan data ke intent
                gotodivepramuka.putExtra("jenis_tiket", "Pramuka Island");
                startActivity(gotodivepramuka);
            }
        });

        bira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodivepramuka =  new Intent(HomeActv.this,DoDiveActv.class);
                gotodivepramuka.putExtra("jenis_tiket", "Bira Island");
                startActivity(gotodivepramuka);
            }
        });
        raja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodivepramuka =  new Intent(HomeActv.this,DoDiveActv.class);
                gotodivepramuka.putExtra("jenis_tiket", "Raja Ampat Island");
                startActivity(gotodivepramuka);
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
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
