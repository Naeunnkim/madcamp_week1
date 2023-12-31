package com.example.week1_android_studio;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordLearningSelectedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_learning_page);

        Toolbar mToolbar = findViewById(R.id.word_learning_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("단어 학습");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        try {
            initUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUI() throws IOException, JSONException {
        ArrayList<String> englishDataSet = new ArrayList<>();
        ArrayList<String> koreanDataSet = new ArrayList<>();

        String day = getIntent().getStringExtra("day");
        String jsonName = "jsons/" + day + ".json";

        AssetManager assetManager = getAssets();
        InputStream is = assetManager.open(jsonName);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder buffer = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
        buffer.append(line + "\n");
            line = reader.readLine();
        }

        String jsonData = buffer.toString();

        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String english_word = jo.getString("val2");
            String korean_word = jo.getString("val3");
            englishDataSet.add(english_word);
            koreanDataSet.add(korean_word);
        }

        RecyclerView recyclerView = findViewById(R.id.word_learning_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        WordLearningAdapter wordLearningAdapter = new WordLearningAdapter(englishDataSet, koreanDataSet);

        recyclerView.setAdapter(wordLearningAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
