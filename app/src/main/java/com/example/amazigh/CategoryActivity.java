package com.example.amazigh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference mBase;
    CategoryAdapter adapter;
    ArrayList<Integer> randomIndex = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mBase = FirebaseDatabase.getInstance().getReference().child("category");

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FirebaseRecyclerOptions<Category> options
                = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(mBase, Category.class)
                .build();

        adapter = new CategoryAdapter(options);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent getintent = getIntent();
                                String type = getintent.getStringExtra("type");
                                if (type.equals("oefen")) {
                                    Intent intent = new Intent(CategoryActivity.this, OefenActivity.class);
                                    intent.putExtra("cat_id", position + 1);
                                    startActivity(intent);
                                } else if (type.equals("quiz")) {
                                    if (position == 8) {
                                        for (int i = 1; i <= 10; i++) {
                                            randomIndex.add(i);
                                        }
                                        Collections.shuffle(randomIndex);

                                    } else {
                                        for (int i = 1; i <= 15; i++) {
                                            randomIndex.add(i);
                                        }
                                        Collections.shuffle(randomIndex);
                                    }
                                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                                    intent.putExtra("cat_id", position + 1);
                                    intent.putExtra("integers", randomIndex);
                                    startActivity(intent);
                                }
                            }


                            @Override
                            public void onLongItemClick(View view, int position) {
                            // dit is alleen voor lange clicks. word niet gebruikt bij deze functie
                            }

                        }
                )
        );

        recyclerView.setAdapter(adapter);
    }


    // Starten en stoppen van de adapter
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    boolean contains(ArrayList<Integer> list, Integer name) {
        for (Integer item : list) {
            if (item.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
