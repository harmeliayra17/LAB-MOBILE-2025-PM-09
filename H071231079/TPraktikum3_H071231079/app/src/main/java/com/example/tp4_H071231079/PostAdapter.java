package com.example.tp4_H071231079;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> postList;
    private List<User> userList;

    public PostAdapter(Context context, List<Post> postList, List<User> userList) {
        this.context = context;
        this.postList = postList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        // Cari user berdasarkan userId (username) dari post
        User postUser = userList.stream().filter(user -> user.getUsername().equals(post.getUserId())).findFirst().orElse(null);

        // Bind user info
        if (postUser != null) {
            holder.imageProfile.setImageResource(postUser.getProfileImage());
            holder.textUsername.setText(postUser.getUsername());
            holder.textUsername2.setText(postUser.getUsername());
        } else {
            holder.imageProfile.setImageResource(R.drawable.post1);
            holder.textUsername.setText("unknown");
            holder.textUsername2.setText("unknown");
        }

        // Bind post image
        if (post.hasImageUri()) {
            holder.imagePost.setImageURI(post.getImageUri());
        } else {
            holder.imagePost.setImageResource(post.getImageResId());
        }

        // Bind caption dan likes
        holder.textDescription.setText(post.getCaption());
        holder.textLikes.setText(post.getLikes() + " likes");
        holder.textViewComments.setText("View all " + post.getComments() + " comments");

        // Click listener untuk profile
        View.OnClickListener goToProfile = v -> {
            if (postUser == null) return;

            Class<?> targetActivity;
            String username = postUser.getUsername();

            if (username.equals("kelompok8_") || username.equals("user1")) {
                targetActivity = ProfileAccountActivity.class;
            } else {
                targetActivity = ProfileActivity.class;
            }

            Intent intent = new Intent(context, targetActivity);
            // Kirim data user
            intent.putExtra("profileImage", postUser.getProfileImage());
            intent.putExtra("username", postUser.getUsername());
            intent.putExtra("name", postUser.getName());
            intent.putExtra("bio", postUser.getBio());
            intent.putExtra("postsCount", postUser.getPosts().size());
            intent.putExtra("followersCount", postUser.getFollowersCount());
            intent.putExtra("followingCount", postUser.getFollowingCount());
            intent.putExtra("followedBy", postUser.getFollowedBy());
            intent.putParcelableArrayListExtra(
                    "highlights",
                    new ArrayList<>(postUser.getHighlights())
            );
            intent.putParcelableArrayListExtra(
                    "posts",
                    new ArrayList<>(postUser.getPosts())
            );
            context.startActivity(intent);
        };

        holder.imageProfile.setOnClickListener(goToProfile);
        holder.textUsername.setOnClickListener(goToProfile);
        holder.textUsername2.setOnClickListener(goToProfile);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProfile, imagePost;
        TextView textUsername, textUsername2, textDescription, textLikes, textViewComments;

        public PostViewHolder(View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            imagePost = itemView.findViewById(R.id.imagePost);
            textUsername = itemView.findViewById(R.id.textUsername);
            textUsername2 = itemView.findViewById(R.id.textUsername2);
            textDescription = itemView.findViewById(R.id.textDescription);
            textLikes = itemView.findViewById(R.id.textLikes);
            textViewComments = itemView.findViewById(R.id.textViewComments);
        }
    }
}
