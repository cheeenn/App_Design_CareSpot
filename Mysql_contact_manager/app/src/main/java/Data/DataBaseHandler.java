package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Utils.Util;
import model.contact;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL   structure query language
        String CREATE_CONTACT_TABLE = "CREATE TABLE "+Util.TABLE_NAME+" ("
                +Util.KEY_ID+" INTEGER PRIMARY KEY, "+Util.KEY_NAME+" TEXT, "
                +Util.KEY_PHONE_NUMBER+" TEXT" + ")";
//            String create_table = "CREATE TABLE " + Util.TABLE_NAME + "("+
//                    Util.KEY_ID +" INTEGER PRIMARY KEY," + Util.KEY_NAME+" TEXT,"+ Util.KEY_PHONE_NUMBER +
//                    " TEXT" +")";
            db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //dropping is deleting the table
            db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME );
            onCreate(db);
    }
    //add
    public void addcontact(contact c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME , c.getName());
        value.put(Util.KEY_PHONE_NUMBER,c.getPhonenumber());
        db.insert(Util.TABLE_NAME,null,value);
        db.close();
    }
    //get
    public  contact getcontact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME,Util.KEY_PHONE_NUMBER },
                Util.KEY_ID + "=?" , new String[] {String.valueOf(id)},
                null,null,null,null);

        if(cursor!=null) cursor.moveToFirst();
        contact c = new contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2));
        return c;
    }
    //get all

    public List<contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<contact> contactList = new ArrayList<>();
        //select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        //loop through our contact
        if(cursor.moveToFirst()){
            do{
                contact c = new contact();
                c.setId(Integer.parseInt(cursor.getString(0)));
                c.setName(cursor.getString(1));
                c.setPhonenumber(cursor.getString(2));

                //add contact to our contact list
                contactList.add(c);
            }while(cursor.moveToNext());
        }
        Log.d("check " , String.valueOf(contactList.size()));
        return contactList;
    }

    public  int updatecontact(contact c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, c.getName());
        values.put(Util.KEY_PHONE_NUMBER,c.getPhonenumber());
        //update row

        return db.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?", new
        String[]{String.valueOf(c.getId())});
    }
    public void Deletecontact(contact c){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",new String[]{String.valueOf(c.getId())});
        db.close();

    }
    //get constant count
    public int getconstantcount(){
        String countquery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countquery,null);
        //cursor.close();

        return cursor.getCount();
    }
}
