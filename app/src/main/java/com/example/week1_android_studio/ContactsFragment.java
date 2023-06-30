package com.example.week1_android_studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.person_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        initUI(rootView);

        return rootView;
    }
    private void initUI(ViewGroup rootView){
        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i = 0; i<20; i++) {
            testDataSet.add("TEST DATA" + i);
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        CustomAdapter customAdapter = new CustomAdapter(testDataSet);

        //click event implementation
        customAdapter.setOnItemclickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, String data) {
                Intent intent = new Intent(getContext(), HomeInfoActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(customAdapter);
    }
}