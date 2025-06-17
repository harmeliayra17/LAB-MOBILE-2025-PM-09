package com.example.labpertemuan8;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private final ArrayList<Notes> notes = new ArrayList<>();
    private final Activity activity;

    public NotesAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setNotes(ArrayList<Notes> notes) {
        this.notes.clear();
        if (notes.size() > 0) {
            this.notes.addAll(notes);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvTimestamp;
        final CardView cardView;

        NotesViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            cardView = itemView.findViewById(R.id.card_view);
        }

        void bind(Notes notes) {
            tvTitle.setText(notes.getTitle());
            tvDescription.setText(notes.getDescription());

            if (notes.getCreatedAt().equals(notes.getUpdatedAt())) {
                tvTimestamp.setText("Created at " + notes.getCreatedAt());
            } else {
                tvTimestamp.setText("Updated at " + notes.getUpdatedAt());
            }

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, FormActivity.class);
                intent.putExtra(FormActivity.EXTRA_NOTES, notes);
                activity.startActivityForResult(intent, FormActivity.REQUEST_UPDATE);
            });
        }
    }
}
