package com.example.cakeslk.Supplier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeslk.database.Cakes_lk_Database;

public class SupplierDatabaseHelper extends Cakes_lk_Database {
    public SupplierDatabaseHelper(Context context) {
        super(context);
    }

    public boolean insertSupplier(Supplier supplier){
        ContentValues contentValues = new ContentValues();
        contentValues.put("S_Name",supplier.getSuppliername());
        contentValues.put("Acc_Num",supplier.getSupplierAccountNumber());
        contentValues.put("P_Num",supplier.getPhoneNumber());
        contentValues.put("S_Add",supplier.getAddress());
        contentValues.put("S_Add",supplier.getAddress());
        contentValues.put("S_Deli",supplier.getDelivery());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("Supplier",null,contentValues);

        return result != -1;

    }
    public boolean updateSupplier(Supplier supplier){
        ContentValues contentValues = new ContentValues();
        contentValues.put("S_Name",supplier.getSuppliername());
        contentValues.put("Acc_Num",supplier.getSupplierAccountNumber());
        contentValues.put("P_Num",supplier.getPhoneNumber());
        contentValues.put("S_Add",supplier.getAddress());
        contentValues.put("S_Deli",supplier.getDelivery());

        SQLiteDatabase db= getReadableDatabase();

        int result=db.update("Supplier",contentValues,"S_Id ="+supplier.getSupplierID(),null);
        return result != -1;
    }

    public Cursor displaySupplier(){
        SQLiteDatabase db=getWritableDatabase();

        String query="SELECT * FROM Supplier";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public boolean deleteSupplier(int id){

        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("Supplier","S_Id ="+id,null);
        return result != -1;
    }
}
