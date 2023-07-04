package com.example.week1_android_studio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

public class Game2SelectActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Game2Question> questionList;
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

        //질문 리스트 가져오기
        questionList = Game2QuestionData.getQuestion();

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
                if (selectedOption != 0) {
                    Game2Question question = questionList.get(currentPosition - 1);

                    //정답 체크
                    if (selectedOption != question.getCorrect_answer()) {
                        setColor(selectedOption, R.drawable.wrong_option_background);
                        callDialog("오답", question.getCorrect_answer()+"번");
                    } else { //정답인 경우
                        callDialog("정답", "맞았습니");
                        score++;
                    }
                    setColor(question.getCorrect_answer(), R.drawable.correct_option_background);

                    if (currentPosition == questionList.size()) {
                        submitBtn.setText(getString(R.string.submit, "끝"));
                    } else {
                        submitBtn.setText(getString(R.string.submit, "다음"));
                    }
                } else {
                    // 위치 값 상승
                    currentPosition++;
                    if (currentPosition <= questionList.size()) {
                        getQuestionData();
                    } else {
//                        // 결과 액티비티로 이동하는 코드
//                        Intent intent = new Intent(~~);
//                        intent.putExtra("score", score);
//                        intent.putExtra("totalSize", questionList.size());
//                        startActivity(intent);
                        finish();
                    }
                }
                selectedOption=0;
            }
        });
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

    private void callDialog(String alertTitle, String correctAnswer) {
        new AlertDialog.Builder(this)
                .setTitle(alertTitle)
                .setMessage("정답: " + correctAnswer)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    //툴바 뒤로가기
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

        setSubmitBtn("제출");
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
            op.setTextColor(Color.parseColor("#FFFFFFFF"));
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