package com.example.labpertemuan8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;

import com.example.labpertemuan8.database.DatabaseContract;
import com.example.labpertemuan8.database.NotesHelper;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_NOTES = "extra_notes";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    public static final int REQUEST_UPDATE = 200;

    private NotesHelper notesHelper;
    private TextView etTitle, etDescription, btnSave, btnDelete;
    private boolean isEdit = false;
    private Notes notes;
    private ImageView btnBack;

    private boolean isChanged = false; // flag untuk cek perubahan data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_content);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        btnBack = findViewById(R.id.iv_back);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();

        notes = getIntent().getParcelableExtra(EXTRA_NOTES);
        if (notes != null) {
            isEdit = true;
        } else {
            notes = new Notes();
        }

        String actionBarTitle = isEdit ? "Edit Note" : "Add Note";
        String buttonTitle = isEdit ? "Update" : "Save";

        if (isEdit) {
            etTitle.setText(notes.getTitle());
            etDescription.setText(notes.getDescription());
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setText(buttonTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Tandai isChanged jika user edit teks
        etTitle.addTextChangedListener(new SimpleTextWatcher(() -> isChanged = true));
        etDescription.addTextChangedListener(new SimpleTextWatcher(() -> isChanged = true));

        btnSave.setOnClickListener(view -> showSaveConfirmation());
        btnDelete.setOnClickListener(view -> showDeleteConfirmation());

        etDescription.setMovementMethod(new ScrollingMovementMethod());

        btnBack.setOnClickListener(view -> {
            handleBackPressed();
        });
    }

    private void handleBackPressed() {
        if (isChanged) {
            showCancelConfirmation();
        } else {
            finishWithAnimation();
        }
    }

    private void finishWithAnimation() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void showSaveConfirmation() {
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Konfirmasi Simpan")
                .setMessage("Apakah Anda yakin ingin menyimpan?")
                .setPositiveButton("Ya", (dialog1, which) -> saveNote())
                .setNegativeButton("Batal", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(getResources().getColor(R.color.purple_500));
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(getResources().getColor(android.R.color.darker_gray));
        });

        dialog.show();
    }

    private void showDeleteConfirmation() {
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Konfirmasi Hapus")
                .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                .setPositiveButton("Hapus", (dialog1, which) -> deleteNote())
                .setNegativeButton("Batal", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(getResources().getColor(R.color.purple_500));
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(getResources().getColor(android.R.color.darker_gray));
        });

        dialog.show();
    }

    private void showCancelConfirmation() {
        String message;
        if (isEdit) {
            message = "Apakah Anda yakin ingin membatalkan perubahan?";
        } else {
            message = "Apakah Anda yakin ingin membatalkan penambahan?";
        }

        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Konfirmasi Batal")
                .setMessage(message)
                .setPositiveButton("Ya", (dialog1, which) -> finishWithAnimation())
                .setNegativeButton("Tidak", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(getResources().getColor(R.color.purple_500));
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(getResources().getColor(android.R.color.darker_gray));
        });

        dialog.show();
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Please fill this field");
            return;
        }

        if (desc.isEmpty()) {
            etDescription.setError("Please fill this field");
            return;
        }

        notes.setTitle(title);
        notes.setDescription(desc);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NotesColumn.TITLE, title);
        values.put(DatabaseContract.NotesColumn.DESCRIPTION, desc);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTES, notes);

        if (isEdit) {
            long result = notesHelper.update(String.valueOf(notes.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finishWithAnimation();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = notesHelper.insert(values);
            if (result > 0) {
                notes.setId((int) result);
                setResult(RESULT_ADD, intent);
                finishWithAnimation();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteNote() {
        if (notes != null && notes.getId() > 0) {
            long result = notesHelper.deleteById(String.valueOf(notes.getId()));
            if (result > 0) {
                setResult(RESULT_DELETE);
                finishWithAnimation();
            } else {
                Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid note data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            handleBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        handleBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (notesHelper != null) {
            notesHelper.close();
        }
    }

    // SimpleTextWatcher class untuk memudahkan text change listener
    private static class SimpleTextWatcher implements TextWatcher {

        private final Runnable onTextChangedCallback;

        public SimpleTextWatcher(Runnable onTextChangedCallback) {
            this.onTextChangedCallback = onTextChangedCallback;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // kosong
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (onTextChangedCallback != null) {
                onTextChangedCallback.run();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // kosong
        }
    }
}
