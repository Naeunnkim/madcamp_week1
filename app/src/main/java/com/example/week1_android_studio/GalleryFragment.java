package com.example.week1_android_studio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
        ArrayList<GalleryData> dataset = new ArrayList<>();
        for(int i=0; i<3; i++) {
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.galleryView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

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
    }
}