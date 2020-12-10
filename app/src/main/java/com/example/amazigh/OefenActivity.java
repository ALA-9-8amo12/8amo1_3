package com.example.amazigh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.io.IOException;


public class OefenActivity extends AppCompatActivity{

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

        Button audio = (Button) findViewById(R.id.ButtonAudio);

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
    }

public void speel_geluid(View v) {
    MediaPlayer mediaPlayer = new MediaPlayer();
    try {
        mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/amazigh-28582.appspot.com/o/Geluidsfragmenten%2FDieren%2001%2Fdieren01_egel.mp3?alt=media&token=821d78df-ac09-4901-aaf0-07313c98a4f5");
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mediaPlayer.prepare();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

}