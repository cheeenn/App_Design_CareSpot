package com.example.meterstoinches.nursinghouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView Dog;
    public ImageView Cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dog = (ImageView) findViewById(R.id.DOGV);
        Cat = (ImageView) findViewById(R.id.CATV);
        Dog.setOnClickListener(this);
        Cat.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.DOGV:
                Toast.makeText(MainActivity.this,"Dog",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, sec_act.class);
                intent.putExtra("name", "Billy");
                intent.putExtra("comment","This is a nice dog");
                startActivity(intent);

                break;
            case R.id.CATV:
                Toast.makeText(MainActivity.this,"Cat",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, sec_act.class);
                intent2.putExtra("name", "Juhua");
                intent2.putExtra("comment","This is a nice Cat");
                startActivity(intent2);
                break;
        }
    }
}
