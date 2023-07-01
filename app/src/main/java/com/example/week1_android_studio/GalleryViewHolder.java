package com.example.week1_android_studio;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;

    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
    }

    public void onBind(GalleryData data) {
        img.setImageResource(data.getImage());
    }
}
