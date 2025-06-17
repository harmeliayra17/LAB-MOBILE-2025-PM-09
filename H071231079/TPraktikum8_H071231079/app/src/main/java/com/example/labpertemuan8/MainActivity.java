package com.example.labpertemuan8;

import static com.example.labpertemuan8.FormActivity.REQUEST_UPDATE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labpertemuan8.database.NotesHelper;
import com.example.labpertemuan8.MappingHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;
    private TextView noData;
    private EditText searchEditText;
    private ImageView searchIcon;
    private FloatingActionButton fabAdd;
    private NotesAdapter notesAdapter;
    private NotesHelper notesHelper;

    public static final int REQUEST_ADD = 100;

    private ArrayList<Notes> allNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Notes List");
        }

        rvNotes = findViewById(R.id.rv_students);
        noData = findViewById(R.id.noData);
        searchEditText = findViewById(R.id.searchEditText);
        searchIcon = findViewById(R.id.searchIcon);
        fabAdd = findViewById(R.id.fab_add);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(this);
        rvNotes.setAdapter(notesAdapter);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        loadNotes();
    }

    private void loadNotes() {
        new LoadNotesAsync(this, notes -> {
            allNotes = notes;
            if (notes.size() > 0) {
                notesAdapter.setNotes(notes);
                noData.setVisibility(View.GONE);
            } else {
                notesAdapter.setNotes(new ArrayList<>());
                noData.setVisibility(View.VISIBLE);
            }
        }).execute();
    }

    private void searchNotes(String keyword) {
        ArrayList<Notes> filtered = new ArrayList<>();
        for (Notes note : allNotes) {
            if (note.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(note);
            }
        }

        if (filtered.isEmpty()) {
            notesAdapter.setNotes(new ArrayList<>());
            noData.setVisibility(View.VISIBLE);
        } else {
            notesAdapter.setNotes(filtered);
            noData.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD) {
            if (resultCode == FormActivity.RESULT_ADD) {
                showToast("Notes added successfully");
                loadNotes();
            }
        } else if (requestCode == REQUEST_UPDATE) {
            if (resultCode == FormActivity.RESULT_UPDATE) {
                showToast("Notes updated successfully");
                loadNotes();
            } else if (resultCode == FormActivity.RESULT_DELETE) {
                showToast("Notes deleted successfully");
                loadNotes();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (notesHelper != null) {
            notesHelper.close();
        }
    }

    private static class LoadNotesAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNotesCallback> weakCallback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                if (context != null) {
                    NotesHelper notesHelper = NotesHelper.getInstance(context);
                    notesHelper.open();

                    Cursor cursor = notesHelper.queryAll();
                    ArrayList<Notes> notes = MappingHelper.mapCursorToArrayList(cursor);
                    cursor.close();

                    handler.post(() -> {
                        LoadNotesCallback callback = weakCallback.get();
                        if (callback != null) {
                            callback.postExecute(notes);
                        }
                    });
                }
            });
        }
    }

    interface LoadNotesCallback {
        void postExecute(ArrayList<Notes> notes);
    }
}
