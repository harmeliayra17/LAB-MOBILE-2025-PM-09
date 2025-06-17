package com.example.tp4_H071231079;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {
    private Context context;
    private List<Highlight> highlightList;

    public HighlightAdapter(Context context, List<Highlight> highlightList) {
        this.context = context;
        this.highlightList = highlightList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Highlight highlight = highlightList.get(position);
        holder.imageView.setImageResource(highlight.getImageResId());
        holder.titleHighlight.setText(highlight.getTitle());

        // Set click listener untuk membuka HighlightViewerActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HighlightViewerActivity.class);
            intent.putExtra("highlight", highlight);
            intent.putExtra("title", highlight.getTitle());
            intent.putExtra("imagehighlight", highlight.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleHighlight;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageHighlight);
            titleHighlight = itemView.findViewById(R.id.textHighlightName);
        }
    }
}
