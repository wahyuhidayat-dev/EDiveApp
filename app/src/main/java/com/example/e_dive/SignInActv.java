package com.example.e_dive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActv extends AppCompatActivity {

    TextView btn_new_account;
    Button btnsignin;
    EditText xusername, xpassword;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actv);

        btn_new_account = findViewById(R.id.btn_new_account);
        btnsignin = findViewById(R.id.btnsignin);
        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone =  new Intent(SignInActv.this,RegisterOneActv.class);
                startActivity(gotoregisterone);
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnsignin.setEnabled(false);
                btnsignin.setText("Loading ...");

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Empty!",Toast.LENGTH_LONG).show();
                    btnsignin.setEnabled(true);
                    btnsignin.setText("SIGN IN");

                }else {
                    if (password.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password Empty!",Toast.LENGTH_LONG).show();
                        btnsignin.setEnabled(true);
                        btnsignin.setText("SIGN IN");
                    }
                    else {
                        reference = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(username);

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    //ambil password from firebase
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                    //validasi password dengan password firebase
                                    if (password.equals(passwordFromFirebase)){

                                        //simpan ke local HP
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        //berpindah Activity
                                        Intent gotohome =  new Intent(SignInActv.this,HomeActv.class);
                                        startActivity(gotohome);
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Wrong Password!",Toast.LENGTH_LONG).show();
                                        btnsignin.setEnabled(true);
                                        btnsignin.setText("SIGN IN");
                                    }


                                }else {
                                    Toast.makeText(getApplicationContext(),"Username Not Found!",Toast.LENGTH_LONG).show();
                                    btnsignin.setEnabled(true);
                                    btnsignin.setText("SIGN IN");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            }
        });
    }
}
