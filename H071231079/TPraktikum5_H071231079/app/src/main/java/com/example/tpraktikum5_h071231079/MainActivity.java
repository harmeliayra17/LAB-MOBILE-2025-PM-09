package com.example.tpraktikum5_h071231079;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ImageView homeButton;
    private ImageView favButton;
    private ImageView addButton;
    private TextView homeText;
    private TextView favText;
    private TextView addText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = findViewById(R.id.home_button);
        favButton  = findViewById(R.id.favorite_button);
        addButton  = findViewById(R.id.add_button);
        homeText   = findViewById(R.id.text_home);
        favText    = findViewById(R.id.text_fav);
        addText    = findViewById(R.id.text_add);

        // Home tampil pertama
        if (savedInstanceState == null) {
            selectTab(Tab.HOME);
        }

        // Click Handlingg
        homeButton.setOnClickListener(v -> selectTab(Tab.HOME));
        favButton .setOnClickListener(v -> selectTab(Tab.FAVORITES));
        addButton .setOnClickListener(v -> selectTab(Tab.ADD));
    }

    private enum Tab { HOME, FAVORITES, ADD }

    private void selectTab(Tab tab) {
        Fragment fragment;
        @ColorInt int activeColor   = ContextCompat.getColor(this, R.color.white);
        @ColorInt int inactiveColor = ContextCompat.getColor(this, R.color.gray);

        // Reset semua icon jadi inactive
        tintButton(homeButton, inactiveColor);
        tintButton(favButton, inactiveColor);
        tintButton(addButton, inactiveColor);
        homeText.setTextColor(inactiveColor);
        favText.setTextColor(inactiveColor);
        addText.setTextColor(inactiveColor);

        switch (tab) {
            case HOME:
                fragment = new HomeFragment();
                tintButton(homeButton, activeColor);
                homeText.setTextColor(activeColor);
                break;
            case FAVORITES:
                fragment = new FavoriteBookFragment();
                tintButton(favButton, activeColor);
                favText.setTextColor(activeColor);
                break;
            case ADD:
                fragment = new AddBooksFragment();
                tintButton(addButton, activeColor);
                addText.setTextColor(activeColor);
                break;
            default:
                return;
        }

        // Ganti fragment containernya
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_home, fragment);
        ft.commit();
    }

    private void tintButton(ImageView button, @ColorInt int color) {
        Drawable drawable = button.getDrawable().mutate();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        button.setImageDrawable(drawable);
    }
}
