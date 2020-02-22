package com.android.famousmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hakimi on 22/2/2020.
 */
public class MovieAdapter extends RecyclerView.Adapter<MainActivity.MovieViewHolder> {

    private List<Movie> mMovieList;
    private LayoutInflater inflater;
    private Context mContext;

    public MovieAdapter (Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainActivity.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_movie, parent, false);
        MainActivity.MovieViewHolder viewHolder = new MainActivity.MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivity.MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Picasso.get()
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setmMovieList(List<Movie> mMovieList){
        this.mMovieList.clear();
        this.mMovieList.addAll(mMovieList);
    }
}
