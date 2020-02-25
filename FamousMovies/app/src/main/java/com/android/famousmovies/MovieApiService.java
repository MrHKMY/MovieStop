package com.android.famousmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hakimi on 23/2/2020.
 */
public interface MovieApiService {

    @GET("movie/popular")
    Call <MovieClass> getPopularMovies(@Query("api_key") String api_key);
}
