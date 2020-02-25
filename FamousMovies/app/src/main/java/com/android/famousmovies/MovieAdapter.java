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

    private List<MovieClass> mMovieList;
    private LayoutInflater inflater;
    private Context mContext;

    public MovieAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Context context = holder.imageView.getContext();
        MovieClass movie = mMovieList.get(position);
        Picasso.get()
                .load(movie.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovieList(List<MovieClass> mMovieList) {
        this.mMovieList.clear();
        this.mMovieList = mMovieList;
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
