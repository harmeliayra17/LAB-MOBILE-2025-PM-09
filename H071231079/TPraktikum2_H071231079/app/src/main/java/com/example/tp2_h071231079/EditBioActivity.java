package com.example.tp2_h071231079;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditBioActivity extends AppCompatActivity {

    private EditText etInputBio;
    private TextView btnSimpan;
    private ImageView btnClear, btnBack;

    private String currentBio = "";
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        etInputBio = findViewById(R.id.etInputBio);
        btnSimpan = findViewById(R.id.btnSimpanNama);
        btnClear = findViewById(R.id.btnClearBio);
        btnBack = findViewById(R.id.btnBackNama);

        // ini untuk ambil data dari intent
        userProfile = getIntent().getParcelableExtra("user_profile");
        if (userProfile != null) {
            currentBio = userProfile.getBio();
            etInputBio.setText(currentBio);
            etInputBio.setSelection(currentBio.length());
        }

        // Default tombol simpan tidak aktif
        btnSimpan.setTextColor(Color.parseColor("#AEACAC"));
        btnSimpan.setEnabled(false);

        // Listener input bio
        etInputBio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newBio = s.toString().trim();

                if (newBio.length() > 500) {
                    etInputBio.setError("Bio tidak boleh lebih dari 500 karakter");
                    btnSimpan.setTextColor(Color.parseColor("#AEACAC"));
                    btnSimpan.setEnabled(false);
                }
                else if (!newBio.equals(currentBio) && !newBio.isEmpty()) {
                    btnSimpan.setTextColor(Color.parseColor("#FF5722"));
                    btnSimpan.setEnabled(true);
                } else {
                    btnSimpan.setTextColor(Color.parseColor("#AEACAC"));
                    btnSimpan.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Tombol Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInputBio.setText("");
            }
        });

        // Tombol Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Tombol Simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnSimpan.isEnabled()) return;

                String newBio = etInputBio.getText().toString().trim();
                if (userProfile != null) {
                    userProfile.setBio(newBio); // diupdate bio pada modelnya
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("user_profile", userProfile);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
