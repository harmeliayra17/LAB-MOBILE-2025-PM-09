package com.example.tp4_H071231079;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_viewer);

        // Inisialisasi views
        CircleImageView ivProfile   = findViewById(R.id.imageProfile);
        TextView        tvUserName  = findViewById(R.id.textUsername);
        ImageView       ivPost      = findViewById(R.id.imagePost);
        TextView        tvLikes     = findViewById(R.id.textLikes);
        TextView        tvUser2     = findViewById(R.id.textUsername2);
        TextView        tvCaption   = findViewById(R.id.textDescription);
        TextView        tvViewCmt   = findViewById(R.id.textViewComments);

        // Ambil data dari Intent
        Intent intent = getIntent();

        String caption = intent.getStringExtra("caption");
        int likes = intent.getIntExtra("likes", 0);
        int comments = intent.getIntExtra("comments", 0);

        int profileImage = intent.getIntExtra("profileImage", R.drawable.post1);
        String username = intent.getStringExtra("username");

        String imageUriStr = intent.getStringExtra("imageUri");
        int imageResId = intent.getIntExtra("imageResId", 0);

        // Tampilkan data user
        ivProfile.setImageResource(profileImage);
        tvUserName.setText(username);
        tvUser2.setText(username);

        // Tampilkan gambar post (URI > resource fallback)
        if (imageUriStr != null) {
            Uri imageUri = Uri.parse(imageUriStr);
            Glide.with(this)
                    .load(imageUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.post8)
                    .into(ivPost);
        } else {
            Glide.with(this)
                    .load(imageResId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.post8)
                    .into(ivPost);
        }
        ImageView btnBackPost = findViewById(R.id.btnBack);
        btnBackPost.setOnClickListener(v -> {
            // Akhiri activity ini, kembali ke yang sebelumnya
            finish();
        });

        // Tampilkan teks lainnya
        tvCaption.setText(caption);
        tvLikes.setText(likes + " Likes");
        tvViewCmt.setText("View all " + comments + " comments");
    }
}
