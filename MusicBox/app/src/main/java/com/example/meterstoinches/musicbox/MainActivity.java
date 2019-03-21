package com.example.meterstoinches.musicbox;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button prevbutton;
    public Button playbutton;
    public Button nextbutton;
    public SeekBar seekbb;
    public ImageView profie;
    public TextView lefttime;
    public TextView righttime;
    public Thread thread;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.muisc_test);
        seekbb.setMax(mediaPlayer.getDuration());
        seekbb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){

                    mediaPlayer.seekTo(progress);
                }
                SimpleDateFormat dataformat = new SimpleDateFormat("mm:ss");
                int position = mediaPlayer.getCurrentPosition();
                int duration =mediaPlayer.getDuration();
                lefttime.setText(dataformat.format(new Date(position)));
                righttime.setText(dataformat.format(new Date(duration-position)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void setUi(){
        prevbutton = (Button) findViewById(R.id.prev);
        playbutton = (Button) findViewById(R.id.play);
        nextbutton = (Button) findViewById(R.id.next);
        seekbb = (SeekBar) findViewById(R.id.seekBar);

        lefttime = (TextView) findViewById(R.id.leftvalue);
        righttime = (TextView)findViewById(R.id.right);

        profie = (ImageView) findViewById(R.id.oval);
        prevbutton.setOnClickListener(this);
        playbutton.setOnClickListener(this);
        nextbutton.setOnClickListener(this);

    }
    public  void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            playbutton.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

    public  void playing(){
        if(mediaPlayer!=null){
            mediaPlayer.start();
            updateThread();
            playbutton.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prev:
                mediaPlayer.seekTo(0);
                seekbb.setProgress(0);
                    break;
            case R.id.play:
               if(mediaPlayer.isPlaying()){
                   pause();
               }
                else {
                   playing();

               }
                break;
            case R.id.next:
                mediaPlayer.seekTo(seekbb.getMax());
                seekbb.setProgress(seekbb.getMax());
                break;
        }
    }

    public void updateThread(){
            thread = new Thread(){
                //use ctrl+O to override
                @Override
                public void run() {

                    try{

                        while(mediaPlayer!=null && mediaPlayer.isPlaying()) {

                            Thread.sleep(50);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                        int newposition = mediaPlayer.getCurrentPosition();
                                        int newmax = mediaPlayer.getDuration();
                                        seekbb.setMax(newmax);
                                        seekbb.setProgress(newposition);
                                        //update the text
                                        //SimpleDateFormat dateFormat2 = new SimpleDateFormat("mm:dd");
                                        lefttime.setText(String .valueOf(new java.text.SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getCurrentPosition()))));
                                        righttime.setText(String.valueOf(new java.text.SimpleDateFormat("mm:ss").format(
                                                new Date(mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition()))));
                                }
                            });
                        }

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;

        }
        thread.interrupt();
        thread=null;

        super.onDestroy();
    }
}
