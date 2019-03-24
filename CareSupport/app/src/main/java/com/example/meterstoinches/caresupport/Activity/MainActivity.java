package com.example.meterstoinches.caresupport.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meterstoinches.caresupport.R;
import com.example.meterstoinches.caresupport.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        sinin();
        Register();
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

    private void Register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emails = email.getText().toString();
                String pwd = password.getText().toString();
                if(!emails.isEmpty()&&!pwd.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(emails, pwd).addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"Failed create" , Toast.LENGTH_LONG)
                                                .show();
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Account Created " , Toast.LENGTH_LONG)
                                                .show();
                                        //now can write things
                                        User customer = new User("Chen","Mao",emails,99);
                                        databaseReference.setValue(customer);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void sinin() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emails = email.getText().toString();
                String pwd = password.getText().toString();
                if(!emails.isEmpty()&&!pwd.isEmpty()){
                    mAuth.signInWithEmailAndPassword(emails,pwd).addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"Failed sign in" , Toast.LENGTH_LONG)
                                                .show();
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"signed in" , Toast.LENGTH_LONG)
                                                .show();
                                        //now can write things
                                        User customer = new User("Chen","Mao",emails,99);
                                        databaseReference.setValue(customer);
                                        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void setUI() {
        email = (EditText) findViewById(R.id.Email_a1);
        password=(EditText)findViewById(R.id.Password_a1);
        logo=(ImageView)findViewById(R.id.Logo_a1);
        signin=(Button)findViewById(R.id.Signin_a1);
        register=(Button)findViewById(R.id.Register_a1);
    }
}
