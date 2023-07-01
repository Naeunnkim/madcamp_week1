package com.example.week1_android_studio;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Gallery;
import android.widget.GridLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    GalleryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_gallery, container, false);
        init(rootView);
        getData();

        return rootView;
    }

    private void init(ViewGroup rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.galleryView);
        recyclerView.addItemDecoration(new GallerySpacing(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new GalleryAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void getData() {
        GalleryData data = new GalleryData(R.drawable.img1);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img2);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img3);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img4);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img5);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img6);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img7);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img8);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img9);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img10);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img11);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img12);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img13);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img14);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img15);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img16);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img17);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img18);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img19);
        adapter.addItem(data);
        data = new GalleryData(R.drawable.img20);
        adapter.addItem(data);
    }
}