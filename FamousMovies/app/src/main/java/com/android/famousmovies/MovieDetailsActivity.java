package com.android.famousmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Hakimi on 29/2/2020.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private Movie mMovie;
    private TextView movieTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        movieTitle = findViewById(R.id.textView2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mMovie = (Movie) bundle.getSerializable("movie");
        populateActivity(mMovie);
    }

    private void populateActivity (Movie mMovie) {
        movieTitle.setText(mMovie.getOverview());
    }
}
