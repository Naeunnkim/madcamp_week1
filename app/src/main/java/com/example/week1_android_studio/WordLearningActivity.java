package com.example.week1_android_studio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WordLearningActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_learning_page);
    }

//    private void initUI(ViewGroup rootView) throws IOException, JSONException {
//        ArrayList<String> englishDataSet = new ArrayList<>();
//        ArrayList<String> koreanDataSet = new ArrayList<>();
//
//        AssetManager assetManager = getAssets();
//        InputStream is = assetManager.open("jsons/contacts.json");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//        StringBuilder buffer = new StringBuilder();
//        String line = reader.readLine();
//        while (line != null) {
//            buffer.append(line + "\n");
//            line = reader.readLine();
//        }
//
//        String jsonData = buffer.toString();
//
//        JSONArray jsonArray = new JSONArray(jsonData);
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jo = jsonArray.getJSONObject(i);
//            String name = jo.getString("Name");
//            String phone = jo.getString("tel");
//            englishDataSet.add(name);
//            koreanDataSet.add(phone);
//        }
//
//        RecyclerView recyclerView = rootView.findViewById(R.id.word_learning_recyclerview);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        WordLearningAdapter wordLearningAdapter = new WordLearningAdapter(englishDataSet, koreanDataSet);
//
//        recyclerView.setAdapter(wordLearningAdapter);
//    }
}
