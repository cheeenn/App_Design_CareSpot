package com.example.meterstoinches.firebaseintro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
     private FirebaseDatabase database;
     private DatabaseReference databaseReference;
     private FirebaseAuth mAuth;
     private FirebaseAuth.AuthStateListener mAuthListener;

     private EditText email;
     private EditText password;
     private Button login;
     private Button signout;
     private Button Create;
     private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("message");
        //databaseReference.setValue("Hello Firebase");
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signout = (Button) findViewById(R.id.signoutButton);
        login =(Button) findViewById(R.id.LoginButton);
        Create=(Button) findViewById(R.id.CreateAButton);
        login.setOnClickListener(new View.OnClickListener() {
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
                                        Customer customer = new Customer("Chen","Mao",emails,99);
                                        databaseReference.setValue(customer);
                                    }
                                }
                            });
                }
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this,"you signed out",Toast.LENGTH_LONG).show();
            }
        });
        Create.setOnClickListener(new View.OnClickListener() {
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
                                        Customer customer = new Customer("Chen","Mao",emails,99);
                                        databaseReference.setValue(customer);
                                    }
                                }
                            });
                }
            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(Customer.class);
//                Toast.makeText(MainActivity.this,value,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    Log.d(TAG,"user signed in");
                }
                else{
                    //user is signed out
                    Log.d(TAG,"user signed out");
                }
            }
        };
    }
    @Override
    protected  void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected  void onStop(){
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
