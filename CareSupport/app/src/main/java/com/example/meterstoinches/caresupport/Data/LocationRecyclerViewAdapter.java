package com.example.meterstoinches.caresupport.Data;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meterstoinches.caresupport.Activity.Location_Detail_activity_a8;
import com.example.meterstoinches.caresupport.Model.Location;
import com.example.meterstoinches.caresupport.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Location> movieList;

    public LocationRecyclerViewAdapter(Context context, List<Location> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public LocationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_detail,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Location movie = movieList.get(i);
        String posterLink = movie.getPoster();
        viewHolder.title.setText(movie.getTitle());
        viewHolder.type.setText(movie.getMovieType());
        Picasso.with(context)
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(viewHolder.poster);
        viewHolder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView poster;
        TextView year;
        TextView type;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            title = (TextView) itemView.findViewById(R.id.movieTitleID);
            poster = (ImageView) itemView.findViewById(R.id.movieImageID);
            year = (TextView) itemView.findViewById(R.id.movieReleaseID);
            type = (TextView) itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"Row Trapped!", Toast.LENGTH_LONG).show();
                    Location movie = movieList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Location_Detail_activity_a8.class);
                    intent.putExtra("movie", movie);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
