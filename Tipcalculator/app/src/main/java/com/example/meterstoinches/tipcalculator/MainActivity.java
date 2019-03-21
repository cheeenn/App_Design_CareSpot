package com.example.meterstoinches.tipcalculator;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button cal_button;
    private SeekBar seekBar;
    private TextView result;
    private TextView show_percentage;
    private EditText input;
    private int percent;
    private float enterbill;
    private TextView total;
    public static final String TAG = "MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cal_button = (Button) findViewById(R.id.calulate_button);
        show_percentage = findViewById(R.id.show_percentage);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        input = (EditText) findViewById(R.id.input_text);
        result = (TextView) findViewById(R.id.result);
        total = findViewById(R.id.total);
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        //Log.v("hh",getApplicationContext().getString(R.string.app_name));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show_percentage.setText(seekBar.getProgress()+"%"+String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                percent = seekBar.getProgress();
            }
        });
        cal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

public void calculate(){
        float result2 = 0.0f;
        if(!input.getText().toString().equals("")){
            enterbill = Float.parseFloat(input.getText().toString());
            result2 = enterbill*percent/100;
            result.setText("your tip will be "+ String.valueOf(result2));
            total.setText("total bill is "+ String.valueOf(result2+enterbill));
            Log.d(TAG,"Hellow from calculate");
        }
        else{
            Toast.makeText(MainActivity.this,"please enter a bill amount.",Toast.LENGTH_LONG).show();
        }
}

            //TODO HERE IS TEST
}
