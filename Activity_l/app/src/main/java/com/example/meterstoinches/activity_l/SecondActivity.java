package com.example.meterstoinches.activity_l;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView showfromfirst;
    private Button gobakButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showfromfirst = findViewById(R.id.showfromfirst);
        Bundle extra = getIntent().getExtras();
        gobakButton = (Button) findViewById(R.id.backButton);

        if(extra!=null)showfromfirst.setText(String.valueOf(extra.getInt("value"))+ extra.getString("message"));

        gobakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent = getIntent();
                //Intent backintent = new Intent(SecondActivity.this,MainActivity.class);
                backintent.putExtra("back","back from second scnene");
                setResult(RESULT_OK,backintent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
