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

public class EditUsernameActivity extends AppCompatActivity {

    private EditText etInputUsername;
    private TextView btnSimpanUsername, tvCurrentUsername;
    private ImageView btnClearUsername, btnBackUsername;

    private String currentUsername = "";
    private UserProfile userProfile; // Tambahan model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_username);

        etInputUsername = findViewById(R.id.etInputNewUsername);
        btnSimpanUsername = findViewById(R.id.btnSimpanUsername);
        btnClearUsername = findViewById(R.id.btnClearUsername);
        btnBackUsername = findViewById(R.id.btnBackUsername);
        tvCurrentUsername = findViewById(R.id.tvCurrentUsername);

        // Ambil user profile dari intent
        userProfile = getIntent().getParcelableExtra("user_profile");
        if (userProfile != null) {
            currentUsername = userProfile.getUsername();
            tvCurrentUsername.setText(currentUsername);
        }

        // Default tombol simpan nonaktif
        btnSimpanUsername.setTextColor(Color.parseColor("#AEACAC"));
        btnSimpanUsername.setEnabled(false);

        // Listener perubahan input
        etInputUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newInput = s.toString().trim();

                if (!newInput.equals(currentUsername) && !newInput.isEmpty()) {
                    btnSimpanUsername.setTextColor(Color.parseColor("#FF5722"));
                    btnSimpanUsername.setEnabled(true);
                } else {
                    btnSimpanUsername.setTextColor(Color.parseColor("#AEACAC"));
                    btnSimpanUsername.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Tombol Clear
        btnClearUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInputUsername.setText("");
            }
        });

        // Tombol Back
        btnBackUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Tombol Simpan
        btnSimpanUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnSimpanUsername.isEnabled()) return;

                String newUsername = etInputUsername.getText().toString().trim();
                if (userProfile != null) {
                    userProfile.setUsername(newUsername); // update ke model
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("user_profile", userProfile); // kirim balik model
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
