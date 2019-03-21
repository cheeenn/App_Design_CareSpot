package com.example.meterstoinches.nursinghouse;

import android.media.Image;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class sec_act extends AppCompatActivity {

    public TextView name;
    public TextView comment;
    public ImageView image;
    public Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_act);
        name = (TextView) findViewById(R.id.name_sec);
        comment=(TextView) findViewById(R.id.comment);
        image = (ImageView) findViewById(R.id.imageView);

        Bundle extra = getIntent().getExtras();
        String name2 = extra.getString("name");
        name.setText(name2);
        comment.setText(extra.getString("comment"));
        if(name2.equals("Juhua"))image.setImageDrawable(image.getResources().getDrawable(R.drawable.cat));
        else image.setImageDrawable(image.getResources().getDrawable(R.drawable.dog));

    }


}
