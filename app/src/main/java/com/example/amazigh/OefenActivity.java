package com.example.amazigh;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class OefenActivity extends AppCompatActivity {

    OefenAdapter adapter;
   private RecyclerView recyclerView;
    Query mBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);

        Intent intent = getIntent();
        Integer cat_id = intent.getIntExtra("cat_id", 0);

        mBase = FirebaseDatabase.getInstance().getReference().child("woorden").orderByChild("category_id").equalTo(cat_id);

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