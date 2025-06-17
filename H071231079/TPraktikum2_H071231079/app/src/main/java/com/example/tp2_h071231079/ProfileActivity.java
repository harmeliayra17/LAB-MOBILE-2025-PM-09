package com.example.tp2_h071231079;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserGender, tvBirthDate, tvUserUsername, tvUserBio;
    private ImageView profileImage;
    private LinearLayout birthDateLayout;

    private ImageView saveIcon, btnBack;
    private static final int REQUEST_GALLERY = 101;

    private UserProfile userProfile;
    private UserProfile originalProfile;
    private boolean isDataChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // inisialisasi view
        tvUserName = findViewById(R.id.userName);
        tvUserUsername = findViewById(R.id.userUsername);
        tvUserBio = findViewById(R.id.userBio);
        tvUserGender = findViewById(R.id.userGender);
        tvBirthDate = findViewById(R.id.userBirthday);
        profileImage = findViewById(R.id.profileImage);
        birthDateLayout = findViewById(R.id.birthDate);
        saveIcon = findViewById(R.id.saveIcon);
        btnBack = findViewById(R.id.btnBack);

        // ambil data dari MainActivity
        userProfile = getIntent().getParcelableExtra("user_profile");

        if (userProfile != null) {
            updateUIFromUserProfile();
            originalProfile = new UserProfile(userProfile);
        }

        // Tombol kembali back
        btnBack.setOnClickListener(v -> onBackPressed());

        // Tombol simpan jika ada perubahan
        saveIcon.setOnClickListener(v -> {
            if (isDataChanged) {
                Intent intent = new Intent();
                intent.putExtra("user_profile", userProfile);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Edit Username
        findViewById(R.id.username).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditUsernameActivity.class);
            intent.putExtra("user_profile", userProfile);
            startActivityForResult(intent, 2);
        });

        // Edit Name
        findViewById(R.id.name).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditNameActivity.class);
            intent.putExtra("user_profile", userProfile);
            startActivityForResult(intent, 1);
        });

        // Edit Bio
        findViewById(R.id.bio).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditBioActivity.class);
            intent.putExtra("user_profile", userProfile);
            startActivityForResult(intent, 3);
        });

        // Edit Gender
        findViewById(R.id.gender).setOnClickListener(v -> showGenderDialog());

        // Edit Tanggal Lahir
        birthDateLayout.setOnClickListener(v -> showDatePicker());

        // Edit Profile Image
        profileImage.setClipToOutline(true);
        profileImage.setOnClickListener(v -> openGalleryToPickImage());
    }

    private void updateUIFromUserProfile() {
        tvUserName.setText(userProfile.getName());
        tvUserUsername.setText(userProfile.getUsername());
        tvUserBio.setText(userProfile.getBio());
        tvUserGender.setText(userProfile.getGender());
        tvBirthDate.setText(userProfile.getBirthDate());

        if (userProfile.getProfilePicture() != null) {
            Uri uri = Uri.parse(userProfile.getProfilePicture());
            profileImage.setImageURI(uri);
        }

        checkDataChanges();
    }

    private void showGenderDialog() {
        Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.dialog_gender);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setDimAmount(0.5f);

        TextView pilihPria = dialog.findViewById(R.id.pilihPria);
        TextView pilihPerempuan = dialog.findViewById(R.id.pilihPerempuan);
        TextView pilihLainnya = dialog.findViewById(R.id.pilihLainnya);

        pilihPria.setOnClickListener(v -> {
            userProfile.setGender("Pria");
            updateUIFromUserProfile();
            dialog.dismiss();
        });

        pilihPerempuan.setOnClickListener(v -> {
            userProfile.setGender("Perempuan");
            updateUIFromUserProfile();
            dialog.dismiss();
        });

        pilihLainnya.setOnClickListener(v -> {
            userProfile.setGender("Lainnya");
            updateUIFromUserProfile();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void openGalleryToPickImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.SpinnerDatePickerDialog,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = selectedDay + " " + getMonthName(selectedMonth) + " " + selectedYear;
                    userProfile.setBirthDate(formattedDate);
                    updateUIFromUserProfile();
                },
                year, month, day
        );

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private String getMonthName(int month) {
        String[] months = {"Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        return months[month];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_GALLERY) {
                Uri selectedImage = data.getData();
                profileImage.setImageURI(selectedImage);
                userProfile.setProfilePicture(selectedImage.toString());
                checkDataChanges();
            } else {
                UserProfile updatedProfile = data.getParcelableExtra("user_profile");
                if (updatedProfile != null) {
                    userProfile = updatedProfile;
                    updateUIFromUserProfile();
                }
            }
        }
    }

    private void checkDataChanges() {
        if (originalProfile != null && userProfile != null) {
            isDataChanged = !userProfile.equals(originalProfile);

            if (isDataChanged) {
                saveIcon.setVisibility(View.VISIBLE);
                saveIcon.setImageResource(R.drawable.check_orange);
            } else {
                saveIcon.setVisibility(View.GONE);
            }
        }
    }

}
