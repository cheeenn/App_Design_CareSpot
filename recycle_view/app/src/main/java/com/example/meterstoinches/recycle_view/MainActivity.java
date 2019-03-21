package com.example.meterstoinches.recycle_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import model.Listitem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem>  listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //image1.getResources().getDrawable(R.drawable.NRbaby);
        //image2.getResources().getDrawable(R.drawable.NursingRoom);
        //image3.getResources().getDrawable(R.drawable.NRoomPurple);

        recyclerView = (RecyclerView) findViewById(R.id.R_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();

        Listitem item1 = new Listitem("item "+(1),
                "Fancy Romantic ", "Excellent");
        Listitem item2 = new Listitem("item "+(2),
                "Nice Moive ", "Good");
        Listitem item3 = new Listitem("item "+(3),
                "Comic ", "Nice");

//        for(int i = 0 ; i <10; i++){
//            Listitem item = new Listitem("item "+(i+1),
//            "Description ", "Good"
//            );
        //}

        listitems.add(item1);
        listitems.add(item2);
        listitems.add(item3);

        adapter = new MyAdapter(this,listitems);
        recyclerView.setAdapter(adapter);
    }
}
