package com.example.moviesqrcodeapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button listMoviesButton;
    private Button addMoviesButton;
    private ListView moviesListView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        MovieService service = RetrofitClient.getRetrofit().create(MovieService.class);
        listMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                service.getMovies().enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            movieList.clear();
                            movieList.addAll(response.body());
                            movieAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void init() {
        listMoviesButton = findViewById(R.id.listMoviesButton);
        addMoviesButton = findViewById(R.id.addMoviesButton);
        moviesListView = findViewById(R.id.moviesListView);
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList);
        moviesListView.setAdapter(movieAdapter);
    }
}