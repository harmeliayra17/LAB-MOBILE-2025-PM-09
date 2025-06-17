package com.example.tpraktikum4_h071231079;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private Set<String> genreSet = new HashSet<>();
    private LinearLayout genreLayout;
    private final String[] selectedGenre = { "All" };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.bookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookList = BookRepository.getBooks();

        // Inisialisasi adapter
        adapter = new BookAdapter(getContext(), bookList, book -> {
            Intent intent = new Intent(getContext(), DetailBookActivity.class);
            intent.putExtra(DetailBookActivity.EXTRA_BOOK, book);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        genreLayout = view.findViewById(R.id.genreLinearLayout);
        EditText searchEditText = view.findViewById(R.id.searchEditText);

        // Tambahkan genre "All" terlebih dahulu
        addGenreButtonIfNeeded("All", searchEditText);

        for (Book book : bookList) {
            addGenreButtonIfNeeded(book.getGenre(), searchEditText);
        }

        // Listener pencarian
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {
                filterBooks(s.toString(), selectedGenre[0]);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Jalankan filter awal
        filterBooks("", selectedGenre[0]);
        return view;
    }


     //  Tambah button genre
    private void addGenreButtonIfNeeded(String genre, EditText searchEditText) {
        if (!genreSet.contains(genre)) {
            genreSet.add(genre);

            Button button = new Button(requireContext());
            button.setText(genre);
            button.setTextColor(Color.BLACK);
            button.setAllCaps(false);
            button.setBackgroundResource(R.drawable.bg_genre);
            button.setTextSize(14);

            int paddingHorizontal = dpToPx(20);
            int paddingVertical = dpToPx(4);
            button.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(30)
            );

            params.setMargins(dpToPx(0), 0, dpToPx(12), 0);
            button.setLayoutParams(params);

            Typeface font = ResourcesCompat.getFont(requireContext(), R.font.poppins_medium);
            button.setTypeface(font);

            button.setOnClickListener(v -> {
                // Reset tombolnya
                for (int i = 0; i < genreLayout.getChildCount(); i++) {
                    View child = genreLayout.getChildAt(i);
                    if (child instanceof Button) {
                        child.setBackgroundResource(R.drawable.bg_genre);
                        ((Button) child).setTextColor(Color.BLACK);
                    }
                }

                button.setBackgroundResource(R.drawable.bg_genre_selected);
                button.setTextColor(Color.WHITE);
                selectedGenre[0] = genre;
                filterBooks(searchEditText.getText().toString(), selectedGenre[0]);
            });

            genreLayout.addView(button);

            // Kalau genre all langsung aktif
            if (genre.equals("All")) {
                button.setBackgroundResource(R.drawable.bg_genre_selected);
                button.setTextColor(Color.WHITE);
            }
        }
    }

    // Filter daftar buku berdasarkan keyword dan genre.
    private void filterBooks(String keyword, String genre) {
        List<Book> filtered = new ArrayList<>();
        for (Book b : bookList) {
            boolean matchGenre = genre.equals("All") || b.getGenre().equalsIgnoreCase(genre);
            boolean matchKey = b.getTitle().toLowerCase().contains(keyword.toLowerCase());
            if (matchGenre && matchKey) {
                filtered.add(b);
            }
        }

        adapter.setFilteredList(filtered);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
