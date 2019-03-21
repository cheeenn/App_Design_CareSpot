package com.example.helloandroid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

//    private Button hiButton ;
    private Button click_Button;
    private TextView hi_text;

    private EditText meditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //r to layout view
        setContentView(R.layout.activity_main);
        click_Button = (Button) findViewById(R.id.mbutton);
        hi_text =(TextView) findViewById(R.id.myHello);

        meditText=(EditText) findViewById(R.id.editText);

        //change the txt
        click_Button.setText(R.string.click_now);

        click_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredText;
                enteredText = meditText.getText().toString();
                
                hi_text.setVisibility(View.VISIBLE);
                hi_text.setText(enteredText);
            }
        });
//        hiButton =(Button) findViewById(R.id.button2);
//        //use this to adjust string
//        hiButton.setText(R.string.button_name);
//        hiButton.setTextColor(Color.BLUE);
//        hiButton.setTextSize(32);
    }
//    public  void hahaha(View view){
//        hi_text.setVisibility(View.VISIBLE);
//        hi_text.setText(R.string.hahaha);
//
//
//    }
}
