package com.example.tpraktikum6_h071231079.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tpraktikum6_h071231079.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME    = "extra_name";
    public static final String EXTRA_STATUS  = "extra_status";
    public static final String EXTRA_SPECIES = "extra_species";
    public static final String EXTRA_GENDER  = "extra_gender";
    public static final String EXTRA_IMAGE   = "extra_image";

    private TextView tvDetailName;
    private ImageView imgDetailAvatar;
    private TextView tvDetailStatus;
    private TextView tvDetailSpecies;
    private TextView tvDetailGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // inisialisasi
        tvDetailName      = findViewById(R.id.tvDetailName);
        imgDetailAvatar   = findViewById(R.id.imgDetailAvatar);
        tvDetailStatus    = findViewById(R.id.tvDetailStatus);
        tvDetailSpecies   = findViewById(R.id.tvDetailSpecies);
        tvDetailGender    = findViewById(R.id.tvDetailGender);

        // ambil data
        String name    = getIntent().getStringExtra(EXTRA_NAME);
        String status  = getIntent().getStringExtra(EXTRA_STATUS);
        String species = getIntent().getStringExtra(EXTRA_SPECIES);
        String gender  = getIntent().getStringExtra(EXTRA_GENDER);
        String image   = getIntent().getStringExtra(EXTRA_IMAGE);

        // set data ke View
        tvDetailName.setText(name);
        tvDetailStatus.setText("Status  : " + status);
        tvDetailSpecies.setText("Species : " + species);
        tvDetailGender.setText("Gender  : " + gender);

        Glide.with(this)
                .load(image)
                .circleCrop()
                .into(imgDetailAvatar);
    }
}
