package com.example.cakeslk.cakes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeslk.database.Cakes_lk_Database;

public class DatabaseHelper extends Cakes_lk_Database {

    public DatabaseHelper(Context context)
    {
        super(context);
    }

    public boolean insertCake(Cake cake){
        ContentValues contentValues = new ContentValues();
        contentValues.put("C_Name",cake.getCakeName());
        contentValues.put("C_Flavour",cake.getFlavour());
        contentValues.put("C_Price",cake.getPrice());
        contentValues.put("C_Quantity",cake.getQuantity());
        contentValues.put("C_Description",cake.getDescription());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("Cake",null,contentValues);

        return result != -1;
    }

    public boolean updateCake(Cake cake){
        ContentValues contentValues = new ContentValues();
        contentValues.put("C_Name",cake.getCakeName());
        contentValues.put("C_Flavour",cake.getFlavour());
        contentValues.put("C_Price",cake.getPrice());
        contentValues.put("C_Quantity",cake.getQuantity());
        contentValues.put("C_Description",cake.getDescription());
        SQLiteDatabase db= getReadableDatabase();

        int result=db.update("Cake",contentValues,"C_Id ="+cake.getCakeId(),null);

        return result != -1;
    }

    public Cursor displayCakes(){
        SQLiteDatabase db=getWritableDatabase();

        String query="SELECT * FROM Cake";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public boolean deleteCake(int id){
        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("Cake","C_Id ="+id,null);
        return result != -1;
    }
}