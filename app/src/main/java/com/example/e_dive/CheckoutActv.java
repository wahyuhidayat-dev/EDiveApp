package com.example.e_dive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class CheckoutActv extends AppCompatActivity {

    Button btn_pay_ticket, btn_plus,btn_minus;
    LinearLayout btn_back;
    TextView jumlah_ticket,total_harga, harga_ticket,nama_trip,location,ketentuan;
    Integer valuejumlahticket = 1;
    Integer valuetotalharga = 0;
    Integer valuehargaticket = 0;

    DatabaseReference reference,reference2;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    String date_trip = "";
    String time_trip = "";

    //generate nomor secara random
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_actv);

        getUsernameLocal();

        btn_pay_ticket = findViewById(R.id.btn_pay_ticket);
        btn_back = findViewById(R.id.btn_back);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        jumlah_ticket = findViewById(R.id.jumlah_ticket);
        total_harga = findViewById(R.id.total_harga);
        harga_ticket = findViewById(R.id.harga_ticket);
        nama_trip = findViewById(R.id.nama_trip);
        location = findViewById(R.id.location);
        ketentuan = findViewById(R.id.ketentuan);

        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String jenis_dive_baru = bundle.getString("jenis_tiket");

        //setting value baru untuk qty tiket
        jumlah_ticket.setText(valuejumlahticket.toString());

        //setting Currrency Rupiah
        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //secara default hide btn_minus
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);

        //mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Trip").child(jenis_dive_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_trip.setText(dataSnapshot.child("nama_trip").getValue().toString());
                location.setText(dataSnapshot.child("location").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

                date_trip = dataSnapshot.child("date_trip").getValue().toString();
                time_trip = dataSnapshot.child("time_trip").getValue().toString();

                valuehargaticket = Integer.valueOf(dataSnapshot.child("harga_pax").getValue().toString());
                harga_ticket.setText(formatRupiah.format((double) valuehargaticket));
                valuetotalharga = valuehargaticket * valuejumlahticket;
                total_harga.setText(formatRupiah.format((double) valuetotalharga));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahticket-=1;
                jumlah_ticket.setText(valuejumlahticket.toString());
                if (valuejumlahticket < 2){
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }
                valuetotalharga = valuehargaticket * valuejumlahticket;
                total_harga.setText(formatRupiah.format((double) valuetotalharga));
                //total_harga.setText("Rp. "+ valuetotalharga+",-");
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahticket+=1;
                jumlah_ticket.setText(valuejumlahticket.toString());
                if (valuejumlahticket > 1){
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }
                valuetotalharga = valuehargaticket * valuejumlahticket;
                total_harga.setText(formatRupiah.format((double) valuetotalharga));
            }
        });



        btn_pay_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_pay_ticket.setEnabled(false);
                btn_pay_ticket.setText("Loading ...");
                //menyimpan data user ke firebase dan memnuat table baru "MyTiketTrip"
                reference2 = FirebaseDatabase.getInstance().getReference().child("MyTiketTrip")
                        .child(username_key_new).child(nama_trip.getText().toString() + nomor_transaksi);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference2.getRef().child("id_ticket").setValue(nama_trip.getText().toString() + nomor_transaksi);
                        reference2.getRef().child("nama_trip").setValue(nama_trip.getText().toString());
                        reference2.getRef().child("lokasi").setValue(location.getText().toString());
                        reference2.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference2.getRef().child("date_trip").setValue(date_trip);
                        reference2.getRef().child("time_trip").setValue(time_trip);
                        reference2.getRef().child("jumlah_ticket").setValue(valuejumlahticket.toString());
                        reference2.getRef().child("total_harga").setValue(valuetotalharga.intValue());

                        Intent successorder =  new Intent(CheckoutActv.this,SuccessOrder.class);
                        startActivity(successorder);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotobackdive =  new Intent(CheckoutActv.this,HomeActv.class);
                startActivity(gotobackdive);
            }
        });

    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
