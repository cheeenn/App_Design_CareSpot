package com.example.meterstoinches.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public AnimationDrawable animationDrawable;
    public ImageView imageView;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //start animation
        animationDrawable.start();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Animation startanimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeanim);
                //imageView.startAnimation(startanimation);
                //stop animation
               animationDrawable.stop();
            }
        },5000);//5sec
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        //set background resource file (xml)
        imageView.setBackgroundResource(R.drawable.animation);
        //get animation from imageview
        animationDrawable = (AnimationDrawable) imageView.getBackground();
    }
}
