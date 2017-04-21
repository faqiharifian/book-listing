package com.arifian.udacity.booklisting;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arifian.udacity.booklisting.adapters.BookRecyclerAdapter;
import com.arifian.udacity.booklisting.entities.Book;
import com.arifian.udacity.booklisting.utils.JSONUtils;
import com.arifian.udacity.booklisting.utils.NetworkUtils;
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
    public static final String KEY_QUERY = "query";
    private final int ID_LOADER = 0;
    protected String queryStr;
    BookRecyclerAdapter bookAdapter;
    ProgressDialog progressDialog;
    SearchView searchView;
    RecyclerView bookRecyclerView;
    TextView errorNoInternetTextView, errorEmptyTextView, errorServerTextView;

    BroadcastReceiver receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        queryStr = getIntent().getStringExtra(KEY_QUERY);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        errorNoInternetTextView = (TextView) findViewById(R.id.text_error_no_internet);
        errorEmptyTextView = (TextView) findViewById(R.id.text_error_empty);
        errorServerTextView = (TextView) findViewById(R.id.text_error_server);

        bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_book);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else
            bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        bookRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_space)));

        bookAdapter = new BookRecyclerAdapter(this);
        bookRecyclerView.setAdapter(bookAdapter);

        getSupportLoaderManager().initLoader(ID_LOADER, null, this).forceLoad();

        if(!NetworkUtils.isConnected(this)){
            showErrorNoInternet();
        }else{
            hideErrors();
        }

        receiver = new NetworkChangeReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_QUERY, queryStr);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        queryStr = savedInstanceState.getString(KEY_QUERY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_result, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();
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
        hideErrors();
        return new BookLoader(this, queryStr, bookAdapter);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        hideProgressDialog();
        if(data.size() == 0) showErrorEmpty();
        bookAdapter.setBooks(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        hideProgressDialog();
        showErrorEmpty();
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

    private void showErrorNoInternet(){
        errorNoInternetTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorEmpty(){
        bookRecyclerView.setVisibility(View.GONE);
        errorEmptyTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorServer(){
        bookRecyclerView.setVisibility(View.GONE);
        errorServerTextView.setVisibility(View.VISIBLE);
    }

    private void hideErrors(){
        bookRecyclerView.setVisibility(View.VISIBLE);
        errorEmptyTextView.setVisibility(View.GONE);
        errorNoInternetTextView.setVisibility(View.GONE);
        errorServerTextView.setVisibility(View.GONE);
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
                URL url = new URL(getUrlWithParams());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    String jsonStr = readFromStream(inputStream);
                    books = JSONUtils.parseJSON(jsonStr);
                } else {
                    ((SearchResultActivity)context).showErrorServer();
                }
            } catch (IOException e) {
                e.printStackTrace();
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

        private String getUrlWithParams(){
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

    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            if(!NetworkUtils.isConnected(context)){
                showErrorNoInternet();
            }else{
                hideErrors();
                if(bookAdapter.getItemCount() == 0){
                    getSupportLoaderManager().restartLoader(ID_LOADER, null, SearchResultActivity.this).forceLoad();
                }
            }
        }
    }
}
