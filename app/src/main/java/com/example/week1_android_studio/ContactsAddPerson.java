package com.example.week1_android_studio;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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

            AssetManager assetManager = getAssets();

            try{
                InputStream is = assetManager.open("jsons/contacts.json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                StringBuilder buffer = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line + "\n");
                    line = reader.readLine();
                }

                reader.close();
                is.close();

                String jsonData = buffer.toString();

                JSONArray jsonArray = new JSONArray(jsonData);

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name", nameEditText.getText().toString());
                jsonObject.put("tel", numberEditText.getText().toString());
                jsonObject.put("email", emailEditText.getText().toString());
                jsonObject.put("pic", "");

                jsonArray.put(jsonObject);

                String newJsonData = jsonArray.toString();
                String str = jsonObject.toString();

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                OutputStream os = openFileOutput("contacts.json", Context.MODE_PRIVATE);

                os.write(str.getBytes());
                os.close();
            }
            catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
