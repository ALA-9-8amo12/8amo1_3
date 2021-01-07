package com.example.amazigh;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.io.IOException;

// KLasse aanmaken op basis van de abstracte klass FireBaseRecycleAdapter
// Een aantal methoden zijn verplicht
public class OefenAdapter extends FirebaseRecyclerAdapter<
        Oefen, OefenAdapter.CircViewholder> {

    Button ButtonAudio;
    // Verplichte methode, standaard
    public OefenAdapter(
            @NonNull FirebaseRecyclerOptions<Oefen> options)
    {
        super(options);
    }


    // Verplichte event listener, voor als de data wordt geladen
    // Wordt aangepast voor gebruik met klasse Circuit
    @Override
    protected void onBindViewHolder(@NonNull CircViewholder holder,
                                    int position, @NonNull final Oefen model)
    {

        final MediaPlayer mediaPlayer = new MediaPlayer();
        // Gegevens van circuit worden opgehaald uit model, en in viewholder gezet
        // model is een instantie van Circuit, dus gebruikt de Getters
        holder.AmaWoord.setText("Amazigh: " + model.getAmazigh_woord());
        holder.Nedwoord.setText("Nederlands: " + model.getWoord());
        // Voor het plaatsen van een plaatje wordt de library Glide gebruikt.
        Glide.with(holder.itemView.getContext())
                .load(model.getFoto())
                .into(holder.Image);

        holder.ButtonAudio.setOnClickListener(new View.OnClickListener() {
            boolean check = true;
            @Override
            public void onClick(View v) {
                try {
                    if(check) {
                        check = false;
                        mediaPlayer.setDataSource(model.getGeluid());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayer.reset();
                                check = true;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // Verplichte methode. Geeft als waarde een viewholder van het juiste type
    //

    @NonNull
    @Override
    public CircViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oefenen, parent, false);
        return new OefenAdapter.CircViewholder(view);
    }

    // Subklasse aanmaken voor ViewHolder,
    // gebaseerd op de abstracte klasse RecyclerView.ViewHolder
    static class CircViewholder
            extends RecyclerView.ViewHolder {
        TextView Nedwoord,AmaWoord;
        ImageView Image;
        Button ButtonAudio;
        public CircViewholder(@NonNull View itemView)
        {
            super(itemView);
//            Name = itemView.findViewById(R.id.tvName);
            AmaWoord = itemView.findViewById(R.id.tvCity);
            Nedwoord = itemView.findViewById(R.id.tvCountry);
            ButtonAudio = itemView.findViewById(R.id.ButtonAudio);
            Image = itemView.findViewById(R.id.FOTO);
        }
    }

}







    
