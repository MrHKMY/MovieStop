package com.android.famousmovies;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String theURL = "http://api.themoviedb.org/3/";
    private Call<MoviePageResult> call;
    private List<Movie> movieResults;
    private MovieAdapter mAdapter;
    private int totalPages;
    private static final int FIRST_PAGE = 1;
    private ProgressBar loadProgress;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        loadProgress = findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= totalPages) {
                    loadPage(page + 1);
                }
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        loadPage(FIRST_PAGE);


        FloatingActionButton fab1 = findViewById(R.id.fab_action1);
        FloatingActionButton fab2 = findViewById(R.id.fab_action2);
        FloatingActionButton fab3 = findViewById(R.id.fab_action3);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUpcoming(FIRST_PAGE);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage(FIRST_PAGE);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTVShow(FIRST_PAGE);
            }
        });

    }

    private void loadPage(final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(theURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        call = movieApiService.getPopularMovies(page, getString(R.string.api_key));

        call.enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {

                if (page == 1) {
                    loadProgress.setVisibility(View.GONE);
                    //Log.v(TAG, "Response code: " + response.toString());
                    assert response.body() != null;
                    movieResults = response.body().getMovieResult();
                    assert response.body() != null;
                    totalPages = response.body().getTotalPages();

                    mAdapter = new MovieAdapter(movieResults);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    //Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                    //Log.v(TAG, "Response code: " + response.code());
                    assert response.body() != null;
                    List<Movie> movies = response.body().getMovieResult();
                    for (Movie movie : movies) {
                        movieResults.add(movie);
                        mAdapter.notifyItemInserted(movieResults.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUpcoming(final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(theURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        call = movieApiService.getUpcomingMovies(page, getString(R.string.api_key));

        call.enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {

                if (page == 1) {
                    //Log.v(TAG, "Response code: " + response.toString());
                    assert response.body() != null;
                    movieResults = response.body().getMovieResult();
                    assert response.body() != null;
                    totalPages = response.body().getTotalPages();

                    mAdapter = new MovieAdapter(movieResults);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    //Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                    //Log.v(TAG, "Response code: " + response.code());
                    assert response.body() != null;
                    List<Movie> movies = response.body().getMovieResult();
                    for (Movie movie : movies) {
                        movieResults.add(movie);
                        mAdapter.notifyItemInserted(movieResults.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTVShow(final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(theURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        call = movieApiService.getPopularTV(page, getString(R.string.api_key));

        call.enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {

                if (page == 1) {
                    //Log.v(TAG, "Response code: " + response.toString());
                    assert response.body() != null;
                    movieResults = response.body().getMovieResult();
                    assert response.body() != null;
                    totalPages = response.body().getTotalPages();

                    mAdapter = new MovieAdapter(movieResults);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    //Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                    //Log.v(TAG, "Response code: " + response.code());
                    assert response.body() != null;
                    List<Movie> movies = response.body().getMovieResult();
                    for (Movie movie : movies) {
                        movieResults.add(movie);
                        mAdapter.notifyItemInserted(movieResults.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
