package com.example.moviesqrcodeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.movies_list_items, viewGroup, false);

        TextView directorTextView = view.findViewById(R.id.directorTextView);
        TextView durationTextView = view.findViewById(R.id.durationTextView);
        TextView ratingTextView = view.findViewById(R.id.ratingTextView);
        TextView categoryTextView = view.findViewById(R.id.categoryTextView);
        Button deleteButton = view.findViewById(R.id.deleteButton);

        Movie movie = movies.get(i);

        directorTextView.setText(movie.getDirector());
        int duration = movie.getTime();
        durationTextView.setText(duration / 60 + " óra" + duration % 60 + " perc");
        ratingTextView.setText(String.valueOf(movie.getRating()));
        categoryTextView.setText(movie.getCategory());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieService service = RetrofitClient.getRetrofit().create(MovieService.class);
                service.deleteMovie(movie.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            movies.remove(movie);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sikeres törlés", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sikertelen törlés, " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }
}
