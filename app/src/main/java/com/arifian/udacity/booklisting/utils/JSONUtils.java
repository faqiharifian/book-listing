package com.arifian.udacity.booklisting.utils;

import com.arifian.udacity.booklisting.entities.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faqih on 20/04/17.
 */

public class JSONUtils {
    public static List<Book> parseJSON(String jsonString){
        List<Book> books = new ArrayList<>();

        try{
            JSONObject rootJson = new JSONObject(jsonString);
            JSONArray itemsJson = rootJson.getJSONArray("items");
            for(int i = 0; i < itemsJson.length(); i++){
                JSONObject itemJson = itemsJson.getJSONObject(i);

                JSONObject volumeInfoJson = itemJson.getJSONObject("volumeInfo");
                String title = volumeInfoJson.getString("title");
                String[] authors = getAuthors(volumeInfoJson.getJSONArray("authors"));
                JSONObject imageLinksJson = volumeInfoJson.getJSONObject("imageLinks");
                String thumbnail = imageLinksJson.getString("thumbnail");

                JSONObject accessInfoJson = itemJson.getJSONObject("accessInfo");
                String link = accessInfoJson.getString("webReaderLink");

                Book book = new Book(title, link, thumbnail, authors);
                books.add(book);
            }
        }catch(JSONException je){
            je.printStackTrace();
        }

        return books;
    }

    private static String[] getAuthors(JSONArray authorsJson) {
        String[] authors = new String[authorsJson.length()];
        try{
            for(int i = 0; i < authorsJson.length(); i++){
                authors[i] = authorsJson.getString(i);
            }
        }catch(JSONException je){
            je.printStackTrace();
        }
        return authors;
    }
}
