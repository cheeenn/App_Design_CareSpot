package com.example.meterstoinches.recycle_view;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private TextView Name;
    private TextView Description;
    private TextView Rating;
    private ImageView image;
    private Button play;
    private MediaPlayer mp;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        Bundle extra = getIntent().getExtras();

        Name = (TextView) findViewById(R.id.Name);
        Description = (TextView) findViewById(R.id.Description);
        Rating =(TextView) findViewById(R.id.Rating);
        image = (ImageView) findViewById(R.id.D_imageView);
        
        play = (Button) findViewById(R.id.button);

        mp = new MediaPlayer();
        mp= MediaPlayer.create(getApplicationContext(),R.raw.muisc_test);

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(mp.getDuration());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        if(extra!=null){
            Name.setText(extra.getString("Name"));
            Description.setText(extra.getString("Description"));
            Rating.setText(extra.getString("Rating"));
            image.setImageDrawable(image.getResources().getDrawable(R.drawable.nrbaby));
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();

                String duration2 = String.valueOf(duration/1000);

                Toast.makeText(getApplicationContext(),"duration = "+duration2,Toast.LENGTH_LONG ).show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                        pause();
                }
                else{
                        playy();
                }
            }
        });

    }
    public  void  playy(){
        if(mp!=null){
            mp.start();
            play.setText("Playing");
        }
    }
    public  void  pause(){
            if(mp!=null){
                mp.pause();
                play.setText("pause");
            }
    }

    @Override
    protected void onDestroy() {
        if(mp!= null&& mp.isPlaying()){
            mp.stop();
            mp.release();
            mp=null;
        }
        super.onDestroy();
    }
}
