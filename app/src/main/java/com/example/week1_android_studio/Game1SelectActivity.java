package com.example.week1_android_studio;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game1SelectActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] buttons = new Button[10]; //버튼 배열
    private ArrayList<String> wordList = new ArrayList<String>(); //영단어 리스트
    private ArrayList<String> meaningList  = new ArrayList<String>(); //한글뜻 리스트
    private ArrayList<String> originalWordList = new ArrayList<>(); //영단어 리스트 원본
    private ArrayList<String> originalMeaningList = new ArrayList<>(); //뜻 리스트 원본
    private ArrayList<String> thirtyWordList = new ArrayList<>();
    private ArrayList<String> thirtyMeaningList = new ArrayList<>();
    private ArrayList<Game1MemoryCard> cards = new ArrayList<Game1MemoryCard>(); //카드 리스트
    private TextView resultText; //결과 텍스트
    //private TextView resetBtn; //초기화 버튼

    int preCardPosition = -1;

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

        String day = getIntent().getStringExtra("day");
        String jsonName = "jsons/" + day + ".json";

        InputStream is = assetManager.open(jsonName);

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
            String word = jo.getString("val2");
            String meaning = jo.getString("val3");

            //인덱스가 같으면 뜻, 단어 매치
            thirtyWordList.add(word);
            thirtyMeaningList.add(meaning);
        }

        //인덱스 번호 5개 고르기(30개 중)
        Random rnd = new Random();
        int[] idx = new int[5];
        for(int i = 0; i < 5; i++) {
            idx[i]=rnd.nextInt(30);
            for(int j = 0; j < i; j++) {
                if(idx[i]==idx[j]) {
                    i--;
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            wordList.add(thirtyWordList.get(idx[i]));
            meaningList.add(thirtyMeaningList.get(idx[i]));
            originalWordList.add(thirtyWordList.get(idx[i]));
            originalMeaningList.add(thirtyMeaningList.get(idx[i]));
        }

        //순서 섞기
        Collections.shuffle(wordList);
        Collections.shuffle(meaningList);

        for(int i = 0; i < buttons.length; i++) {
            String buttonID = "imageBtn"+i;
            int resourceID = getResources().getIdentifier(buttonID,  "id", getPackageName());
            buttons[i] = findViewById(resourceID);

            //각 버튼에 클릭이벤트 적용
            buttons[i].setOnClickListener(this);

            //카드 리스트에 담기
            if(i % 2==0) {
                Game1MemoryCard wordtmp = new Game1MemoryCard(wordList.get(i/2), false, false);
                Game1MemoryCard meaningtmp = new Game1MemoryCard(meaningList.get(i/2), false, false);
                cards.add(wordtmp);
                cards.add(meaningtmp);
            }

            //기본 이미지로 변경
            buttons[i].setBackgroundResource(R.drawable.question);

            buttons[i].setAlpha(1.0f);
        }
        //결과 텍스트 초기화
        resultText.setText("");
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

        updateModel(position);
        updateView(position);
    }

    //데이터 변경
    private void updateModel(int position) {
        Game1MemoryCard card = cards.get(position);

        //나중에 카드 비교할 때 뒤집고 다시 클릭하는 것 방지
        if(card.isFaceUp()) {
            Toast.makeText(this, "이미 선택된 카드입니다", Toast.LENGTH_SHORT).show();
            return;
        }

        if(preCardPosition==-1) { //뒤집힌 카드: 이전에 뒤집힌 카드 0 또는 2개 일 때
            restoreCard(); //카드 초기화

            preCardPosition = position; //위치 저장
        }
        else { //이전에 뒤집힌 카드가 1개일 때
            checkForMatch(preCardPosition, position);
            preCardPosition=-1;
        }
        //반대 값 넣어주기
        cards.get(position).setFaceUp(!card.isFaceUp());

    }

    //뷰 변경
    private void updateView(int position) {
        Game1MemoryCard card = cards.get(position);

        //뒤집었으면 랜덤 이미지로 보여준다
        if(card.isFaceUp()) {
            buttons[position].setBackgroundResource(R.drawable.button_drawable_background);
            buttons[position].setText(cards.get(position).getImageId());
        }
        else {
            buttons[position].setBackgroundResource(R.drawable.button_drawable_background);
        }
    }

    //매치되지 않은 카드 초기화
    private void restoreCard() {
        for(int i = 0; i < cards.size(); i++) {
            //매치되지 않은 것
            if(!cards.get(i).isMatched()) {
                //이미지 앞으로
                buttons[i].setBackgroundResource(R.drawable.question);
                buttons[i].setText("");

                //데이터 수정
                cards.get(i).setFaceUp(false);
            }
        }
    }

    /*
    카드 이미지 비교
    prePosition : 이전 카드 위치
    position: 현재 카드 위치
     */
    private void checkForMatch(int prePosition, int position) {
        //처음과 두번째가 매치 된다면.... 어케 짜지
        String pretmp = cards.get(prePosition).getImageId();
        String posttmp = cards.get(position).getImageId();

        if(returnIndex(prePosition) == returnIndex(position)) {
            resultText.setText("매치 성공");

            cards.get(prePosition).setMatched(true);
            cards.get(position).setMatched(true);

            //색상 변경
            buttons[prePosition].setAlpha(0.1f);
            buttons[position].setAlpha(0.1f);

            //완료 체크
            checkCompletion();
        }
        else {
            resultText.setText("매치 실패");
        }

    }

    private int returnIndex(int position) {
        String tmp = cards.get(position).getImageId();
        if(originalWordList.contains(tmp)) { return originalWordList.indexOf(tmp); }
        else { return originalMeaningList.indexOf(tmp); }
    }

    private void checkCompletion() {
        int cnt=0;
        for(int i=0; i<cards.size(); i++) {
            if(cards.get(i).isMatched()) {
                cnt++;
            }
        }

        if(cnt==cards.size()) {
            resultText.setText("게임 종료");
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
