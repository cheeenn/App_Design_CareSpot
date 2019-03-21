package com.example.meterstoinches.store_message;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button save;
    public TextView memory;
    public EditText inputt;
    public SharedPreferences sharedPreferences;
    public static final String key_word = "my word";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.button);
        memory = (TextView) findViewById( R.id.text);
        inputt = (EditText) findViewById(R.id.editText);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(key_word, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message" , inputt.getText().toString());
                editor.putString("message2" , "omg");
                editor.commit();//such as save the input
            }
        });
        SharedPreferences read = getSharedPreferences(key_word,0);
        if(read.contains("message")){
            String message = read.getString("message2","not exit");
            memory.setText("messgae is = " + message);
        }

    }
}
