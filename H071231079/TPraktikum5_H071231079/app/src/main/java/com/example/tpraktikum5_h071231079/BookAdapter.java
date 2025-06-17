package com.example.tpraktikum5_h071231079;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    private List<Book> bookList;
    private final Context context;
    private final OnItemClickListener listener;

    public BookAdapter(Context context, List<Book> bookList, OnItemClickListener listener) {
        this.context = context;
        this.bookList = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book, listener);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setFilteredList(List<Book> filteredList) {
        this.bookList = filteredList;
        notifyDataSetChanged();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title, author, rating, genre, description;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover       = itemView.findViewById(R.id.bookCover);
            title       = itemView.findViewById(R.id.bookTitle);
            author      = itemView.findViewById(R.id.bookAuthor);
            rating      = itemView.findViewById(R.id.bookRating);
            genre       = itemView.findViewById(R.id.bookGenre);
            description = itemView.findViewById(R.id.bookDescription);
        }

        public void bind(Book book, OnItemClickListener listener) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            rating.setText(String.format("%.1f", book.getRating()));
            genre.setText(book.getGenre());
            description.setText(book.getDescription());

            if (book.getCoverImg() != 0) {
                cover.setImageResource(book.getCoverImg());
            } else if (book.getCoverUri() != null) {
                cover.setImageURI(Uri.parse(book.getCoverUri()));
            }

            itemView.setOnClickListener(v -> listener.onItemClick(book));
        }
    }
}
