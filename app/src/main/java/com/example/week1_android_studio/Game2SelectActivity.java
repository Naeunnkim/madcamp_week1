package com.example.week1_android_studio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Game2SelectActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Game2Question> questionList;
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> meaningList = new ArrayList<>();
    private int currentPosition =1; //질문 위치
    private int selectedOption =0; //선택 옵션
    private int score =0; //점수

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView questionText;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2_quiz);

        Toolbar mToolbar = findViewById(R.id.game2_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progress_text);
        questionText = findViewById(R.id.question_text);
        option1 = findViewById(R.id.option1_text);
        option2 = findViewById(R.id.option2_text);
        option3 = findViewById(R.id.option3_text);
        option4 = findViewById(R.id.option4_text);
        submitBtn = findViewById(R.id.submit_btn);

        String day = getIntent().getStringExtra("day");
        String jsonName = "jsons/" + day + ".json";

        //질문 리스트 가져오기
        try {
            questionList = getQuestion(jsonName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //화면 세팅
        getQuestionData();

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        //답변 체크 이벤트
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.setEnabled(true);
                option2.setEnabled(true);
                option3.setEnabled(true);
                option4.setEnabled(true);
                if (selectedOption != 0) {
                    Game2Question question = questionList.get(currentPosition - 1);

                    //정답 체크
                    if (selectedOption != question.getCorrect_answer()) {
                        setColor(selectedOption, R.drawable.wrong_option_background);
                        option1.setEnabled(false);
                        option2.setEnabled(false);
                        option3.setEnabled(false);
                        option4.setEnabled(false);
                    } else { //정답인 경우
                        score++;
                        option1.setEnabled(false);
                        option2.setEnabled(false);
                        option3.setEnabled(false);
                        option4.setEnabled(false);
                    }
                    setColor(question.getCorrect_answer(), R.drawable.correct_option_background);

                    if (currentPosition == questionList.size()) {
                        submitBtn.setText(getString(R.string.submit, "게임 종료"));
                    } else {
                        submitBtn.setText(getString(R.string.submit, "다음 문제"));
                    }
                } else {
                    // 위치 값 상승
                    currentPosition++;
                    if (currentPosition <= questionList.size()) {
                        getQuestionData();
                    } else {
                        // 결과 액티비티로 이동하는 코드
                        Intent intent = new Intent(Game2SelectActivity.this, Game2ScoreActivity.class);
                        intent.putExtra("score", score);
                        intent.putExtra("totalSize", questionList.size());
                        startActivity(intent);
                        finish();
                    }
                }
                selectedOption=0;
            }
        });
    }

    public ArrayList<Game2Question> getQuestion(String jsonPath) throws IOException, JSONException {
        AssetManager assetManager = this.getAssets();

        InputStream is = assetManager.open(jsonPath);

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
            wordList.add(word);
            meaningList.add(meaning);
        }

        Random rnd = new Random();
        ArrayList<Game2Question> queList = new ArrayList<>();
        int[] answerlist = new int[10]; //정답 10개 미리 뽑아두기
        for(int i=0; i<10; i++) {
            answerlist[i]=rnd.nextInt(30);
            for(int j=0; j<i; j++) {
                if(answerlist[i]==answerlist[j]) { i--; }
            }
        }

        for(int i=0; i<10; i++) {
            int answer_idx = answerlist[i]; //정답에 해당하는 인덱스
            int correct_answer = rnd.nextInt(4)+1; //정답이 들어갈 보기 번호
            String question = meaningList.get(answer_idx);
            String option_one="", option_two="", option_three="", option_four="";

            //나머지 자리에 나머지 단어 넣기
            //나머지 단어들 선정
            int[] idxs = new int[3];
            for(int j=0; j<3; j++) {
                idxs[j] = rnd.nextInt(30);
                if(idxs[j]==answer_idx) { j--; }
                for(int k=0; k<j; k++) {
                    if(idxs[j]==idxs[k]) { j--; }
                }
            }

            //선지에 단어 넣기
            if(correct_answer==1) {
                option_one=wordList.get(answer_idx);
                option_two=wordList.get(idxs[0]);
                option_three=wordList.get(idxs[1]);
                option_four=wordList.get(idxs[2]);
            }
            else if(correct_answer==2) {
                option_two=wordList.get(answer_idx);
                option_one=wordList.get(idxs[0]);
                option_three=wordList.get(idxs[1]);
                option_four=wordList.get(idxs[2]);
            }
            else if(correct_answer==3) {
                option_three=wordList.get(answer_idx);
                option_one=wordList.get(idxs[0]);
                option_two=wordList.get(idxs[1]);
                option_four=wordList.get(idxs[2]);
            }
            else if(correct_answer==4) {
                option_four=wordList.get(answer_idx);
                option_one=wordList.get(idxs[0]);
                option_two=wordList.get(idxs[1]);
                option_three=wordList.get(idxs[2]);
            }

            Game2Question q = new Game2Question(i,question, option_one, option_two,
                    option_three, option_four, correct_answer);
            queList.add(q);
        }

        return queList;
    }

    private void setColor(int opt, int color) {
        if(opt==1) {
            option1.setBackground(ContextCompat.getDrawable(this, color));
        }
        else if(opt==2) {
            option2.setBackground(ContextCompat.getDrawable(this, color));
        }
        else if(opt==3) {
            option3.setBackground(ContextCompat.getDrawable(this, color));
        }
        else if(opt==4) {
            option4.setBackground(ContextCompat.getDrawable(this, color));
        }

    }

    //툴바 뒤로가기
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

    private void getQuestionData() {
        setOptionStyle();

        Game2Question question = questionList.get(currentPosition -1);

        progressBar.setProgress(currentPosition); //상태바 위치
        progressBar.setMax(questionList.size()); //상태바 최대값
        progressText.setText(getString(R.string.count_label, currentPosition, questionList.size()));//현재 위치 표시

        questionText.setText(question.getQuestion());//질문 표시

        //답변 표시
        option1.setText(question.getOption_one());
        option2.setText(question.getOption_two());
        option3.setText(question.getOption_three());
        option4.setText(question.getOption_four());

        setSubmitBtn("정답 확인");
    }

    private void setSubmitBtn(String name) {
        submitBtn.setText(getString(R.string.submit, name));
    }

    //답변 스타일 설정
    private void setOptionStyle() {
        ArrayList<Button> optionList = new ArrayList<>();

        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        optionList.add(option4);

        for(Button op : optionList) {
            op.setTextColor(Color.parseColor("#000000"));
            op.setBackground(ContextCompat.getDrawable(this, R.drawable.option_background));
            op.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    //답변 선택 이벤트
    private void selectedOptionStyle(Button btn, int opt) {
        setOptionStyle();
        selectedOption = opt;

        btn.setTextColor((Color.parseColor("#5F00FF")));
        btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_background));
        btn.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.option1_text) {
            selectedOptionStyle(option1, 1);
        }
        else if(view.getId()==R.id.option2_text) {
            selectedOptionStyle(option2, 2);
        }
        else if(view.getId()==R.id.option3_text) {
            selectedOptionStyle(option3, 3);
        }
        else if(view.getId()==R.id.option4_text) {
            selectedOptionStyle(option4, 4);
        }
    }

}