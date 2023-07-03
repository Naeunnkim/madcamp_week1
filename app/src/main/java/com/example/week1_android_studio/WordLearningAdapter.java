package com.example.week1_android_studio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordLearningAdapter extends RecyclerView.Adapter<WordLearningAdapter.ViewHolder>{

    private ArrayList<String> englishDataSet;
    private ArrayList<String> koreanDataSet;

    public interface OnItemClickListener {
        void onItemClicked(int position, String data);
    }

    //OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public void setOnItemclickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView englishTextView;
        private TextView koreanTextView;
        private LinearLayout word_learning_recyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            englishTextView = itemView.findViewById(R.id.english_word);
            koreanTextView = itemView.findViewById(R.id.korean_word);
            word_learning_recyclerview = itemView.findViewById(R.id.word_learning_recyclerview);
        }
        public TextView getEnglishTextView() {
            return englishTextView;
        }
        public TextView getKoreanTextView() {
            return koreanTextView;
        }
    }
    public WordLearningAdapter(ArrayList<String> englishSet, ArrayList<String> koreanSet) {
        englishDataSet = englishSet;
        koreanDataSet = koreanSet;
    }

    @NonNull
    @Override
    public WordLearningAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_learning, parent, false);
        return new WordLearningAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordLearningAdapter.ViewHolder holder, int position) {
        String english = englishDataSet.get(position);
        holder.englishTextView.setText(english);

        String korean = koreanDataSet.get(position);
        holder.koreanTextView.setText(korean);
    }

    @Override
    public int getItemCount() {
        return englishDataSet.size();
    }
}