package com.arifian.udacity.booklisting;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arifian.udacity.booklisting.adapters.BookRecyclerAdapter;
import com.arifian.udacity.booklisting.entities.Book;
import com.arifian.udacity.booklisting.utils.JSONUtils;
import com.arifian.udacity.booklisting.view.SpacesItemDecoration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    public static final String KEY_QUERY = "queryStr";
    private final int ID_LOADER = 0;
    protected String queryStr;
    BookRecyclerAdapter bookAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        queryStr = getIntent().getStringExtra(KEY_QUERY);
        Log.e("QUERY", queryStr);

        RecyclerView bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_book);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        bookRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_space)));

        bookAdapter = new BookRecyclerAdapter(this);
        bookRecyclerView.setAdapter(bookAdapter);

        getSupportLoaderManager().initLoader(ID_LOADER, null, this).forceLoad();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_result, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewExpanded();
        searchView.setQuery(queryStr, true);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryStr = query;
                getSupportLoaderManager().restartLoader(ID_LOADER, null, SearchResultActivity.this).forceLoad();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        showProgressDialog();
        return new BookLoader(this, queryStr, bookAdapter);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        hideProgressDialog();
        bookAdapter.setBooks(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        hideProgressDialog();
        bookAdapter.setBooks(new ArrayList<Book>());
    }

    private void showProgressDialog() {
        progressDialog.show();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_bar);
    }

    private void hideProgressDialog() {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    public static class BookLoader extends AsyncTaskLoader<List<Book>>{
        private final String url = "https://www.googleapis.com/books/v1/volumes";
        Context context;
        String query;
        RecyclerView.Adapter adapter;

        public BookLoader(Context context, String query, RecyclerView.Adapter adapter) {
            super(context);
            this.context = context;
            this.query = query;
            this.adapter = adapter;
        }

        @Override
        public List<Book> loadInBackground() {
            List<Book> books = new ArrayList<>();
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(getParams());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    books = JSONUtils.parseJSON(readFromStream(inputStream));
                } else {
//                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
//                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }catch(IOException io){
                        io.printStackTrace();
                    }
                }
            }
            return books;
        }

        private String getParams(){
            Uri.Builder builder = Uri.parse(url).buildUpon();
            builder.appendQueryParameter("q", query);
            builder.appendQueryParameter("maxResult", "10");
            builder.appendQueryParameter("startIndex", String.valueOf(adapter.getItemCount()));
            return builder.toString();
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
    }
}
