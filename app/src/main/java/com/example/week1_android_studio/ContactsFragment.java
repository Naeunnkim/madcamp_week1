package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
<<<<<<< HEAD:app/src/main/java/com/example/week1_android_studio/ContactsFragment.java
 * Use the  factory method to
=======
 * Use the  factory method to
>>>>>>> 8e843dbbf2146997a6f248f314a12cad6d6a78de:app/src/main/java/com/example/week1_android_studio/HomeFragment.java
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.contacts_toolbar);

//        Toast.makeText(getActivity(), "왔다", Toast.LENGTH_SHORT).show();

        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.tab1){
                Intent intent = new Intent(getContext(), ContactsAddPerson.class);
                startActivity(intent);
                return true;
            }
            return true;
        });

        try {
            initUI(rootView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.person_add, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tab1) {
            View view = item.getActionView();
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ContactsAddPerson.class);
                    startActivity(intent);
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI(ViewGroup rootView) throws IOException, JSONException {
        ArrayList<String> nameDataSet = new ArrayList<>();
        ArrayList<String> telDataSet = new ArrayList<>();
        ArrayList<String> emailDataSet = new ArrayList<>();
        ArrayList<String> galleryDataSet = new ArrayList<>();

        FileInputStream is = getContext().openFileInput("contactsInfo.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line + "\n");
            line = reader.readLine();
        }

        String jsonData = buffer.toString();

        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String name = jo.getString("Name");
            String phone = jo.getString("tel");
            String email_address = jo.getString("email");
            String image_path = jo.getString("pic");
            nameDataSet.add(name);
            telDataSet.add(phone);
            emailDataSet.add(email_address);
            galleryDataSet.add(image_path);
        }
        Toast.makeText(getActivity(), "" + nameDataSet.size(), Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        CustomAdapter customAdapter = new CustomAdapter(nameDataSet, telDataSet, emailDataSet, galleryDataSet);

        //click event implementation
        customAdapter.setOnItemclickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, String data) {
                Intent intent = new Intent(getContext(), ContactsActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(customAdapter);
    }
}