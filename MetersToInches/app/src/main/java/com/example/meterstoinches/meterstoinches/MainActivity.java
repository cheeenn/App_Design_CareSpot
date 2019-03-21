package com.example.meterstoinches.meterstoinches;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
        1m = 39.3701 in
     */
    private EditText input_meter;
    private Button convert_button;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_meter = (EditText) findViewById(R.id.input);
        convert_button = (Button) findViewById(R.id.conver_button);
        result = (TextView) findViewById(R.id.result);

        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_meter.getText().toString().equals("")){
                    result.setText(R.string.please_input);
                    result.setTextColor(Color.RED);
                }
                else{
                    double meter_c  = 39.37;
                    double result2 = 0.0;
                    double meter = Double.parseDouble(input_meter.getText().toString());

                    result2 = meter*meter_c;
                    // result.setText(Double.toString(result2)+" inches");
                    result.setText(String.format("%.3f",result2)+" inches");
                    result.setTextColor(Color.DKGRAY);
                }


            }
        });




    }
}
