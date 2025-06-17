package com.example.tp2_h071231079;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PROFILE = 100;

    private ImageView profileImage;
    private TextView userName;
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profileImage);
        userName = findViewById(R.id.userUsername);

        // === Set Dummy Data ke userProfile ===
        userProfile = new UserProfile();
        userProfile.setName("Lia Rahmatika");
        userProfile.setUsername("@h071231079");
        userProfile.setBio("Mahasiswa Sistem Informasi Unhas");
        userProfile.setGender("Perempuan");
        userProfile.setBirthDate("1 Januari 2003");

        // Ambil gambar dari drawable
        Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.profile);
        userProfile.setProfilePicture(imageUri.toString());

        // Tampilkan data di MainActivity
        profileImage.setClipToOutline(true);
        userName.setText(userProfile.getUsername());
        profileImage.setImageURI(imageUri);

        View.OnClickListener openProfile = v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("user_profile", userProfile);
            startActivityForResult(intent, REQUEST_PROFILE); // <--- FIXED
        };

        profileImage.setOnClickListener(openProfile);
        userName.setOnClickListener(openProfile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PROFILE && resultCode == RESULT_OK && data != null) {
            UserProfile updatedProfile = data.getParcelableExtra("user_profile");
            if (updatedProfile != null) {
                userProfile = updatedProfile;

                // Update UI di MainActivity
                userName.setText(userProfile.getUsername());
                if (userProfile.getProfilePicture() != null) {
                    profileImage.setImageURI(Uri.parse(userProfile.getProfilePicture()));
                }
            }
        }
    }
}
