package com.android.famousmovies;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final String TAG = "MainActivity";
    private static final String theURL = "http://api.themoviedb.org/3/";
    private MovieApiService apiService;
    private Call<MoviePageResult> call;
    private List<Movie> movieResults;
    private MovieAdapter mAdapter;
    private int totalPages;
    private int currentSortMode = 1;
    private static final int FIRST_PAGE = 1;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= totalPages && currentSortMode != 3) {
                    loadPage(page + 1);
                }
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        loadPage(FIRST_PAGE);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadPage(final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(theURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        call = movieApiService.getPopularMovies(getString(R.string.api_key));

        call.enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {

                if (page == 1) {
                    Log.v(TAG, "Response code: " + response.toString());
                    assert response.body() != null;
                    movieResults = response.body().getMovieResult();
                    assert response.body() != null;
                    totalPages = response.body().getTotalPages();

                    mAdapter = new MovieAdapter(movieResults, new MovieClickListener() {
                        @Override
                        public void onMovieClick(Movie movie) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("movie", movie);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {

                    Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "Response code: " + response.code());
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


    private void getPopularMovie() {
        apiService.getPopularMovies(getString(R.string.api_key)).enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {
                Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Response code: " + response.code());
                assert response.body() != null;
                List<Movie> movies = response.body().getMovieResult();
                for (Movie movie : movies) {
                    movieResults.add(movie);
                    mAdapter.notifyItemInserted(movieResults.size() - 1);
                }

            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
