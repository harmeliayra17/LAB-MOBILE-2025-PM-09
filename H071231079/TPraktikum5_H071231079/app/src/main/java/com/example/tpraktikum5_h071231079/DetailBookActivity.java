package com.example.tpraktikum5_h071231079;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class DetailBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "extra_book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        // Ambil salinan Book dari Intent
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        if (book == null) {
            finish();
            return;
        }

        // Cari instance asli di repository
        Book fetched = BookRepository.getBookById(book.getId());
        final Book realBook = (fetched != null) ? fetched : book;

        // Bind semua view
        TextView title             = findViewById(R.id.textTitle);
        ImageView cover            = findViewById(R.id.imageCover);
        TextView author            = findViewById(R.id.textAuthor);
        TextView genre             = findViewById(R.id.textGenre);
        TextView rating            = findViewById(R.id.textRating);
        TextView sinopsis          = findViewById(R.id.textSinopsis);
        ImageView backButton       = findViewById(R.id.backButton);
        ImageView bookmarkButton   = findViewById(R.id.bookmarkButton);
        ImageView addFavButton     = findViewById(R.id.button_addfav);
        LinearLayout reviewContainer = findViewById(R.id.reviewContainer);

        // Tampilkan data buku dari realBook
        title.setText(realBook.getTitle());
        author.setText("by " + realBook.getAuthor());
        genre.setText(realBook.getGenre());
        rating.setText(String.format(Locale.US, "%.1f/5.0", realBook.getRating()));
        sinopsis.setText(realBook.getDescription());

        if (realBook.getCoverImg() != 0) {
            cover.setImageResource(realBook.getCoverImg());
        } else if (realBook.getCoverUri() != null) {
            cover.setImageURI(Uri.parse(realBook.getCoverUri()));
        }
        cover.setClipToOutline(true);

        // Helper untuk update ikon
        Runnable updateIcons = () -> {
            if (realBook.isFavorite()) {
                bookmarkButton.setImageResource(R.drawable.add_favbook);
                addFavButton.setImageResource(R.drawable.add_fav);
            } else {
                bookmarkButton.setImageResource(R.drawable.white_add_favbook);
                addFavButton.setImageResource(R.drawable.add_fav_before);
            }
        };
        updateIcons.run();

        // Back button
        backButton.setOnClickListener(v -> onBackPressed());

        // Toggle favorite lewat bookmarkButton
        View.OnClickListener toggleFavorite = v -> {
            boolean newState = !realBook.isFavorite();
            realBook.setFavorite(newState);
            updateIcons.run();
        };
        bookmarkButton.setOnClickListener(toggleFavorite);
        addFavButton.setOnClickListener(toggleFavorite);

        // Render reviews dari realBook
        for (String review : realBook.getReviews()) {
            // 1) Create a container for each review
            LinearLayout container = new LinearLayout(this);
            container.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            // bottom margin = 12dp
            params.setMargins(0, 0, 0, dpToPx(12));
            container.setLayoutParams(params);

            // 2) Use your pill background (no elevation)
            container.setBackground(ContextCompat.getDrawable(this, R.drawable.pill_spinner));
            container.setPadding(
                    dpToPx(16),
                    dpToPx(16),
                    dpToPx(16),
                    dpToPx(16)
            );

            // 3) Add the review text
            TextView reviewText = new TextView(this);
            reviewText.setText(review);
            reviewText.setTextSize(14);
            reviewText.setTextColor(0xFF333333);
            // no extra padding needed since container already has it
            container.addView(reviewText);

            // 4) Add to the parent
            reviewContainer.addView(container);
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
