package com.example.e_dive;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class RegisterTwoActv extends AppCompatActivity {

    LinearLayout btn_back;
    Button btncontinue, btn_add_photo ;
    ImageView pic_photo_register;
    EditText nama_lengkap, bio, dive_lisence;
    DatabaseReference reference;
    StorageReference storage;


    Uri photo_location;
    Integer photo_max = 1;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two_actv);

        getUsernameLocal();

        btn_back = findViewById(R.id.btn_back);
        btncontinue = findViewById(R.id.btncontinue);
        btn_add_photo = findViewById(R.id.btn_add_photo);
        pic_photo_register = findViewById(R.id.pic_photo_register);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        dive_lisence = findViewById(R.id.dive_lisence);

        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhoto();
            }
        });

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncontinue.setEnabled(false);
                btncontinue.setText("Loading ...");

                //menyimpan ke firebase
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                storage = FirebaseStorage.getInstance().getReference().child("PhotoUser").child(username_key_new);

                // validasi untuk file (apakah ada ?)
               if (photo_location != null) {
                    final StorageReference storageReference1 = storage.child(System.currentTimeMillis() + "." +
                            getFileExtension(photo_location));

                   storageReference1.putFile(photo_location).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                           if (task.isSuccessful()){
                               storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       String downloadUrl = uri.toString();
                                       reference.getRef().child("url_photo_profile").setValue(downloadUrl);
                                       reference.getRef().child("nama_lengkap").setValue(nama_lengkap.getText().toString());
                                       reference.getRef().child("dive_lisence").setValue(dive_lisence.getText().toString());
                                       reference.getRef().child("bio").setValue(bio.getText().toString());
                                       //berpidah activity
                                        Intent gotosuccess =  new Intent(RegisterTwoActv.this,SuccessActv.class);
                                        startActivity(gotosuccess);
                                   }
                               });
                           }
                       }
                   });
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoprev =  new Intent(RegisterTwoActv.this,RegisterOneActv.class);
                startActivity(backtoprev);
            }
        });
    }

    String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void  findPhoto(){
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pic_photo_register);
        }
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
