package com.example.week1_android_studio;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ContactsFragment contactsFragment;
    GalleryFragment galleryFragment;
    FreeFragment freeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsFragment = new ContactsFragment();
        galleryFragment = new GalleryFragment();
        freeFragment = new FreeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, contactsFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if(item.getItemId() == R.id.contacts){
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, contactsFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.photo) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, galleryFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.free) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, freeFragment).commit();
                    return true;
                }
                else return false;
            }
        });
    }
}