package com.example.week1_android_studio;

//public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    //adapter에 들어갈 list
//    private ArrayList<GalleryData> listData = new ArrayList<>();
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
//        return new GalleryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        ((GalleryViewHolder)holder).onBind(listData.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }
//
//    void addItem(GalleryData data) {
//        //외부에서 item 추가시키는 함수
//        listData.add(data);
//    }
//}

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{

    private ArrayList<GalleryData> listData = new ArrayList<>();

    //click event implementation
    //OnItemClickListener 인터페이스 선언
    public interface OnItemClickListener {
        void onItemClicked(int position, String data);
    }

    //OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public void setOnItemclickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }

        public ImageView getImageView() { return img; }

        public void onBind(GalleryData data) {
            img.setImageResource(data.getImage());
        }
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        GalleryAdapter.GalleryViewHolder viewHolder = new GalleryAdapter.GalleryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.GalleryViewHolder holder, int position) {
        holder.onBind(listData.get(position));
        GalleryData picture = listData.get(position);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                Intent galleryActivity = new Intent(context, GalleryActivity.class);

                galleryActivity.putExtra("image", picture.getImage());

                ((MainActivity)context).startActivity(galleryActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(GalleryData data) {
        //외부에서 item 추가시키는 함수
        listData.add(data);
    }
}
