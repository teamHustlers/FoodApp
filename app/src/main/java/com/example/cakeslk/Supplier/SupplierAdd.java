package com.example.cakeslk.Supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cakeslk.R;

public class SupplierAdd extends AppCompatActivity {

    EditText suName, anum, phnum, sadd, sdel;
    Button btn_add;
    SupplierDatabaseHelper supplierDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add);

        suName = findViewById(R.id.suName);
        anum = findViewById(R.id.anum);
        phnum = findViewById(R.id.phnum);
        sadd = findViewById(R.id.sadd);
        sdel = findViewById(R.id.sdel);
        btn_add = findViewById(R.id.btn_add1);
        supplierDatabaseHelper = new SupplierDatabaseHelper(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!suName.getText().toString().isEmpty()) {
                        if (!anum.getText().toString().isEmpty()) {
                            if (!phnum.getText().toString().isEmpty()) {
                                if (!sadd.getText().toString().isEmpty()) {
                                    if (!sdel.getText().toString().isEmpty()) {

                                        String s_Name = suName.getText().toString();
                                        int s_Num = Integer.parseInt(anum.getText().toString());
                                        int p_Num = Integer.parseInt(phnum.getText().toString());
                                        String s_Add = sadd.getText().toString();
                                        Double s_Del = Double.parseDouble(sdel.getText().toString());

                                        Supplier supplier = new Supplier(s_Name, s_Num, p_Num, s_Add, s_Del);
                                        addData(supplier);

                                    }else

                                        Toast.makeText(SupplierAdd.this, "Delivery Charge cannot be empty", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(SupplierAdd.this, "Address cannot be empty", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(SupplierAdd.this, "Phone Number cannot be empty", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(SupplierAdd.this, "Account Number cannot be empty", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(SupplierAdd.this, "Name cannot be empty", Toast.LENGTH_LONG).show();


                } catch (NumberFormatException ex) {
                    Toast.makeText(SupplierAdd.this, "Invalid Supplier Details!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addData(Supplier supplier){

        if (supplierDatabaseHelper.insertSupplier(supplier)) {
            Toast.makeText(SupplierAdd.this, "Successfully Inserted!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SupplierAdd.this, SupplierView.class);
            startActivity(intent);
        } else
            Toast.makeText(SupplierAdd.this, "Error while Inserting....", Toast.LENGTH_LONG).show();
    }
    }
