package com.example.tp4_H071231079;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PostGridAdapter extends RecyclerView.Adapter<PostGridAdapter.GridViewHolder> {
    private static final String TAG = "PostGridAdapter";
    private final Context context;
    private final List<Post> postList;
    private final User user;

    public PostGridAdapter(Context context, List<Post> postList, User user) {
        this.context = context;
        this.postList = postList != null ? postList : new ArrayList<>();
        this.user = user;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_post_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Post post = postList.get(position);
        if (post == null) return;

        // Load image from URI or resource ID
        if (post.hasImageUri() && post.getImageUri() != null) {
            try (InputStream is = context.getContentResolver().openInputStream(post.getImageUri())) {
                Bitmap bmp = BitmapFactory.decodeStream(is);
                if (bmp != null) {
                    holder.imagePost.setImageBitmap(bmp);
                } else {
                    holder.imagePost.setImageResource(R.drawable.post8);
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to load URI manually", e);
                holder.imagePost.setImageResource(R.drawable.post8);
            }
        } else {
            Glide.with(context)
                    .load(post.getImageResId())
                    .into(holder.imagePost);
        }

        holder.imagePost.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostViewerActivity.class);

            // Kirim data Post
            intent.putExtra("caption", post.getCaption());
            intent.putExtra("likes", post.getLikes());
            intent.putExtra("comments", post.getComments());

            // Kirim data User atau default jika null
            if (user != null) {
                intent.putExtra("profileImage", user.getProfileImage());
                intent.putExtra("username",     user.getUsername());
            } else {
                // Default values
                intent.putExtra("username",     "Unknown");
                intent.putExtra("profileImage", R.drawable.profile2);
            }
            // Kirimkan imageUri jika ada, jika tidak, kirimkan imageResId
            if (post.getImageUri() != null) {
                intent.putExtra("imageUri", post.getImageUri().toString());
            } else {
                intent.putExtra("imageResId", post.getImageResId());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    static class GridViewHolder extends RecyclerView.ViewHolder {
        final ImageView imagePost;

        GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePost = itemView.findViewById(R.id.imagePost);
        }
    }
}
