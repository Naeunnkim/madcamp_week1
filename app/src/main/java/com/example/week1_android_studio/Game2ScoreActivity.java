package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game2ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        View view = LayoutInflater.from(this).inflate(R.layout.game2_result, null);
        setContentView(view);

        int score = getIntent().getIntExtra("score", 0);
        int totalSize = getIntent().getIntExtra("totalSize", 0);

        TextView scoreText = view.findViewById(R.id.score_text);
        scoreText.setText(getString(R.string.count_label, score, totalSize));

        Button resetBtn = view.findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game2ScoreActivity.this, Game2MainActivity.class);
                startActivity(intent);
            }
        });
    }




}
