package com.example.tpraktikum4_h071231079;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static android.app.Activity.RESULT_OK;
import static com.example.tpraktikum4_h071231079.BookRepository.books;

public class AddBooksFragment extends Fragment {
    private static final int PICK_IMAGE = 100;

    private EditText inputTitle, inputAuthor, inputYear, inputRating, inputDescription, inputOtherGenre;
    private Spinner spinnerGenre;
    private ImageView imagePreview;
    private Button buttonAddBook;
    private Uri selectedImageUri;

    public AddBooksFragment() {}

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        // bind views
        inputTitle       = view.findViewById(R.id.inputTitle);
        inputAuthor      = view.findViewById(R.id.inputAuthor);
        inputYear        = view.findViewById(R.id.inputYear);
        inputRating      = view.findViewById(R.id.inputRating);
        inputDescription = view.findViewById(R.id.inputDescription);
        spinnerGenre     = view.findViewById(R.id.spinnerGenre);
        inputOtherGenre  = view.findViewById(R.id.inputOtherGenre);
        imagePreview     = view.findViewById(R.id.cardImagePicker);
        buttonAddBook    = view.findViewById(R.id.buttonAddBook);

        // image picker
        imagePreview.setOnClickListener(v -> {
            Intent pick = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            pick.addCategory(Intent.CATEGORY_OPENABLE);
            pick.setType("image/*");
            pick.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION |
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            );
            startActivityForResult(pick, PICK_IMAGE);
        });

        // build genre list with dummy prompt at pos 0
        List<String> genres = new ArrayList<>();
        genres.add("Choose Genre…"); // hint
        genres.addAll(
                books.stream()
                        .map(Book::getGenre)
                        .distinct()
                        .collect(Collectors.toList())
        );
        genres.add("Other");


        // adapter that disables pos 0 and grays it out
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                genres
        ) {
            @Override public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView)v;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return v;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
        spinnerGenre.setSelection(0);

        // show/hide Other field
        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                if (pos > 0 && "Other".equals(genres.get(pos))) {
                    inputOtherGenre.setVisibility(View.VISIBLE);
                } else {
                    inputOtherGenre.setVisibility(View.GONE);
                }
            }
            @Override public void onNothingSelected(AdapterView<?> parent) { }
        });

        // add button
        buttonAddBook.setOnClickListener(v -> addBook());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                selectedImageUri = uri;
                final int flags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                requireContext().getContentResolver().takePersistableUriPermission(uri, flags);
                imagePreview.setImageURI(uri);
            }
        }
    }

    private void addBook() {
        String title       = inputTitle.getText().toString().trim();
        String author      = inputAuthor.getText().toString().trim();
        String yearStr     = inputYear.getText().toString().trim();
        String ratingStr   = inputRating.getText().toString().trim();
        String description = inputDescription.getText().toString().trim();

        // determine genre
        int pos = spinnerGenre.getSelectedItemPosition();
        String genre = pos == 0
                ? ""
                : spinnerGenre.getItemAtPosition(pos).toString();
        if ("Other".equals(genre)) {
            genre = inputOtherGenre.getText().toString().trim();
        }

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) ||
                TextUtils.isEmpty(genre) || TextUtils.isEmpty(ratingStr) ||
                TextUtils.isEmpty(description) || TextUtils.isEmpty(yearStr) ||
                selectedImageUri == null) {
            Toast.makeText(getContext(),
                    "Please fill in all fields and select an image",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        float rating;
        int year;
        try {
            rating = Float.parseFloat(ratingStr);
            if (rating < 0 || rating > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(),
                    "Rating must be between 0.0 and 5.0",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(),
                    "Year must be a valid number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = new Book(
                BookRepository.generateNewId(),
                title, author, year,
                description, genre, rating,
                new String[]{ "No reviews yet." },
                selectedImageUri.toString()
        );

        BookRepository.addBook(newBook);
        Toast.makeText(getContext(),
                "Book added successfully!", Toast.LENGTH_SHORT).show();

        // reset form
        inputTitle      .setText("");
        inputAuthor     .setText("");
        inputYear       .setText("");
        inputRating     .setText("");
        inputDescription.setText("");
        spinnerGenre    .setSelection(0);
        inputOtherGenre .setVisibility(View.GONE);
        selectedImageUri= null;
        imagePreview    .setImageResource(R.drawable.add_image);
    }
}
