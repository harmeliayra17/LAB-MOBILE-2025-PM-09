package com.example.tpraktikum5_h071231079;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpraktikum5_h071231079.Book;
import com.example.tpraktikum5_h071231079.BookAdapter;
import com.example.tpraktikum5_h071231079.DetailBookActivity;
import com.example.tpraktikum5_h071231079.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteBookFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_book, container, false);

        recyclerView = view.findViewById(R.id.bookRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookAdapter(
                getContext(),
                new ArrayList<>(),
                book -> {
                    Intent intent = new Intent(getContext(), DetailBookActivity.class);
                    intent.putExtra(DetailBookActivity.EXTRA_BOOK, book);
                    startActivity(intent);
                }
        );
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        progressBar.setVisibility(View.VISIBLE); // Tampilkan loading

        executor.execute(() -> {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Book> favorites = new ArrayList<>();
            for (Book b : BookRepository.getBooks()) {
                if (b.isFavorite()) {
                    favorites.add(b);
                }
            }

            handler.post(() -> {
                adapter.setFilteredList(favorites);
                progressBar.setVisibility(View.GONE); // Sembunyikan loading
            });
        });
    }
}
