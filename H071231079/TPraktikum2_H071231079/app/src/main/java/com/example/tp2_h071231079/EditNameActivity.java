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

public class EditNameActivity extends AppCompatActivity {

    private EditText etInputBaru;
    private TextView btnSimpan;
    private ImageView btnClearText, btnBack;

    private String currentName = "";
    private UserProfile userProfile; // Tambahan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        etInputBaru = findViewById(R.id.etInputNewName);
        btnSimpan = findViewById(R.id.btnSimpanNama);
        btnClearText = findViewById(R.id.btnClearName);
        btnBack = findViewById(R.id.btnBackNama);

        // Ambil model dari intent
        userProfile = getIntent().getParcelableExtra("user_profile");
        if (userProfile != null) {
            currentName = userProfile.getName();
            etInputBaru.setText(currentName);
            etInputBaru.setSelection(currentName.length());
        }

        // Default tombol simpan tidak aktif
        btnSimpan.setTextColor(Color.parseColor("#AEACAC"));
        btnSimpan.setEnabled(false);

        // Listener perubahan input
        etInputBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newInput = s.toString().trim();
                if (!newInput.equals(currentName) && !newInput.isEmpty()) {
                    btnSimpan.setTextColor(Color.parseColor("#FF5722"));
                    btnSimpan.setEnabled(true);
                } else {
                    btnSimpan.setTextColor(Color.parseColor("#AEACAC"));
                    btnSimpan.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 100) {
                    s.delete(100, s.length());
                }
            }
        });

        // Tombol Clear
        btnClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInputBaru.setText("");
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

                String newName = etInputBaru.getText().toString().trim();
                if (userProfile != null) {
                    userProfile.setName(newName); // Update nama
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("user_profile", userProfile); // Kirim model
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
