package com.example.week1_android_studio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ContactsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsinfo);

        Toolbar mToolbar = findViewById(R.id.contact_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        TextView contact_name = (TextView) findViewById(R.id.contact_name);
        TextView contact_number = (TextView) findViewById(R.id.contact_number);
        TextView contact_email = (TextView) findViewById(R.id.contact_email);
        ImageView contact_profile = (ImageView) findViewById(R.id.contact_profile);
        contact_profile.setClipToOutline(true);

        Button call_btn = (Button) findViewById(R.id.phone_call);
        Button message_btn = (Button) findViewById(R.id.message);
        Button email_btn = (Button) findViewById(R.id.email);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String number = intent.getExtras().getString("tel");
        String email = intent.getExtras().getString("email");
        String pic = intent.getExtras().getString("pic");

        contact_name.setText(name);
        contact_number.setText(number);
        contact_email.setText(email);

        if (pic.length() != 0){
            int id = getResources().getIdentifier(pic, "drawable", getPackageName());
            contact_profile.setImageResource(id);
        }

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

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                String[] address = {email};
                intent.putExtra(Intent.EXTRA_EMAIL, address);
                startActivity(intent);
            }
        });

//        favorites_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (favorites_btn.isSelected()){
//                    favorites_btn.setBackgroundResource(R.drawable.phone_unmark_favorites);
//                    favorites_btn.setSelected(false);
//                }
//                else{
//                    favorites_btn.setBackgroundResource(R.drawable.phone_mark_favorites);
//                    favorites_btn.setSelected(true);
//                }
//            }
//        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
