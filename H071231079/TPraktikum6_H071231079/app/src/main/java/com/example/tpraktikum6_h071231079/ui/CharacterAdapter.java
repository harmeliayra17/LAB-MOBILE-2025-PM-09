package com.example.tpraktikum6_h071231079.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tpraktikum6_h071231079.R;
import com.example.tpraktikum6_h071231079.data.response.Character;
import com.example.tpraktikum6_h071231079.home.DetailActivity;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final List<Character> characterList;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);

        // set teks
        holder.tvName.setText(character.getName());
        holder.tvSpecies.setText(character.getSpecies());

        // load gambar
        Glide.with(holder.itemView.getContext())
                .load(character.getImage())
                .centerCrop()
                .into(holder.imgCharacter);

        // klik untuk ke DetailActivity dengan semua datanya
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_NAME, character.getName());
            intent.putExtra(DetailActivity.EXTRA_STATUS, character.getStatus());
            intent.putExtra(DetailActivity.EXTRA_SPECIES, character.getSpecies());
            intent.putExtra(DetailActivity.EXTRA_GENDER, character.getGender());
            intent.putExtra(DetailActivity.EXTRA_IMAGE, character.getImage());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCharacter;
        TextView tvName, tvSpecies;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.imgCharacter);
            tvName       = itemView.findViewById(R.id.tvName);
            tvSpecies    = itemView.findViewById(R.id.tvSpecies);
        }
    }
}
