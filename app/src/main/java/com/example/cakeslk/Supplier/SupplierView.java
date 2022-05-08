package com.example.cakeslk.Supplier;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cakeslk.MainActivity;
import com.example.cakeslk.R;

import java.util.ArrayList;

public class SupplierView extends AppCompatActivity{

    SupplierDatabaseHelper supplierDatabaseHelper;
    ArrayList<Supplier> supplierList;
    Cursor data;
    EditText editText;
    ListView listView;
    Supplier supplier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_view);
        this.setTitle("Supplier");

        supplierDatabaseHelper = new SupplierDatabaseHelper(this);

        supplierList = new ArrayList<>();
        listView = findViewById(R.id.supplierListView);
        data = supplierDatabaseHelper.displaySupplier();
        int numRows = data.getCount();
        editText = findViewById(R.id.editText);

        if (numRows == 0)
            Toast.makeText(SupplierView.this, "No Supplier to Display", Toast.LENGTH_LONG).show();
        else{
            while(data.moveToNext()){
            supplier = new Supplier(data.getString(1), data.getInt(2), data.getInt(3), data.getString(4), data.getDouble(5));
            supplier.setSupplierID(data.getInt(0));
            supplierList.add(supplier);
        }
            ArrayAdapter custom_object = new SupplierViewAdapter(this,supplierList);
            listView.setAdapter(custom_object);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Supplier supplier = (Supplier) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(SupplierView.this, SupplierUpdate.class);
                intent.putExtra("Supplier",(Parcelable) supplier);
                startActivity(intent);



            }
        });
    }

    private void loadOrders(){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int textLength=charSequence.length();
                ArrayList<Supplier> tempArrayList = new ArrayList<>();
                for(Supplier p:supplierList){
                    if(textLength <= p.getSuppliername().length()){
                        if(p.getSuppliername().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            tempArrayList.add(p);
                        }
                    }
                }
                if(tempArrayList.size()==0){
                    Toast.makeText(SupplierView.this, "No Match Found!!!!", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayAdapter p_arrayadapter = new SupplierViewAdapter(getApplicationContext(), tempArrayList);
                    final ListView p_listView = findViewById(R.id.supplierListView);
                    p_listView.setAdapter(p_arrayadapter);


                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        loadOrders();

    }
    public void setOnClick(View view){

        Intent intent = new Intent(this, SupplierAdd.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SupplierView.this, MainActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.right_enter, R.anim.right_out);
    }
}

