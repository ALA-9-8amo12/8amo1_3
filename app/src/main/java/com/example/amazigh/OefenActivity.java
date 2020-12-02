package com.example.amazigh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


public class OefenActivity extends AppCompatActivity {

OefenAdapter adapter;
   private RecyclerView recyclerView;
DatabaseReference mBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);
        mBase = FirebaseDatabase.getInstance().getReference().child("woorden");

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        FirebaseRecyclerOptions<Oefen> options
                = new FirebaseRecyclerOptions.Builder<Oefen>()
                .setQuery(mBase, Oefen.class)
                .build();

        // Het aanmaken van de datadapter
        adapter = new OefenAdapter(options);

        // Connecting Adapter class with the Recycler view
        recyclerView.setAdapter(adapter);


//        ImageView imageView = (ImageView) findViewById(R.id.FOTO);
//
//        Glide.with(this)  // this
//                .load("gs://amazigh-28582.appspot.com/Plaatjes/Dieren 01/dieren01_ezel.jpg")
//                .placeholder(R.drawable.splashscreen)
//                .into(imageView);    }




    }
    // Starten en stoppen van de adapter
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }}