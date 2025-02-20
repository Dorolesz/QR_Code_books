package com.example.qrcode_books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return books.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView temaTextView = view.findViewById(R.id.temaTextView);
        TextView lapokTextView = view.findViewById(R.id.lapokTextView);
        TextView szerzoTextView = view.findViewById(R.id.szerzoTextView);
        Button deleteButton = view.findViewById(R.id.deleteButton);

        Book book = books.get(i);

        temaTextView.setText(book.getTema());
        int lapok = book.getLapok();
        lapokTextView.setText(lapok + " oldal");
        szerzoTextView.setText(book.getSzerzo());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookService service = RetrofitClient.getRetrofit().create(BookService.class);
                service.deleteBook(book.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            books.remove(book);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sikeres törlés", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sikertelen törlés, " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Hiba történt: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}