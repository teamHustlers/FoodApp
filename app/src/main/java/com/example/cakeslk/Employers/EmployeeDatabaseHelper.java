package com.example.cakeslk.Employers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeslk.database.Cakes_lk_Database;

public class EmployeeDatabaseHelper extends Cakes_lk_Database {
    public EmployeeDatabaseHelper(Context context) {
        super(context);
    }

    public boolean insertEmployee(Employee employee){
        ContentValues contentValues = new ContentValues();
        contentValues.put("E_Name", employee.getEmployeeName());
        contentValues.put("E_Num", employee.getEmpNum());
        contentValues.put("E_Address", employee.getEmpAdd());
        contentValues.put("E_Salary",employee.getEmpSal());
        contentValues.put("E_Bonus",employee.getEmpBon());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("Employers",null,contentValues);

        return result != -1;
    }

    public boolean updateEmployee(Employee employee){
        ContentValues contentValues = new ContentValues();
        contentValues.put("E_Name", employee.getEmployeeName());
        contentValues.put("E_Num", employee.getEmpNum());
        contentValues.put("E_Address", employee.getEmpAdd());
        contentValues.put("E_Salary",employee.getEmpSal());
        contentValues.put("E_Bonus",employee.getEmpBon());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.update("Employers",contentValues,  "E_Id =" +employee.getEmpId(),null );

        return result != -1;

    }


    public Cursor displayEmployee() {

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM Employers";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean deleteEmployee(int id){
        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("Employers","E_Id ="+id,null);
        return result != -1;
    }
}
