package com.example.tp4_H071231079;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HighlightViewerActivity extends AppCompatActivity {
    ImageView imageHighlightFull, imageHighlight;
    TextView textHighlightName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight_viewer);

        imageHighlightFull = findViewById(R.id.imageHighlightFull);
        imageHighlight = findViewById(R.id.imageHighlight);
        textHighlightName = findViewById(R.id.textHighlightName);

        // Ambil data dari intent
        Highlight highlight = getIntent().getParcelableExtra("highlight");
        String title = getIntent().getStringExtra("title");
        int imageHighlightRes = getIntent().getIntExtra("imagehighlight", -1);

        if (highlight != null) {
            imageHighlightFull.setImageResource(highlight.getImageResId());
        }

        if (title != null) {
            textHighlightName.setText(title);
        }

        if (imageHighlightRes != -1) {
            imageHighlight.setImageResource(imageHighlightRes);
        }
    }
}
