package com.example.tpraktikum4_h071231079;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpraktikum4_h071231079.Book;
import com.example.tpraktikum4_h071231079.BookAdapter;
import com.example.tpraktikum4_h071231079.BookRepository;
import com.example.tpraktikum4_h071231079.DetailBookActivity;
import com.example.tpraktikum4_h071231079.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBookFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_book, container, false);

        recyclerView = view.findViewById(R.id.bookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookAdapter(
                getContext(),
                new ArrayList<>(),
                book -> {
                    // buka detail sama seperti HomeFragment
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
        // setiap kali fragment muncul, ambil daftar terbaru
        List<Book> favorites = new ArrayList<>();
        for (Book b : BookRepository.getBooks()) {
            if (b.isFavorite()) {
                favorites.add(b);
            }
        }
        adapter.setFilteredList(favorites);
    }
}
