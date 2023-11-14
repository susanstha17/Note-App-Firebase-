package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button Add;
    RecyclerView rv;
    ArrayList<DataModel> arraylist = new ArrayList<>();
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3,
                LinearLayoutManager.VERTICAL));
        dr = FirebaseDatabase.getInstance().getReference().child("StudentInfo");
        Query query = dr.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arraylist.clear();
            for(DataSnapshot ds:snapshot.getChildren()){
                DataModel dm = ds.getValue(DataModel.class);
                arraylist.add(dm);
                }
                Log.e("Datasize",""+arraylist.size());
            rv.setAdapter(new CustomAdapter(HomeActivity.this,arraylist));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Add = findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AddDataActivity.class);
                startActivity(i);
            }
        });
    }
}