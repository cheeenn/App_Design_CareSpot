package com.example.meterstoinches.activity_l;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showSecond;
    public final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSecond = (Button)findViewById(R.id.showButton);
        showSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("message", "hello from first screen");
                intent.putExtra("value",123);

                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
                //startActivity(new Intent(MainActivity.this,SecondActivity.class));

            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this,"on start",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this,"on resume",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
       // Toast.makeText(this,"on post resume",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this,"on stop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Toast.makeText(this,"on destory",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // Toast.makeText(this,"on pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        requestCode =2;
        if(requestCode==REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("message");
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }
        }
    }
}
