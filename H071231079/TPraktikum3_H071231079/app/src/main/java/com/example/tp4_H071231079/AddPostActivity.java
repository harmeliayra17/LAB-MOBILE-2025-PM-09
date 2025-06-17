package com.example.tp4_H071231079;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddPostActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ImageView ivPreview;
    private EditText etCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        User user = getIntent().getParcelableExtra("USER");
        int userIndex = getIntent().getIntExtra("USER_INDEX", 0);

        ivPreview  = findViewById(R.id.ivPreview);
        etCaption  = findViewById(R.id.etCaption);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        ivPreview.setOnClickListener(v -> {
            // Use ACTION_OPEN_DOCUMENT for persistable URI
            Intent pick = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            pick.addCategory(Intent.CATEGORY_OPENABLE);
            pick.setType("image/*");
            pick.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION |
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            );
            startActivityForResult(pick, PICK_IMAGE);
        });

        btnSubmit.setOnClickListener(v -> {
            String caption = etCaption.getText().toString().trim();
            String username = getIntent().getStringExtra("username");

            if (imageUri != null && !caption.isEmpty() && username != null) {
                Intent result = new Intent();
                result.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                result.setData(imageUri);
                result.putExtra("imageUri", imageUri.toString());
                result.putExtra("caption", caption);
                result.putExtra("username", username);
                setResult(Activity.RESULT_OK, result);
                finish();
            } else {
                Toast.makeText(this, "Pilih gambar & isi caption dulu", Toast.LENGTH_SHORT).show();
            }

            // Di dalam onClick/event simpan post:
            if (user != null) {
                // Buat post baru dengan user ini
                Post newPost = new Post(user, imageUri, caption);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_POST", newPost);
                resultIntent.putExtra("USER_INDEX", userIndex);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                imageUri = uri;
                final int takeFlags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                getContentResolver().takePersistableUriPermission(uri, takeFlags);
                ivPreview.setImageURI(imageUri);
            }
        }
    }
}
