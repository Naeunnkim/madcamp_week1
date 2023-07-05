package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;

import java.io.IOException;

public class WordLearningActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] day_buttons = new Button[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_learning_select_button);

        Toolbar mToolbar = findViewById(R.id.word_learning_day_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("단어 학습");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws IOException, JSONException {
        for(int i = 0; i < day_buttons.length; i++) {
            String buttonID = "day_button"+i;
            int resourceID = getResources().getIdentifier(buttonID,  "id", getPackageName());
            day_buttons[i] = findViewById(resourceID);
            day_buttons[i].setOnClickListener(this);
            }
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
    @Override
    public void onClick(View view) {
        int id = view.getId();
        String day = "day1";

        if (id == R.id.day_button0) {
            day = "day1";
        } else if (id == R.id.day_button1) {
            day = "day2";
        } else if (id == R.id.day_button2) {
            day = "day3";
        } else if (id == R.id.day_button3) {
            day = "day4";
        } else if (id == R.id.day_button4) {
            day = "day5";
        } else if (id == R.id.day_button5) {
            day = "day6";
        } else if (id == R.id.day_button6) {
            day = "day7";
        } else if (id == R.id.day_button7) {
            day = "day8";
        } else if (id == R.id.day_button8) {
            day = "day9";
        } else if (id == R.id.day_button9) {
            day = "day10";
        } else if (id == R.id.day_button10) {
            day = "day11";
        } else if (id == R.id.day_button11) {
            day = "day12";
        } else if (id == R.id.day_button12) {
            day = "day13";
        } else if (id == R.id.day_button13) {
            day = "day14";
        } else if (id == R.id.day_button14) {
            day = "day15";
        } else if (id == R.id.day_button15) {
            day = "day16";
        } else if (id == R.id.day_button16) {
            day = "day17";
        } else if (id == R.id.day_button17) {
            day = "day18";
        } else if (id == R.id.day_button18) {
            day = "day19";
        } else if (id == R.id.day_button19) {
            day = "day20";
        } else if (id == R.id.day_button20) {
            day = "day21";
        } else if (id == R.id.day_button21) {
            day = "day22";
        } else if (id == R.id.day_button22) {
            day = "day23";
        } else if (id == R.id.day_button23) {
            day = "day24";
        } else if (id == R.id.day_button24) {
            day = "day25";
        } else if (id == R.id.day_button25) {
            day = "day26";
        } else if (id == R.id.day_button26) {
            day = "day27";
        } else if (id == R.id.day_button27) {
            day = "day28";
        } else if (id == R.id.day_button28) {
            day = "day29";
        } else if (id == R.id.day_button29) {
            day = "day30";
        }
        Intent intent = new Intent(getApplicationContext(), WordLearningSelectedActivity.class);
        intent.putExtra("day", day);
        startActivity(intent);
    }
}


