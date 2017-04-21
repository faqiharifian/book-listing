package com.arifian.udacity.booklisting.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arifian.udacity.booklisting.R;
import com.arifian.udacity.booklisting.entities.Book;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faqih on 21/04/17.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>{
    List<Book> books;
    Context context;

    public BookRecyclerAdapter(Context context) {
        this.context = context;
        books = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleBookTextView.setText(book.getTitle());

        String authors = "";
        for(int i = 0; i < book.getAuthors().length; i++){
            authors += book.getAuthors()[i];
            if(i < book.getAuthors().length-1) authors += ", ";
        }
        holder.authorBookTextView.setText(authors);

        Glide.with(context).load(book.getThumbnail()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.bookProgressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.bookImageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> data) {
        books = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImageView;
        TextView titleBookTextView, authorBookTextView;
        ProgressBar bookProgressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            bookImageView = (ImageView) itemView.findViewById(R.id.image_book);
            titleBookTextView = (TextView) itemView.findViewById(R.id.text_book_title);
            authorBookTextView = (TextView) itemView.findViewById(R.id.text_book_author);
            bookProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_book);
        }
    }
}
