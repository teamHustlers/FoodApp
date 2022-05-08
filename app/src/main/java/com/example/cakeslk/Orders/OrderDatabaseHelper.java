package com.example.cakeslk.Orders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeslk.database.Cakes_lk_Database;

public class OrderDatabaseHelper extends Cakes_lk_Database {
    public OrderDatabaseHelper(Context context) {
        super(context);
    }

    public boolean insertOrder(Order order){
        ContentValues contentValues = new ContentValues();
        contentValues.put("S_Name", order.getSupplierName());
        contentValues.put("P_Type", order.getProductType());
        contentValues.put("Quantity", order.getQuantity());
        contentValues.put("Amount", order.getAmount());


        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("Orders",null,contentValues);

        return result != -1;
    }

    public boolean updateOrder(Order order){
        ContentValues contentValues = new ContentValues();
        contentValues.put("S_Name", order.getSupplierName());
        contentValues.put("P_Type", order.getProductType());
        contentValues.put("Quantity", order.getQuantity());
        contentValues.put("Amount", order.getAmount());

        SQLiteDatabase db= getReadableDatabase();

        int result=db.update("Orders",contentValues,"P_Id ="+order.getOrderId(),null);

        return result != -1;
    }

    public Cursor displayOrder(){
        SQLiteDatabase db=getWritableDatabase();

        String query="SELECT * FROM Orders";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public boolean deleteOrder(int id){

        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("Orders","P_Id ="+id,null);
        return result != -1;
    }
}
