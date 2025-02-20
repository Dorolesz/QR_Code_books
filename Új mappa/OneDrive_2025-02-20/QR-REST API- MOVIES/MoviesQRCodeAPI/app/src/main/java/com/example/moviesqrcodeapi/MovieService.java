package com.example.moviesqrcodeapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MovieService {

    //az összes film lekérése
    @GET("movie")
    Call<List<Movie>> getMovies();

    //új film hozzáadása
    @POST("movie")
    Call<Movie> createMovie(@Body Movie movie);

    //film törlése
    @DELETE("movie/{id}")
    Call<Void> deleteMovie(@Path("id") int id);
}
