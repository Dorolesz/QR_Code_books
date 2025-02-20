package com.example.qrcode_books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookService {

    //az összes könyv lekérése
    @GET("book")
    Call<List<Book>> getBooks();

    //új könyv hozzáadása
    @POST("book")
    Call<Book> createBook(@Body Book book);

    //könyv törlése
    @DELETE("book/{id}")
    Call<Void> deleteBook(@Path("id") int id);
}