package com.example.meterstoinches.mysql_contact_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DataBaseHandler;
import model.contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler db = new DataBaseHandler(this);

        // insert contacts
        Log.d("Insert: " , "Inerting...");
        db.addcontact(new contact("paul","23434234"));
        db.addcontact(new contact("Mark","324324323"));
        db.addcontact(new contact("Mill","32534555"));
        db.addcontact(new contact("Bella","23432444"));

        int len = db.getconstantcount();
        Log.d("we have：", String.valueOf(len));

        //get 1 contact
        contact c2 = db.getcontact(1);
        String log1 = "id : "+ c2.getId()+" name : " + c2.getName() + " phone number: " + c2.getPhonenumber();
        Log.d("Content: ",log1);

        //update one contact
        c2.setPhonenumber("23423432");
        c2.setName("fdsdsf");
        int a = db.updatecontact(c2);
        //delete contact
       db.Deletecontact(c2);


        //read all of them back
        Log.d("Read: ","reading..." );
        List<contact> list = db.getAllContacts();
        //get all contact
        for(contact c : list){
            String log = "id : "+ c.getId()+" name : " + c.getName() + " phone number: " + c.getPhonenumber();
            Log.d("Content: ",log);
        }

    }
}
