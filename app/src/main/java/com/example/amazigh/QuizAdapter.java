package com.example.amazigh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class QuizAdapter extends FirebaseRecyclerAdapter<
        Oefen, QuizAdapter.QuizViewholder> {

    public QuizAdapter(
            @NonNull FirebaseRecyclerOptions<Oefen> options)
    {
        super(options);
    }


    // Verplichte event listener, voor als de data wordt geladen
    // Wordt aangepast voor gebruik met klasse Circuit
    @Override
    protected void onBindViewHolder(@NonNull QuizAdapter.QuizViewholder holder,
                                    int position, @NonNull Oefen model)
    {
        // Gegevens van circuit worden opgehaald uit model, en in viewholder gezet
        // model is een instantie van Circuit, dus gebruikt de Getters
//        holder.AmaWoord.setText("Amazigh: " + model.getAmazigh_woord());
//        holder.Nedwoord.setText("Nederlands: " + model.getWoord());
        // Voor het plaatsen van een plaatje wordt de library Glide gebruikt.
        Glide.with(holder.itemView.getContext())
                .load(model.getFoto())
                .into(holder.Image);
    }

    // Verplichte methode. Geeft als waarde een viewholder van het juiste type
    //

    @NonNull
    @Override
    public QuizAdapter.QuizViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                                          int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz, parent, false);
        return new QuizAdapter.QuizViewholder(view);
    }

    // Subklasse aanmaken voor ViewHolder,
    // gebaseerd op de abstracte klasse RecyclerView.ViewHolder
    static class QuizViewholder
            extends RecyclerView.ViewHolder {
//        TextView Nedwoord,AmaWoord;
        ImageView Image;
//        Button ButtonAudio;
        public QuizViewholder(@NonNull View itemView)
        {
            super(itemView);
//            Name = itemView.findViewById(R.id.tvName);
//            AmaWoord = itemView.findViewById(R.id.tvCity);
//            Nedwoord = itemView.findViewById(R.id.tvCountry);
//            ButtonAudio = itemView.findViewById(R.id.ButtonAudio);
            Image = itemView.findViewById(R.id.FOTO);
        }
    }
}
