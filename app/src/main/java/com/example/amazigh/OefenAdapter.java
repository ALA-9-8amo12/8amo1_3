package com.example.amazigh;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// KLasse aanmaken op basis van de abstracte klass FireBaseRecycleAdapter
// Een aantal methoden zijn verplicht
public class OefenAdapter extends FirebaseRecyclerAdapter<
        Oefen, OefenAdapter.CircViewholder> {

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
                                    int position, @NonNull Oefen model)
    {
        // Gegevens van circuit worden opgehaald uit model, en in viewholder gezet
        // model is een instantie van Circuit, dus gebruikt de Getters
        holder.AmaWoord.setText(model.getAmazigh_woord());
        holder.Nedwoord.setText(model.getWoorden());
        // Voor het plaatsen van een plaatje wordt de library Glide gebruikt.
        Glide.with(holder.itemView.getContext())
                .load(model.getFoto())
                .into(holder.Image);
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
        public CircViewholder(@NonNull View itemView)
        {
            super(itemView);
//            Name = itemView.findViewById(R.id.tvName);
            AmaWoord = itemView.findViewById(R.id.tvCountry);
            Nedwoord = itemView.findViewById(R.id.tvCity);
            Image = itemView.findViewById(R.id.FOTO);
        }
    }

}
