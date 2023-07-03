package com.example.week1_android_studio;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Game1MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[10]; //버튼 배열
    private ArrayList<String> wordList = new ArrayList<String>(); //영단어 리스트
    private ArrayList<String> meaningList  = new ArrayList<String>(); //한글뜻 리스트
    private ArrayList<Game1MemoryCard> cards = new ArrayList<Game1MemoryCard>(); //카드 리스트
    private TextView resultText; //결과 텍스트
    private TextView resetBtn; //초기화 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1_cardflip);

        Toolbar mToolbar = findViewById(R.id.game1_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        resultText = findViewById(R.id.game1_text);

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //초기화
    public void init() throws IOException, JSONException {
        //리스트에 데이터 등록
        AssetManager assetManager = this.getAssets();
        InputStream is = assetManager.open("jsons/contacts.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while(line!=null) {
            buffer.append(line + "\n");
            line = reader.readLine();
        }

        String jsonData = buffer.toString();

        JSONArray jsonArray = new JSONArray(jsonData);

        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String name = jo.getString("Name");
            String phone = jo.getString("tel");

            wordList.add(name);
            meaningList.add(phone);
        }

        //순서 섞기
        Collections.shuffle(wordList);
        Collections.shuffle(meaningList);

        for(int i=0; i<buttons.length; i++) {
            String buttonID = "imageBtn"+i;
            int resourceID = getResources().getIdentifier(buttonID,  "id", getPackageName());
            buttons[i] = findViewById(resourceID);

            //각 버튼에 클릭이벤트 적용
            buttons[i].setOnClickListener(this);

            //카드 리스트에 담기
            if(i%2==0) {
                Game1MemoryCard wordtmp = new Game1MemoryCard(wordList.get(i/2), false, false);
                Game1MemoryCard meaningtmp = new Game1MemoryCard(meaningList.get(i/2), false, false);
                cards.add(wordtmp);
                cards.add(meaningtmp);
            }

            //버튼 리스트에 각각의 텍스트 지정해주기...
            buttons[i].setText(cards.get(i).getImageId());

        }
    }

    public void onClick(View view) {
        int id = view.getId();
        int position = 0;

        if (id == R.id.imageBtn0) {
            position = 0;
        } else if (id == R.id.imageBtn1) {
            position = 1;
        } else if (id == R.id.imageBtn2) {
            position = 2;
        } else if (id == R.id.imageBtn3) {
            position = 3;
        } else if (id == R.id.imageBtn4) {
            position = 4;
        } else if (id == R.id.imageBtn5) {
            position = 5;
        } else if (id == R.id.imageBtn6) {
            position = 6;
        } else if (id == R.id.imageBtn7) {
            position = 7;
        } else if (id == R.id.imageBtn8) {
            position = 8;
        } else if (id == R.id.imageBtn9) {
            position = 9;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
