package com.example.week1_android_studio;

import java.sql.Array;
import java.util.ArrayList;

//샘플 데이터 클래스..
//이걸 json으로 받아와야 하나...??
public class Game2QuestionData {
    public static ArrayList<Game2Question> getQuestion() {
        ArrayList<Game2Question> queList = new ArrayList<>();
        //샘플 데이터
        Game2Question q1 = new Game2Question(1, "광산, 채굴하다", "imagination",
                "climate", "mine", "protection", 3);
        Game2Question q2 = new Game2Question(2, "요인", "principle", "factor",
                "theory", "desire", 2);

        queList.add(q1);
        queList.add(q2);

        return queList;
    }
}