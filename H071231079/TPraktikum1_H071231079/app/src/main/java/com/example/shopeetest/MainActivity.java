package com.example.shopeetest;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView userName = findViewById(R.id.userName);
        Typeface poppinsFont = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");
        userName.setTypeface(poppinsFont);

    }
}