package com.example.week1_android_studio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsinfo);

        TextView contact_name = (TextView) findViewById(R.id.contact_name);
        TextView contact_number = (TextView) findViewById(R.id.contact_number);
        Button call_btn = (Button) findViewById(R.id.phone_call);
        Button message_btn = (Button) findViewById(R.id.message);
        ToggleButton favorites_btn = (ToggleButton) findViewById(R.id.favorites);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String number = intent.getExtras().getString("tel");

        contact_name.setText(name);
        contact_number.setText(number);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String tel_info = "tel:".concat(number);
                intent.setData(Uri.parse(tel_info));
                startActivity(intent);
            }
        });

        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String tel_info = "smsto:".concat(number);
                intent.setData(Uri.parse(tel_info));
                startActivity(intent);
            }
        });

        favorites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorites_btn.isSelected()){
                    favorites_btn.setBackgroundResource(R.drawable.phone_unmark_favorites);
                    favorites_btn.setSelected(false);
                }
                else{
                    favorites_btn.setBackgroundResource(R.drawable.phone_mark_favorites);
                    favorites_btn.setSelected(true);
                }
            }
        });

    }
}
