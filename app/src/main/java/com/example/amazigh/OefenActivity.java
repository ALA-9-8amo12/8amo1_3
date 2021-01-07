package com.example.amazigh;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class OefenActivity extends AppCompatActivity{

    OefenAdapter adapter;
    private ViewPager2 viewpager2;
    Query mBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);

        Intent intent = getIntent();
        Integer cat_id = intent.getIntExtra("cat_id", 0);

        mBase = FirebaseDatabase.getInstance().getReference().child("woorden").orderByChild("category_id").equalTo(cat_id);

        viewpager2 = findViewById(R.id.viewPager2);

        FirebaseRecyclerOptions<Oefen> options
                = new FirebaseRecyclerOptions.Builder<Oefen>()
                .setQuery(mBase, Oefen.class)
                .build();

        // Het aanmaken van de datadapter
        adapter = new OefenAdapter(options);

        // Connecting Adapter class with the Recycler view
        viewpager2.setAdapter(adapter);

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
}