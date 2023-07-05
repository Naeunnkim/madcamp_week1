package com.example.week1_android_studio;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ContactsFragment contactsFragment;
    GalleryFragment galleryFragment;
    FreeFragment freeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsFragment = new ContactsFragment();

        try {
            putJsonContacts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

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

    public void putJsonContacts() throws IOException, JSONException {
        AssetManager assetManager = getAssets();
        InputStream is = assetManager.open("jsons/contacts.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line + "\n");
            line = reader.readLine();
        }

        String jsonData = buffer.toString();

        JSONArray jsonArray = new JSONArray(jsonData);

        JSONArray new_jsonArray = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            JSONObject new_jo = new JSONObject();

            String name = jo.getString("Name");
            String phone = jo.getString("tel");
            String email_address = jo.getString("email");
            String image_path = jo.getString("pic");

            new_jo.put("Name", name);
            new_jo.put("tel", phone);
            new_jo.put("email", email_address);
            new_jo.put("pic", image_path);

            new_jsonArray.put(new_jo);
        }

        String filename = "contactsInfo.json";
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(new_jsonArray.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}