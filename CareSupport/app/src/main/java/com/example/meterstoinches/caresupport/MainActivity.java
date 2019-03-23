package com.example.meterstoinches.caresupport;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private ImageView logo;
    private Button signin;
    private Button register;
    ///Database part
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // mainly use log
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        database=FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Myupdate");
        //databaseReference.setValue("enter");
        mAuth=FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
    }

    private void setUI() {
        email = (EditText) findViewById(R.id.Email_a1);
        password=(EditText)findViewById(R.id.Password_a1);
        logo=(ImageView)findViewById(R.id.Logo_a1);
        signin=(Button)findViewById(R.id.Signin_a1);
        register=(Button)findViewById(R.id.Register_a1);
    }
}
