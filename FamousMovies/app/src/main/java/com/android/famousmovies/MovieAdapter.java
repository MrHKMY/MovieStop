package com.android.famousmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    private final MovieClickListener movieClickListener;

    public MovieAdapter(List<Movie> movieList, MovieClickListener movieClickListener) {
        this.movieList = movieList;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Context context = holder.imageView.getContext();
        Movie movie = movieList.get(position);
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imageView);
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
