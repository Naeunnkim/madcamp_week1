package com.example.week1_android_studio;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.Random;

public class Game3SelectActivity extends AppCompatActivity{
    private String WORD;
    private String MEAN;

    Dialog dialog;

    private String[] getWord() throws IOException, JSONException {
        ArrayList<String> englishDataSet = new ArrayList<>();
        ArrayList<String> koreanDataSet = new ArrayList<>();

        String[] eng_kor = new String[2];

//        String day = getIntent().getStringExtra("day");
//        String jsonName = "jsons/" + day + ".json";

        AssetManager assetManager = getAssets();
        InputStream is = assetManager.open("jsons/wordle.json");

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
            String english_word = jo.getString("val0");
            String korean_word = jo.getString("val2");
            englishDataSet.add(english_word);
            koreanDataSet.add(korean_word);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(englishDataSet.size());
        eng_kor[0] = englishDataSet.get(randomIndex);
        eng_kor[1] = koreanDataSet.get(randomIndex);
        return eng_kor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3_play_layout);

        dialog = new Dialog(Game3SelectActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.how_to_play_wordle);

        try {
            String[] word_mean = getWord();
            WORD = word_mean[0];
            MEAN = word_mean[1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Toolbar mToolbar = findViewById(R.id.game3_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        makeGameInactive();
        findViewById(R.id.edt_11).setEnabled(true);
//        findViewById(android.R.id.home).setEnabled(true);

        keepPassingFocus();

        EditText lastEditText1 = findViewById(R.id.edt_15);
        EditText lastEditText2 = findViewById(R.id.edt_25);
        EditText lastEditText3 = findViewById(R.id.edt_35);
        EditText lastEditText4 = findViewById(R.id.edt_45);
        EditText lastEditText5 = findViewById(R.id.edt_55);
        EditText lastEditText6 = findViewById(R.id.edt_65);

        lastEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_11),
                            findViewById(R.id.edt_12),
                            findViewById(R.id.edt_13),
                            findViewById(R.id.edt_14),
                            findViewById(R.id.edt_15)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_11),
                            findViewById(R.id.edt_12),
                            findViewById(R.id.edt_13),
                            findViewById(R.id.edt_14),
                            findViewById(R.id.edt_15)
                    );
                }
            }
        });

        lastEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_21),
                            findViewById(R.id.edt_22),
                            findViewById(R.id.edt_23),
                            findViewById(R.id.edt_24),
                            findViewById(R.id.edt_25)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_21),
                            findViewById(R.id.edt_22),
                            findViewById(R.id.edt_23),
                            findViewById(R.id.edt_24),
                            findViewById(R.id.edt_25)
                    );
                }
            }
        });

        lastEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_31),
                            findViewById(R.id.edt_32),
                            findViewById(R.id.edt_33),
                            findViewById(R.id.edt_34),
                            findViewById(R.id.edt_35)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_31),
                            findViewById(R.id.edt_32),
                            findViewById(R.id.edt_33),
                            findViewById(R.id.edt_34),
                            findViewById(R.id.edt_35)
                    );
                }
            }
        });

        lastEditText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_41),
                            findViewById(R.id.edt_42),
                            findViewById(R.id.edt_43),
                            findViewById(R.id.edt_44),
                            findViewById(R.id.edt_45)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_41),
                            findViewById(R.id.edt_42),
                            findViewById(R.id.edt_43),
                            findViewById(R.id.edt_44),
                            findViewById(R.id.edt_45)
                    );
                }
            }
        });

        lastEditText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_51),
                            findViewById(R.id.edt_52),
                            findViewById(R.id.edt_53),
                            findViewById(R.id.edt_54),
                            findViewById(R.id.edt_55)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_51),
                            findViewById(R.id.edt_52),
                            findViewById(R.id.edt_53),
                            findViewById(R.id.edt_54),
                            findViewById(R.id.edt_55)
                    );
                }
            }
        });

        lastEditText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    validateRow(
                            findViewById(R.id.edt_61),
                            findViewById(R.id.edt_62),
                            findViewById(R.id.edt_63),
                            findViewById(R.id.edt_64),
                            findViewById(R.id.edt_65)
                    );
                    CompleteWord(
                            findViewById(R.id.edt_61),
                            findViewById(R.id.edt_62),
                            findViewById(R.id.edt_63),
                            findViewById(R.id.edt_64),
                            findViewById(R.id.edt_65)
                    );
                }

            }
        });
    }

    private void makeGameInactive() {
        findViewById(R.id.edt_11).setEnabled(false);
        findViewById(R.id.edt_12).setEnabled(false);
        findViewById(R.id.edt_13).setEnabled(false);
        findViewById(R.id.edt_14).setEnabled(false);
        findViewById(R.id.edt_15).setEnabled(false);
        findViewById(R.id.edt_21).setEnabled(false);
        findViewById(R.id.edt_22).setEnabled(false);
        findViewById(R.id.edt_23).setEnabled(false);
        findViewById(R.id.edt_24).setEnabled(false);
        findViewById(R.id.edt_25).setEnabled(false);
        findViewById(R.id.edt_31).setEnabled(false);
        findViewById(R.id.edt_32).setEnabled(false);
        findViewById(R.id.edt_33).setEnabled(false);
        findViewById(R.id.edt_34).setEnabled(false);
        findViewById(R.id.edt_35).setEnabled(false);
        findViewById(R.id.edt_41).setEnabled(false);
        findViewById(R.id.edt_42).setEnabled(false);
        findViewById(R.id.edt_43).setEnabled(false);
        findViewById(R.id.edt_44).setEnabled(false);
        findViewById(R.id.edt_45).setEnabled(false);
        findViewById(R.id.edt_51).setEnabled(false);
        findViewById(R.id.edt_52).setEnabled(false);
        findViewById(R.id.edt_53).setEnabled(false);
        findViewById(R.id.edt_54).setEnabled(false);
        findViewById(R.id.edt_55).setEnabled(false);
        findViewById(R.id.edt_61).setEnabled(false);
        findViewById(R.id.edt_62).setEnabled(false);
        findViewById(R.id.edt_63).setEnabled(false);
        findViewById(R.id.edt_64).setEnabled(false);
        findViewById(R.id.edt_65).setEnabled(false);
    }

    private void validateRow(
            EditText editText1,
            EditText editText2,
            EditText editText3,
            EditText editText4,
            EditText editText5
    ){
        String edt1Str = editText1.getText().toString();
        String edt2Str = editText2.getText().toString();
        String edt3Str = editText3.getText().toString();
        String edt4Str = editText4.getText().toString();
        String edt5Str = editText5.getText().toString();

        char edt1Cha = edt1Str.charAt(0);
        char edt2Cha = edt2Str.charAt(0);
        char edt3Cha = edt3Str.charAt(0);
        char edt4Cha = edt4Str.charAt(0);
        char edt5Cha = edt5Str.charAt(0);

        char c1 = WORD.charAt(0);
        char c2 = WORD.charAt(1);
        char c3 = WORD.charAt(2);
        char c4 = WORD.charAt(3);
        char c5 = WORD.charAt(4);

        String edtWord = edt1Str + edt2Str + edt3Str + edt4Str + edt5Str;

        String[] color_lst = check_color(edtWord, WORD);

        editText1.setBackgroundColor(Color.parseColor(color_lst[0]));
        editText2.setBackgroundColor(Color.parseColor(color_lst[1]));
        editText3.setBackgroundColor(Color.parseColor(color_lst[2]));
        editText4.setBackgroundColor(Color.parseColor(color_lst[3]));
        editText5.setBackgroundColor(Color.parseColor(color_lst[4]));

        if (edt1Cha == c1 && edt2Cha == c2 && edt3Cha == c3 && edt4Cha == c4 && edt5Cha == c5){
            TextView endTextView = findViewById(R.id.id_ending_text);
            String message = "축하합니다! 뜻은 " + MEAN + "입니다!";
            endTextView.setText(message);
            endTextView.setVisibility(View.VISIBLE);
            makeGameInactive();
            return;
        }

        if (editText5.getId() == R.id.edt_65){
            TextView endTextView = findViewById(R.id.id_ending_text);
            String message = "아쉽네요ㅠㅠ 정답은" + WORD + "였습니다";
            endTextView.setText(message);
            endTextView.setVisibility(View.VISIBLE);
            makeGameInactive();
        }
    }
    private String[] check_color(String word1, String word2){
        String[] color_list = new String[5];
        String word2_copy = new String(word2);
        for (int i = 0; i < word2.length(); i++){
            if (word1.charAt(i) == word2.charAt(i)){
                word2_copy = word2_copy.replaceFirst(String.valueOf(word1.charAt(i)), "_");
                color_list[i] = "#33CC33";
            } else {
                color_list[i] = "#808080";
            }
        }
        for (int i = 0; i < word2.length(); i++){
            if (word2_copy.contains(String.valueOf(word1.charAt(i))) && word1.charAt(i) != word2.charAt(i)){
                word2_copy = word2_copy.replaceFirst(String.valueOf(word1.charAt(i)), "_");
                color_list[i] = "#FFFF00";
            }
        }
        return color_list;
    }

    private void CompleteWord(
            EditText editText1,
            EditText editText2,
            EditText editText3,
            EditText editText4,
            EditText editText5
    ) {
        editText1.setEnabled(false);
        editText2.setEnabled(false);
        editText3.setEnabled(false);
        editText4.setEnabled(false);
        editText5.setEnabled(false);
    }

    private void keepPassingFocus(){
        passFocusToNextEdt(findViewById(R.id.edt_11), findViewById(R.id.edt_12));
        passFocusToNextEdt(findViewById(R.id.edt_12), findViewById(R.id.edt_13));
        passFocusToNextEdt(findViewById(R.id.edt_13), findViewById(R.id.edt_14));
        passFocusToNextEdt(findViewById(R.id.edt_14), findViewById(R.id.edt_15));
        passFocusToNextEdt(findViewById(R.id.edt_15), findViewById(R.id.edt_21));

        passFocusToNextEdt(findViewById(R.id.edt_21), findViewById(R.id.edt_22));
        passFocusToNextEdt(findViewById(R.id.edt_22), findViewById(R.id.edt_23));
        passFocusToNextEdt(findViewById(R.id.edt_23), findViewById(R.id.edt_24));
        passFocusToNextEdt(findViewById(R.id.edt_24), findViewById(R.id.edt_25));
        passFocusToNextEdt(findViewById(R.id.edt_25), findViewById(R.id.edt_31));

        passFocusToNextEdt(findViewById(R.id.edt_31), findViewById(R.id.edt_32));
        passFocusToNextEdt(findViewById(R.id.edt_32), findViewById(R.id.edt_33));
        passFocusToNextEdt(findViewById(R.id.edt_33), findViewById(R.id.edt_34));
        passFocusToNextEdt(findViewById(R.id.edt_34), findViewById(R.id.edt_35));
        passFocusToNextEdt(findViewById(R.id.edt_35), findViewById(R.id.edt_41));

        passFocusToNextEdt(findViewById(R.id.edt_41), findViewById(R.id.edt_42));
        passFocusToNextEdt(findViewById(R.id.edt_42), findViewById(R.id.edt_43));
        passFocusToNextEdt(findViewById(R.id.edt_43), findViewById(R.id.edt_44));
        passFocusToNextEdt(findViewById(R.id.edt_44), findViewById(R.id.edt_45));
        passFocusToNextEdt(findViewById(R.id.edt_45), findViewById(R.id.edt_51));

        passFocusToNextEdt(findViewById(R.id.edt_51), findViewById(R.id.edt_52));
        passFocusToNextEdt(findViewById(R.id.edt_52), findViewById(R.id.edt_53));
        passFocusToNextEdt(findViewById(R.id.edt_53), findViewById(R.id.edt_54));
        passFocusToNextEdt(findViewById(R.id.edt_54), findViewById(R.id.edt_55));
        passFocusToNextEdt(findViewById(R.id.edt_55), findViewById(R.id.edt_61));

        passFocusToNextEdt(findViewById(R.id.edt_61), findViewById(R.id.edt_62));
        passFocusToNextEdt(findViewById(R.id.edt_62), findViewById(R.id.edt_63));
        passFocusToNextEdt(findViewById(R.id.edt_63), findViewById(R.id.edt_64));
        passFocusToNextEdt(findViewById(R.id.edt_64), findViewById(R.id.edt_65));
    }

    private void passFocusToNextEdt(EditText editText1, EditText editText2){
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    editText2.setEnabled(true);
                    editText2.requestFocus();
                }
                if (editable.length() == 0){
                    editText2.setEnabled(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wordle_help_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        } else if (item.getItemId() == R.id.wordle_helper) {
            showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(){
        dialog.show();
        dialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
