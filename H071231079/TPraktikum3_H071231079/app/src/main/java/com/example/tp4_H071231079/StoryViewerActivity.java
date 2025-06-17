package com.example.tp4_H071231079;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryViewerActivity extends AppCompatActivity {
    ImageView imageStoryFull, imageProfile;
    TextView textUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_viewer);

        imageStoryFull = findViewById(R.id.imageStoryFull);
        imageProfile = findViewById(R.id.imageProfile);
        textUsername = findViewById(R.id.textUsername);

        Story story = getIntent().getParcelableExtra("story");
        String username = getIntent().getStringExtra("username");
        int profileImageRes = getIntent().getIntExtra("profileImage", -1);

        if (story != null) {
            imageStoryFull.setImageResource(story.getImageRes());
        }

        textUsername.setText(username);

        if (profileImageRes != -1) {
            imageProfile.setImageResource(profileImageRes);
        }
    }

}
