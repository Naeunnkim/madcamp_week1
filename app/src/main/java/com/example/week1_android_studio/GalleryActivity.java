package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        PhotoView photoView = findViewById(R.id.photoview);

//        ImageView imageView = (ImageView) findViewById(R.id.img);

        Intent intent = getIntent();

        Integer img = intent.getExtras().getInt("image");

        photoView.setImageResource(img);
    }
}