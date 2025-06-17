package com.example.tp4_H071231079;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView usernameTextView, nameTextView, followedByTextView;
    private TextView postsTextView, bioTextView, followingTextView, followersTextView;
    private RecyclerView highlightRecyclerView, postsRecyclerView;
    private View dividerUnderTopBar;

    // Fields for posts and adapter
    private ArrayList<Post> postList;
    private PostGridAdapter postGridAdapter;
    private User user; // Simpan objek user
    private int userIndex;

    private static final int REQ_ADD_POST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = getIntent().getParcelableExtra("USER");
        userIndex = getIntent().getIntExtra("USER_INDEX", 0);

        // Ambil data dari Intent
        Intent intent = getIntent();
        int profileImage = intent.getIntExtra("profileImage", R.drawable.profile1);
        String username = intent.getStringExtra("username");
        String name = intent.getStringExtra("name");
        String followedBy = intent.getStringExtra("followedBy");
        String bio = intent.getStringExtra("bio");

        int postsCount = intent.getIntExtra("postsCount", 0);
        int followers = intent.getIntExtra("followersCount", 0);
        int following = intent.getIntExtra("followingCount", 0);

        ArrayList<Highlight> highlights = intent.getParcelableArrayListExtra("highlights");
        postList = intent.getParcelableArrayListExtra("posts");
        if (postList == null) {
            postList = new ArrayList<>();
        }

        // Ambil objek User jika dikirim utuh
        User user = intent.getParcelableExtra("USER_DATA");
        if (user == null) {
            user = new User(
                    profileImage,
                    username != null ? username : "Unknown",
                    name != null ? name : "",
                    bio != null ? bio : "",
                    followers,
                    following,
                    followedBy != null ? followedBy : "",
                    new int[0],
                    new String[0],
                    null,
                    postList
            );
        }

        // Inisialisasi View
        profileImageView = findViewById(R.id.imageProfile);
        usernameTextView = findViewById(R.id.textUsername);
        nameTextView = findViewById(R.id.textName);
        postsTextView = findViewById(R.id.textPostCount);
        bioTextView = findViewById(R.id.textBio);
        followersTextView = findViewById(R.id.textFollowers);
        followingTextView = findViewById(R.id.textFollowing);
        followedByTextView = findViewById(R.id.textFollowedBy);
        highlightRecyclerView = findViewById(R.id.recyclerHighlights);
        postsRecyclerView = findViewById(R.id.recyclerViewPosts);
        dividerUnderTopBar = findViewById(R.id.dividerUnderTopBar);

        // Set data ke View
        profileImageView.setImageResource(user.getProfileImage());
        usernameTextView.setText(user.getUsername());
        nameTextView.setText(user.getName());
        followedByTextView.setText(user.getFollowedBy());
        bioTextView.setText(user.getBio());
        postsTextView.setText(String.valueOf(postsCount));
        followersTextView.setText(String.valueOf(followers));
        followingTextView.setText(String.valueOf(following));

        // Setup Highlights
        if (highlights == null) highlights = new ArrayList<>();
        HighlightAdapter highlightAdapter = new HighlightAdapter(this, highlights);
        highlightRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        highlightRecyclerView.setAdapter(highlightAdapter);

        // Setup Posts Grid dan adapter
        Collections.reverse(postList);
        postGridAdapter = new PostGridAdapter(this, postList, user);
        postsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        postsRecyclerView.setAdapter(postGridAdapter);

        // Tombol Back
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Tombol Add Post
        ImageView navPost = findViewById(R.id.item_post);
        User finalUser = user;
        navPost.setOnClickListener(v -> {
            Intent intentAdd = new Intent(ProfileActivity.this, AddPostActivity.class);
            intentAdd.putExtra("USER", finalUser); // Kirim user
            intentAdd.putExtra("USER_INDEX", userIndex); // Kirim index user
            startActivityForResult(intentAdd, REQ_ADD_POST);
        });

        // Divider on scroll
        NestedScrollView scrollContent = findViewById(R.id.scrollContent);
        scrollContent.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldX, oldY) -> {
            dividerUnderTopBar.setVisibility(scrollY > 0 ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_POST && resultCode == RESULT_OK && data != null) {
            Post newPost = data.getParcelableExtra("NEW_POST");
            int receivedUserIndex = data.getIntExtra("USER_INDEX", 0);

            if (newPost != null && receivedUserIndex == userIndex) {
                //Update data lokal
                user.getPosts().add(0, newPost);
                postList.add(0, newPost);

                //Kirim kembali ke MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_POST", newPost);
                resultIntent.putExtra("USER_INDEX", userIndex);
                setResult(RESULT_OK, resultIntent);

                //Update UI
                postGridAdapter.notifyItemInserted(0);
                postsRecyclerView.scrollToPosition(0);
                postsTextView.setText(String.valueOf(user.getPosts().size()));
            }
        }
    }
}
