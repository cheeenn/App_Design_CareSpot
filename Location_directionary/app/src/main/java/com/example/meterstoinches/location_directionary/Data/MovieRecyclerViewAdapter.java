package com.example.meterstoinches.location_directionary.Data;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meterstoinches.location_directionary.Model.Location;
import com.example.meterstoinches.location_directionary.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Location> movieList;

    public MovieRecyclerViewAdapter(Context context, List<Location> movies) {
            this.context = context;
            movieList = movies;

    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder viewHolder, int i) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView poster;
        TextView year;
        TextView type;
        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            context = ctx;
            title = (TextView) itemView.findViewById(R.id.movieTitleID);
            poster= (ImageView) itemView.findViewById(R.id.movieImageID);
            year = (TextView) itemView.findViewById(R.id.movieReleaseID);
            type = (TextView) itemView.findViewById(R.id.movieCatID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Row Trapped!", Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
}
