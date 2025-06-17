package com.example.tpraktikum5_h071231079;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private Set<String> genreSet = new HashSet<>();
    private LinearLayout genreLayout;
    private final String[] selectedGenre = { "All" };
    private ProgressBar progressBar;

    // For background processing
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Pastikan fragment_home.xml root-nya FrameLayout dengan ProgressBar overlay
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 1) Bind views
        progressBar     = view.findViewById(R.id.progressBar);
        recyclerView    = view.findViewById(R.id.bookRecyclerView);
        genreLayout     = view.findViewById(R.id.genreLinearLayout);
        EditText searchEditText = view.findViewById(R.id.searchEditText);

        // 2) Setup RecyclerView & Adapter (mulai kosong)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(getContext(), new ArrayList<>(), book -> {
            Intent intent = new Intent(getContext(), DetailBookActivity.class);
            intent.putExtra(DetailBookActivity.EXTRA_BOOK, book);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // 3) Ambil seluruh buku sekali (fast)
        bookList = BookRepository.getBooks();

        // 4) Setup genre buttons
        addGenreButtonIfNeeded("All", searchEditText);
        for (Book b : bookList) {
            addGenreButtonIfNeeded(b.getGenre(), searchEditText);
        }

        // 5) Setup search listener
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) { }
            @Override public void afterTextChanged(Editable s) { }
            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {
                loadAndFilter(s.toString(), selectedGenre[0]);
            }
        });

        // 6) Initial load
        loadAndFilter("", "All");

        return view;
    }

    private void addGenreButtonIfNeeded(String genre, EditText searchEditText) {
        if (!genreSet.contains(genre)) {
            genreSet.add(genre);

            Button button = new Button(requireContext());
            button.setText(genre);
            button.setAllCaps(false);
            button.setTextSize(14);
            button.setTextColor(Color.BLACK);
            button.setBackgroundResource(R.drawable.bg_genre);
            Typeface font = ResourcesCompat.getFont(requireContext(), R.font.poppins_medium);
            button.setTypeface(font);

            int padH = dpToPx(20), padV = dpToPx(4);
            button.setPadding(padH, padV, padH, padV);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, dpToPx(30)
            );
            params.setMargins(0, 0, dpToPx(12), 0);
            button.setLayoutParams(params);

            button.setOnClickListener(v -> {
                // Reset semua tombol
                for (int i = 0; i < genreLayout.getChildCount(); i++) {
                    View child = genreLayout.getChildAt(i);
                    if (child instanceof Button) {
                        child.setBackgroundResource(R.drawable.bg_genre);
                        ((Button) child).setTextColor(Color.BLACK);
                    }
                }
                // Highlight tombol terpilih
                button.setBackgroundResource(R.drawable.bg_genre_selected);
                button.setTextColor(Color.WHITE);
                selectedGenre[0] = genre;
                loadAndFilter(searchEditText.getText().toString(), genre);
            });

            genreLayout.addView(button);

            if (genre.equals("All")) {
                button.setBackgroundResource(R.drawable.bg_genre_selected);
                button.setTextColor(Color.WHITE);
            }
        }
    }

    /**
     * Jalankan filter di background, tampilkan ProgressBar,
     * sembunyikan RecyclerView selama proses, lalu update adapter
     * dan tampilkan RecyclerView di UI thread.
     */
    private void loadAndFilter(String keyword, String genre) {
        // Tampilkan loading, sembunyikan list
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        executor.execute(() -> {
            // Opsional: simulasi delay
            try { Thread.sleep(800); } catch (InterruptedException ignored) {}

            List<Book> filtered = new ArrayList<>();
            for (Book b : bookList) {
                boolean matchGenre = genre.equals("All")
                        || b.getGenre().equalsIgnoreCase(genre);
                boolean matchKey = b.getTitle().toLowerCase()
                        .contains(keyword.toLowerCase());
                if (matchGenre && matchKey) {
                    filtered.add(b);
                }
            }

            handler.post(() -> {
                // Update UI
                adapter.setFilteredList(filtered);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}
