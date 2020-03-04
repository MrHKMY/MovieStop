package com.android.famousmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hakimi on 29/2/2020.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String theURL = "http://api.themoviedb.org/3/";
    private Movie mMovie;
    private TextView movieOverview;
    private ImageView poster;
    private ImageView backdrop;
    private TextView voteTextView, titleTextView, nameTextView, titleToolbar, reviewMainClick, review1;
    private List<Review> reviewResult;
    private ReviewAdapter rAdapter;

    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;


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
        reviewMainClick = findViewById(R.id.readReview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mMovie = (Movie) bundle.getSerializable("movie");
        populateActivity(mMovie);

        reviewMainClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<ReviewPageResult> call2;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(theURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                call2 = movieApiService.getReview(mMovie.getId(), getString(R.string.api_key));
                final int id = mMovie.getId();

                call2.enqueue(new Callback<ReviewPageResult>() {
                    @Override
                    public void onResponse(Call<ReviewPageResult> call, Response<ReviewPageResult> response) {
                        if (id != 0) {
                            reviewResult = response.body().getMovieReview();
                            rAdapter = new ReviewAdapter(reviewResult);
                            recyclerView2.setAdapter(rAdapter);
                        } else {
                            List<Review> reviews = response.body().getMovieReview();
                            for (Review review : reviews) {
                                reviewResult.add(review);
                                rAdapter.notifyItemInserted(reviewResult.size() - 1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewPageResult> call, Throwable t) {
                    }
                });
            }
        });
    }

    private void populateActivity(Movie mMovie) {
        movieOverview.setText(mMovie.getOverview());
        Picasso.get()
                .load(mMovie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(poster);

        Picasso.get()
                .load(mMovie.getBackdropPath())
                .placeholder(R.color.colorPrimary)
                .into(backdrop);

        voteTextView.setText(mMovie.getVote() + "/10");
        titleTextView.setText(mMovie.getTitle());
        nameTextView.setText(mMovie.getTvShowName());
        if (mMovie.getTitle() != null) {
            titleToolbar.setText(mMovie.getTitle());
        } else {
            titleToolbar.setText(mMovie.getTvShowName());
        }
    }
}
