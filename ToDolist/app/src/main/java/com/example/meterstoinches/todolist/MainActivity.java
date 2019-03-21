package com.example.meterstoinches.todolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public Button save;
    public EditText input;
    public TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText()!=null){
                    String message = input.getText().toString();
                    Textsave(message);
                }
                else{

                }

            }

        });
        try {
            if(readFile()!=null){
                show.setText(readFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initial(){
        save = (Button) findViewById(R.id.button);
        input= (EditText)findViewById(R.id.editText);
        show = (TextView)findViewById(R.id.Record);
    }
    public void Textsave(String message){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todolist.txt",
                    Context.MODE_PRIVATE));
            outputStreamWriter.write(message);
            outputStreamWriter.close();// rememeber always close
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile() throws IOException {
        String ans = "";
        InputStream inputStream = openFileInput("todolist.txt");
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = "";
            StringBuilder sb = new StringBuilder();
            while( (temp = bufferedReader.readLine())!= null){
                sb.append(temp);

            }
            inputStream.close();
            ans = sb.toString();
        }
        return ans;
    }

}
