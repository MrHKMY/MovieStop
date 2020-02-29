package com.android.famousmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hakimi on 22/2/2020.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    //private final MovieClickListener movieClickListener;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
       // this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final Context context = holder.imageView.getContext();
        final Movie movie = movieList.get(position);
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item Clicked : " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (context, MovieDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void setMovieList(List<Movie> mMovieList) {
        this.movieList.clear();
        this.movieList = mMovieList;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public RelativeLayout parentLayout;

        public MovieViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            parentLayout = view.findViewById(R.id.parent_layout);
        }
    }
}
