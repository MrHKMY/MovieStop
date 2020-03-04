package com.android.famousmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hakimi on 23/2/2020.
 */
public interface MovieApiService {

    @GET("movie/popular")
    Call <MoviePageResult> getPopularMovies(@Query("page") int page,@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call <MoviePageResult> getUpcomingMovies(@Query("page") int page,@Query("api_key") String api_key);

    @GET("tv/popular")
    Call <MoviePageResult> getPopularTV(@Query("page") int page,@Query("api_key") String api_key);

    @GET("movie/{id}/reviews")
    Call <ReviewPageResult> getReview(@Path("id") int movieId, @Query("api_key") String api_key);
}
