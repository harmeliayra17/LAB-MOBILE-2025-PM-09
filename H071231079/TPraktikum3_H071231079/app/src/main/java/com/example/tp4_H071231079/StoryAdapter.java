package com.example.tp4_H071231079;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<User> userList;
    private Context context;

    public StoryAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        User user = userList.get(position);
        Story story = user.getStory();

        holder.storyImage.setImageResource(user.getProfileImage());
        holder.storyUsername.setText(user.getUsername());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryViewerActivity.class);
            intent.putExtra("story", story);
            intent.putExtra("username", user.getUsername());
            intent.putExtra("profileImage", user.getProfileImage());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        CircleImageView storyImage;
        TextView storyUsername;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.imageStory);
            storyUsername = itemView.findViewById(R.id.textUsername);
        }
    }
}
