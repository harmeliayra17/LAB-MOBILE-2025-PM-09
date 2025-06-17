package com.example.tpraktikum6_h071231079.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpraktikum6_h071231079.R;
import com.example.tpraktikum6_h071231079.data.network.APIService;
import com.example.tpraktikum6_h071231079.data.network.ApiConfig;
import com.example.tpraktikum6_h071231079.data.response.Character;
import com.example.tpraktikum6_h071231079.data.response.CharacterResponse;
import com.example.tpraktikum6_h071231079.ui.CharacterAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rvCharacters;
    private Button btnLoadMore;

    private CharacterAdapter characterAdapter;
    private final List<Character> characterList = new ArrayList<>();

    // pagenya
    private int currentPage = 1;
    private int totalPages  = Integer.MAX_VALUE;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle      = findViewById(R.id.tvTitle);
        rvCharacters = findViewById(R.id.rvCharacters);
        btnLoadMore  = findViewById(R.id.btnLoadMore);

        rvCharacters.setLayoutManager(new GridLayoutManager(this, 2));
        characterAdapter = new CharacterAdapter(characterList);
        rvCharacters.setAdapter(characterAdapter);

        btnLoadMore.setEnabled(false);

        loadCharacters();

        btnLoadMore.setOnClickListener(v -> {
            if (!isLoading && currentPage <= totalPages) {
                btnLoadMore.setEnabled(false);
                loadCharacters();
            }
        });
    }

    private void loadCharacters() {
        isLoading = true;

        APIService apiService = ApiConfig.getApiService();
        apiService.getCharacters(currentPage).enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CharacterResponse resp = response.body();

                    // add item baru
                    int startPos = characterList.size();
                    characterList.addAll(resp.getResults());
                    characterAdapter.notifyItemRangeInserted(
                            startPos,
                            resp.getResults().size()
                    );

                    // update info pagenya
                    totalPages = resp.getInfo().getPages();
                    currentPage++;
                } else {
                    Toast.makeText(MainActivity.this,
                            "Gagal memuat data",
                            Toast.LENGTH_SHORT).show();
                }

                isLoading = false;

                // aktifkan tombol kalau masih ada halaman berikutnya
                if (currentPage <= totalPages) {
                    btnLoadMore.setEnabled(true);
                } else {
                    btnLoadMore.setVisibility(Button.GONE);
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                isLoading = false;
                // izinkan retry
                btnLoadMore.setEnabled(true);

                Toast.makeText(MainActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage(), t);
            }
        });
    }
}
