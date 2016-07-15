package org.jcapps.androidbookfetch;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
            JSONObject bookJSON = new JSONObject(responseFromServer);
            this.title = bookJSON.getString("Title");
            this.author = bookJSON.getString("Author");
            this.yearReleased = bookJSON.getInt("YearReleased");
            this.setting = bookJSON.getString("Setting");
        } catch (IOException ex) {
            Log.i("HTTP", "Error with HTTP Request");
        } catch (JSONException ex) {
            Log.i("JSON","Error parsing JSON");
        }
    }

    public Book() {
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

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", yearReleased=" + yearReleased +
                ", setting='" + setting + '\'' +
                '}';
    }
}
