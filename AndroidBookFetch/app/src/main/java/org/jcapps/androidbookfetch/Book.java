package org.jcapps.androidbookfetch;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JC on 7/15/16.
 */
public class Book {

    final String BOOK_URL = "https://james-ruby-book.herokuapp.com/";

    private String author;
    private String title;
    private int yearReleased;
    private String setting;

    private OkHttpClient http = new OkHttpClient();
    private Request req;
    private Response res;

    private void getBookFromAPI()  {

        try {
            req = new Request.Builder().url(BOOK_URL).build();
            res = http.newCall(req).execute();
            String responseFromServer = res.body().toString();
        } catch (IOException ex){
            Log.i("HTTP", "Error with HTTP Request");
        }
    }

    public Book(String author, String title, int yearReleased, String setting) {
        this.author = author;
        this.title = title;
        this.yearReleased = yearReleased;
        this.setting = setting;
        getBookFromAPI();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public String getSetting() {
        return setting;
    }
}
