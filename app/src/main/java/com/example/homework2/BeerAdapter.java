package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers;

    public BeerAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View beerView = inflater.inflate(R.layout.item_beer, parent, false);
        ViewHolder viewHolder = new ViewHolder(beerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Beer beer = beers.get(position);

        holder.textView_name.setText(beer.getName());
        holder.textView_description.setText(beer.getDescription());

        Picasso.get().load(beer.getImage_url()).into(holder.imageView_beer);

        holder.imageView_beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FinalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("beer_name", beer.getName());
                bundle.putString("beer_details", beer.getDescription());
//                intent.putExtra("beer_name", beer.getName());
//                intent.putExtra("beer_details", beer.getDescription());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return beers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name;
        TextView textView_description;
        ImageView imageView_beer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_name);
            textView_description = itemView.findViewById(R.id.textView_description);
            imageView_beer = itemView.findViewById(R.id.imageView_beer);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), FinalActivity.class);
//                    v.getContext().startActivity(intent);
//                }
//            });
        }
    }
}
