package com.example.meterstoinches.grocerylist.Activities;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.meterstoinches.grocerylist.Data.DatabaseHandler;
import com.example.meterstoinches.grocerylist.Model.Grocery;
import com.example.meterstoinches.grocerylist.R;
import com.example.meterstoinches.grocerylist.UI.RecyckerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivities extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyckerViewAdapter recyckerViewAdapter;
    private List<Grocery> groceryList;
    private List<Grocery> listItems;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        db = new DatabaseHandler(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryList = new ArrayList<>();
        listItems = new ArrayList<>();

        //get items from database
        groceryList = db.getAllGrocrey();
        for (Grocery c : groceryList){
            Grocery grocery = new Grocery();
            grocery.setName(c.getName());
            grocery.setQuantity("Qty: "+ c.getQuantity());
            grocery.setId(c.getId());
            grocery.setDataItemAdded("Add on: "+c.getDataItemAdded());
            listItems.add(grocery);
        }
        recyckerViewAdapter = new RecyckerViewAdapter(this,listItems);
        recyclerView.setAdapter(recyckerViewAdapter);
        recyckerViewAdapter.notifyDataSetChanged();
    }

}
