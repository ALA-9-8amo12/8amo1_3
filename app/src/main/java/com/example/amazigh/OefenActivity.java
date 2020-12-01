package com.example.amazigh;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.bumptech.glide.Glide;


public class OefenActivity extends AppCompatActivity {

DatabaseReference mBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oefenen);
        mBase = FirebaseDatabase.getInstance().getReference();
        ImageView imageView = (ImageView) findViewById(R.id.FOTO);

        Glide.with(this)  // this
                .load("gs://amazigh-28582.appspot.com/Plaatjes/Dieren 01/dieren01_ezel.jpg")
                .placeholder(R.drawable.splashscreen)
                .into(imageView);    }
}