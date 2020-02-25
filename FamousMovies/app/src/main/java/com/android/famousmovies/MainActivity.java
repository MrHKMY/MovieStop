package com.android.famousmovies;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private static final String TAG = "MainActivity";
    private static final String theURL = "https://api.themoviedb.org/3/";
    private MovieApiService apiService;
    //private ArrayList<MovieClass> resultsList;
    private List<MovieClass> movieResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        List<MovieClass> movies = new ArrayList<>();
        //resultsList = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            movies.add(new MovieClass());
        }
        mAdapter.setMovieList(movies);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //#############################################################
        //#############################################################


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(theURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MovieApiService.class);

        getPopularMovie();


        //MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        //Call<List<MovieClass>> call = movieApiService.getPopularMovies(getString(R.string.api_key));

        /*call.enqueue(new Callback<List<MovieClass>>() {
            @Override
            public void onResponse(Call<List<MovieClass>> call, Response<List<MovieClass>> response) {

                List<MovieClass> aaa = response.body();
                //Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                //Log.v(TAG, "onResponse are called :" + response.code() + response);
                mAdapter.setMovieList(aaa);
            }

            @Override
            public void onFailure(Call<List<MovieClass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something is wrong : " + t, Toast.LENGTH_LONG).show();
            }
        });*/
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

    private void getPopularMovie() {
        apiService.getPopularMovies(getString(R.string.api_key)).enqueue(new Callback<MovieClass>() {
            @Override
            public void onResponse(Call<MovieClass> call, Response<MovieClass> response) {
                Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Response code: " + response.body());

                //movieResults = response.body().getMovieResult();
                //mAdapter = new MovieAdapter(movieResults);
                //mRecyclerView.setAdapter(mAdapter);
                //mAdapter = new MovieAdapter(movieResults);
                //mRecyclerView.setAdapter(response.body().toString());

            }

            @Override
            public void onFailure(Call<MovieClass> call, Throwable t) {

            }
        });

    }
}
