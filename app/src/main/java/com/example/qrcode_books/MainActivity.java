package com.example.qrcode_books;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

    private Button listBooksButton;
    private Button addBooksButton;
    private ListView booksListView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

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



        BookService service = RetrofitClient.getRetrofit().create(BookService.class);
        listBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                service.getBooks().enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            bookList.clear();
                            bookList.addAll(response.body());
                            bookAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void init() {
        listBooksButton = findViewById(R.id.listBooksButton);
        addBooksButton = findViewById(R.id.addBooksButton);
        booksListView = findViewById(R.id.booksListView);
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        booksListView.setAdapter(bookAdapter);
    }
}