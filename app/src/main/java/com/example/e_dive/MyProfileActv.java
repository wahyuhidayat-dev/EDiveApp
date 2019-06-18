package com.example.e_dive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyProfileActv extends AppCompatActivity {

    LinearLayout item_my_ticket;
    Button edit_profile,btn_back_home,btn_sign_out;
    ImageView photo_profile;
    TextView nama_user, bio, nama_trip, location;

    DatabaseReference reference,reference2;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    RecyclerView myticket_place;
    ArrayList<MyTicketTrip> list;
    TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_actv);

        getUsernameLocal();

        item_my_ticket = findViewById(R.id.item_my_ticket);
        photo_profile = findViewById(R.id.photo_profile);
        nama_user = findViewById(R.id.nama_user);
        bio = findViewById(R.id.bio);
        nama_trip = findViewById(R.id.nama_trip);
        location = findViewById(R.id.location);
        edit_profile = findViewById(R.id.edit_profile);
        btn_back_home = findViewById(R.id.btn_back_home);
        btn_sign_out = findViewById(R.id.btn_sign_out);

        myticket_place = findViewById(R.id.myticket_place);
        myticket_place.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTicketTrip>();


        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_user.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                Picasso.with(MyProfileActv.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(photo_profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tohome =  new Intent(MyProfileActv.this,HomeActv.class);
                startActivity(tohome);
            }
        });



        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editprofile =  new Intent(MyProfileActv.this,EditProfileActv.class);
                startActivity(editprofile);
            }
        });

       reference2 = FirebaseDatabase.getInstance().getReference().child("MyTiketTrip").child(username_key_new);
       reference2.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   MyTicketTrip p = dataSnapshot1.getValue(MyTicketTrip.class);
                   list.add(p);
               }
               ticketAdapter = new TicketAdapter(MyProfileActv.this, list);
               myticket_place.setAdapter(ticketAdapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       btn_sign_out.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // menghapus nilai dari  username local
               SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString(username_key, null);
               editor.apply();
               Intent logout =  new Intent(MyProfileActv.this,SignInActv.class);
               startActivity(logout);
               finish();
           }
       });

    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }


}
