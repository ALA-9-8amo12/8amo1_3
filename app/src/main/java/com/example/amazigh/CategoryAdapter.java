package com.example.amazigh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CategoryAdapter extends FirebaseRecyclerAdapter<
        Category, CategoryAdapter.CategoryViewholder> {
    public CategoryAdapter(
            @NonNull FirebaseRecyclerOptions<Category> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewholder holder,
                                    final int position, @NonNull Category category)
    {
        holder.category.setText(category.getCategory());
    }

    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);
        return new CategoryAdapter.CategoryViewholder(view);
    }


    // Subklasse aanmaken voor ViewHolder,
    // gebaseerd op de abstracte klasse RecyclerView.ViewHolder
    static class CategoryViewholder
            extends RecyclerView.ViewHolder {
        TextView category;
        public CategoryViewholder(@NonNull View itemView)
        {
            super(itemView);
            category = itemView.findViewById(R.id.tvCategory);

        }
    }

}
