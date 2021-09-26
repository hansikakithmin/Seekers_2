package com.example.paymentdetails.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="PaymentInfo.db";

    public DataBaseHelper(Context context) { super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE"+UM.Payments.TABLE_NAME+"("+
                        UM.Payments._ID+"INTEGER PRIMARY KEY,"+
                        UM.Payments.COLUMN_NAME_NAME+"TEXT,"+
                        UM.Payments.COLUMN_NAME_COUNTRY+"TEXT,"+
                        UM.Payments.COLUMN_NAME_NUMBER_OF_PARTICIPANTS+"TEXT,"+
                        UM.Payments.COLUMN_NAME_CONTACT_NUMBER+"TEXT,"+
                        UM.Payments.COLUMN_NAME_EMAIL+"TEXT,"+
                        UM.Payments.COLUMN_NAME_PRICE_AND_PAYMENTTYPE+"TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public long addInfo(String name,String country,String number_of_participants,String contact_number,String email,String price_and_paymenttype){
        SQLiteDatabase db =  getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UM.Payments.COLUMN_NAME_NAME,name);
        values.put(UM.Payments.COLUMN_NAME_COUNTRY, country);
        values.put(UM.Payments.COLUMN_NAME_NUMBER_OF_PARTICIPANTS, number_of_participants);
        values.put(UM.Payments.COLUMN_NAME_CONTACT_NUMBER, contact_number);
        values.put(UM.Payments.COLUMN_NAME_EMAIL, email);
        values.put(UM.Payments.COLUMN_NAME_PRICE_AND_PAYMENTTYPE, price_and_paymenttype);

         return db.insert(UM.Payments.TABLE_NAME,null,values);
    }

    public List readAll(){
        SQLiteDatabase database = getReadableDatabase();

        String [] projection = {
                UM.Payments._ID,
                UM.Payments.COLUMN_NAME_NAME,
                UM.Payments.COLUMN_NAME_COUNTRY,
                UM.Payments.COLUMN_NAME_NUMBER_OF_PARTICIPANTS,
                UM.Payments.COLUMN_NAME_CONTACT_NUMBER,
                UM.Payments.COLUMN_NAME_EMAIL,
                UM.Payments.COLUMN_NAME_PRICE_AND_PAYMENTTYPE
        };

        String sortOrder = UM.Payments._ID+"DESC";

        Cursor cursor = database.query(
                UM.Payments.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List info = new ArrayList<>();

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_NAME));
            String country = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_COUNTRY));
            String number_of_participants = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_NUMBER_OF_PARTICIPANTS));
            String contact_number = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_CONTACT_NUMBER));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_EMAIL));
            String price_and_paymenttype = cursor.getString(cursor.getColumnIndexOrThrow(UM.Payments.COLUMN_NAME_PRICE_AND_PAYMENTTYPE));

            info.add(name+":"+country+":"+number_of_participants+":"+contact_number+":"+email+":"+price_and_paymenttype);
        }

        cursor.close();
        return info;
    }

    public void deleteInfo(String name){
        SQLiteDatabase database = getReadableDatabase();

        String selection = UM.Payments.COLUMN_NAME_NAME + " LIKE ?";
        String[] stringArgs = {name};

        database.delete(UM.Payments.TABLE_NAME,selection,stringArgs);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
