package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsinfo);

        TextView contact_name = (TextView) findViewById(R.id.contact_name);
        TextView contact_number = (TextView) findViewById(R.id.contact_number);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String number = intent.getExtras().getString("tel");

        contact_name.setText(name);
        contact_number.setText(number);
    }
}
