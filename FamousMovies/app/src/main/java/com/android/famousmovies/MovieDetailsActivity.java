package com.android.famousmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

/**
 * Created by Hakimi on 29/2/2020.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private Movie mMovie;
    private TextView movieOverview;
    private ImageView poster;
    private ImageView backdrop;
    private TextView voteTextView, titleTextView, nameTextView, titleToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        movieOverview = findViewById(R.id.textView2);
        poster = findViewById(R.id.posterImageView);
        backdrop = findViewById(R.id.backdropImageView);
        voteTextView = findViewById(R.id.voteTextView);
        titleTextView = findViewById(R.id.titleTextView);
        nameTextView = findViewById(R.id.nameTextView);
        titleToolbar = findViewById(R.id.movieTitleToolbar);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mMovie = (Movie) bundle.getSerializable("movie");
        populateActivity(mMovie);
    }

    private void populateActivity (Movie mMovie) {

        movieOverview.setText(mMovie.getOverview());
        Picasso.get()
                .load(mMovie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(poster);

        Picasso.get()
                .load(mMovie.getBackdropPath())
                .placeholder(R.color.colorPrimary)
                .into(backdrop);

        voteTextView.setText(mMovie.getVote());
        titleTextView.setText(mMovie.getTitle());
        nameTextView.setText(mMovie.getTvShowName());
        titleToolbar.setText(mMovie.getTitle());

    }
}
