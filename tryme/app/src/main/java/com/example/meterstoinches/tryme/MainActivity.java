package com.example.meterstoinches.tryme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private View WindowView;
    private Button TrymeButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private SeekBar mseekBar;
    private TextView result;
    private TextView content;
    private ToggleButton toggleButton;

    private Button checkw;
    private TextView resultw;
    private CheckBox mom;
    private CheckBox dad;
    private CheckBox me;

    private AlertDialog.Builder alertDia;
    private Button alertDia_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_main);
        //button
        WindowView = findViewById(R.id.window);
        WindowView.setBackgroundColor(Color.RED);
        TrymeButton = (Button) findViewById(R.id.TrymeButton);
        TrymeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST","tap");
                WindowView.setBackgroundColor(Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
            }
        });

        // radio group
        radioGroup = (RadioGroup) findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);
                switch (radioButton.getId()){
                    case R.id.yes: {
                        if (radioButton.isChecked()) {
                                Log.d("rd","yes");
                        }
                    }
                    break;
                    case R.id.no: {
                        if (radioButton.isChecked()) {
                            Log.d("rd","no");
                        }
                    }
                    break;
                    case R.id.maybe: {
                        if (radioButton.isChecked()) {
                            Log.d("rd","maybe");
                        }
                    }
                }


            }
        });
        //seekBar
        mseekBar = (SeekBar) findViewById( R.id.seekBar);
        result = findViewById(R.id.result);
        content= findViewById(R.id.showyourlevel);
        result.setText("your level"+mseekBar.getProgress()+ "/"+ mseekBar.getMax());
        mseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                result.setTextColor(Color.DKGRAY);
                result.setText("your level"+mseekBar.getProgress()+ "/"+ mseekBar.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                result.setText("your level"+mseekBar.getProgress()+ "/"+ mseekBar.getMax());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    if(mseekBar.getProgress() >= 7){
                        result.setTextColor(Color.RED);
                    }
            }
        });
        //toggleButton
        toggleButton = (ToggleButton) findViewById(R.id.togButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TrymeButton.setVisibility(View.VISIBLE);
                }
                else{
                    TrymeButton.setVisibility(View.INVISIBLE);
                }

            }
        });
        //checkbox
        me = (CheckBox) findViewById(R.id.me);
        mom = (CheckBox) findViewById(R.id.mom);
        dad = (CheckBox) findViewById(R.id.dad);
        checkw = (Button) findViewById(R.id.checkwho);
        resultw=findViewById(R.id.resutlofwho);

        checkw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append(mom.getText().toString() + "mom status is"+ mom.isChecked()+"\n");
                resultw.setText(sb);
            }
        });

        //alert dialog
        alertDia_Button =( Button) findViewById(R.id.alert_dia);
        alertDia_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show actual dialog
                alertDia = new AlertDialog.Builder((MainActivity.this));
                //set things up. set title
                alertDia.setTitle(getResources().getString(R.string.alert_dialog_name));

                //set message
                alertDia.setMessage(getResources().getString(R.string.alert_dia));

                // set cancelable
                alertDia.setCancelable(false);

                //set positive Button
                alertDia.setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        });
                alertDia.setNegativeButton(getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                ///creat actual dialog
                AlertDialog dialog = alertDia.create();
                //show the dialog
                dialog.show();


            }
        });

    }
}
