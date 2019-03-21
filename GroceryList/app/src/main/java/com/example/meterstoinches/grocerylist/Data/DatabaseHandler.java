package com.example.meterstoinches.grocerylist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.meterstoinches.grocerylist.Model.Grocery;
import com.example.meterstoinches.grocerylist.Util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;
    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME+ "("+
                Constants.KEY_ID+ " INTEGER PRIMARY KEY, " + Constants.KEY_GROCERY_ITEM +" TEXT, "+
                Constants.KEY_QTY_NUMBER + " TEXT, " +
                Constants.KEY_DATE_NAME + " LONG);";

         db.execSQL(CREATE_GROCERY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);
    }
    /**
     * create; read; update; delete
     */
    //add Grocery
    public void addGrocery(Grocery grocery){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constants.KEY_GROCERY_ITEM,grocery.getName());
            values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
            values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());
            // Insert row
            db.insert(Constants.TABLE_NAME,null,values);
            Log.d("Saved!!","Saved to DB");
    }

    //get a Grocery
    public Grocery getGrocery(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME,new String[]{Constants.KEY_ID, Constants.KEY_GROCERY_ITEM,
                Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_NAME},Constants.KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

            Grocery grocery = new Grocery();
            grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
            grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
            //convert timestamp to something readable
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                    .getTime());
            grocery.setDataItemAdded(formatedDate);


        return grocery;
    }
    // get all Grocery
    public List<Grocery> getAllGrocrey(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Grocery> groceryList = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_NAME
        }, null,null,null,null, Constants.KEY_DATE_NAME+" DESC");
        //make sure give an space before DESC
        if (cursor.moveToFirst()){
            do {
                Grocery grocery = new Grocery();
                grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
                grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
                //convert timestamp to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                        .getTime());
                grocery.setDataItemAdded(formatedDate);
                // Add to the groceryList
                groceryList.add(grocery);
            }while(cursor.moveToNext());
        }
        return groceryList;
    }

    //update Grocery
    public int updateGrocery(Grocery grocery){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM,grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());
        //update now
        return db.update(Constants.TABLE_NAME,values,Constants.KEY_ID+"=?",new String[]{
                String.valueOf(grocery.getId())
        });
    }

    //delete Grocery
    public  void deleteGrocery(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID+" = ?",
                new String[] { String.valueOf(id)});
        db.close();
    }

    //get count
    public  int Getcount(){
        //* means all or everything
        String countQuery = "SELECT * FROM "+ Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        return cursor.getCount();
    }
}
