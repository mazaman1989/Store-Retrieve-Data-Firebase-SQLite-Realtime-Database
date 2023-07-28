package com.zamansoftwaresolutions.datamangeusingfirebasesqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase.DataClass;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase.MyAdapter;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase.UploadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);


        //==========================
        recyclerView = findViewById(R.id.recyclerView);

        //======================
         searchView = findViewById(R.id.search);
         searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String newText) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 searchList(newText);
                 return true;
             }
         });
        //===============================

        getDataFromFirebase();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getDataFromFirebase() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("User Registration");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }

    public void searchList(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();

        for(DataClass dataClass: dataList){
            if(dataClass.getDataName().toLowerCase().contains(text.toLowerCase())
               || dataClass.getDataEmail().toLowerCase().contains(text.toLowerCase())
               || dataClass.getDataPhone().contains(text)){
                searchList.add(dataClass);
            }

        }
        adapter.searchDataList(searchList);
    }

}