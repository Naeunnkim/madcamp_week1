package com.example.week1_android_studio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<String> nameDataSet;
    private ArrayList<String> telDataSet;
    private ArrayList<String> galleryDataSet;

    //click event implementation
    //OnItemClickListener 인터페이스 선언
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
        private TextView textView;
        private Button call_button;
        private LinearLayout contact_recyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            call_button = itemView.findViewById(R.id.call_button);
            contact_recyclerview = itemView.findViewById(R.id.contact_recyclerview);
        }
        public TextView getTextView() {
            return textView;
        }
        public Button getCall_button(){
            return call_button;
        }
    }
    public CustomAdapter (ArrayList<String> nameSet, ArrayList<String> telSet, ArrayList<String> gallerySet) {
        nameDataSet = nameSet;
        telDataSet = telSet;
        galleryDataSet = gallerySet;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                int position = viewHolder.getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION) {
                    data = viewHolder.getTextView().getText().toString();
                }
                itemClickListener.onItemClicked(position, data);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        String name = nameDataSet.get(position);
        holder.textView.setText(name);
        String tel = telDataSet.get(position);
        String pic = galleryDataSet.get(position);

        holder.contact_recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                Intent contactActivity = new Intent(context, ContactsActivity.class);

                contactActivity.putExtra("name", name);
                contactActivity.putExtra("tel", tel);
                contactActivity.putExtra("pic", pic);

                ((MainActivity)context).startActivity(contactActivity);
            }
        });

        holder.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String tel_info = "tel:".concat(tel);
                intent.setData(Uri.parse(tel_info));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameDataSet.size();
    }
}