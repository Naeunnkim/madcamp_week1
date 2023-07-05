package com.example.week1_android_studio;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ContactsAddPerson extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsadd);

        Toolbar mToolbar = findViewById(R.id.contact_add_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_add_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        else if (item.getItemId() == R.id.contact_add_person){
            EditText nameEditText = (EditText) findViewById(R.id.name_edittext);
            EditText numberEditText = (EditText) findViewById(R.id.number_edittext);
            EditText emailEditText = (EditText) findViewById(R.id.email_edittext);

//            File file = new File(getApplication().getFilesDir(), "contactsInfo.json");
//            if (file.exists()){
//                Toast.makeText(getApplicationContext(), "이이이이이이", Toast.LENGTH_LONG).show();
//                try {
//                    FileOutputStream os = openFileOutput("contactsInfo.json", Context.MODE_APPEND);
//                    os.close();
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            try{
                FileOutputStream os = openFileOutput("contactsInfo.json", Context.MODE_APPEND);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name", nameEditText.getText().toString());
                jsonObject.put("tel", numberEditText.getText().toString());
                jsonObject.put("email", emailEditText.getText().toString());
                jsonObject.put("pic", "");

                String str = jsonObject.toString();

                writer.write(str);
                Toast.makeText(getApplicationContext(), "" + str.toString(), Toast.LENGTH_LONG).show();
                writer.close();
                os.close();
            }
            catch (IOException e) {e.printStackTrace();} catch (JSONException e) {
                throw new RuntimeException(e);
            }

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
