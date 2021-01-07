package com.example.amazigh;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    QuizAdapter adapter;
    private RecyclerView recyclerView;
    Query mBase;
    ArrayList<Integer> randomIndex = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        Integer cat_id = intent.getIntExtra("cat_id", 0);
        randomIndex = intent.getIntegerArrayListExtra("integers");

        mBase = FirebaseDatabase.getInstance().getReference().child("woorden").orderByChild("category_id").equalTo(cat_id);
        mBase.limitToFirst(randomIndex.get(0));

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Oefen> options
                = new FirebaseRecyclerOptions.Builder<Oefen>()
                .setQuery(mBase, Oefen.class)
                .build();

        adapter = new QuizAdapter(options);

        // Connecting Adapter class with the Recycler view
        recyclerView.setAdapter(adapter);


    }

    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}