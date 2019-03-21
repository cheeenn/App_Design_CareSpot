package com.example.meterstoinches.location_directionary.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.meterstoinches.location_directionary.Data.MovieRecyclerViewAdapter;
import com.example.meterstoinches.location_directionary.Model.Location;
import com.example.meterstoinches.location_directionary.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button shownsecond ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shownsecond = (Button) findViewById(R.id.button);

        shownsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
