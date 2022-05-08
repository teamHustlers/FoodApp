package com.example.cakeslk.Supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.cakeslk.R;

public class SupplierUpdate extends  AppCompatActivity {

    EditText suName, anum, phnum, sadd, sdel;
    ProgressBar progressBar;
    Button btnUpdate,btnDelete;
    SupplierDatabaseHelper supplierDatabaseHelper;
    Supplier supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_update);

        supplierDatabaseHelper = new SupplierDatabaseHelper(this);

        Intent intent = getIntent();
        supplier = intent.getParcelableExtra("Supplier");

        suName = findViewById(R.id.suName);
        anum = findViewById(R.id.anum);
        phnum = findViewById(R.id.phnum);
        sadd = findViewById(R.id.sadd);
        sdel = findViewById(R.id.sdel);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        suName.setText(supplier.getSuppliername());
        anum.setText(Integer.toString(supplier.getSupplierAccountNumber()));
        phnum.setText(Integer.toString(supplier.getPhoneNumber()));
        sadd.setText(supplier.getAddress());
        sdel.setText(Double.toString(supplier.getDelivery()));

        suName.setEnabled(false);
        anum.setEnabled(false);
        phnum.setEnabled(false);
        sadd.setEnabled(false);
        sdel.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                    btnUpdate.setText("UPDATE");
                    suName.setEnabled(true);
                    anum.setEnabled(true);
                    phnum.setEnabled(true);
                    sadd.setEnabled(true);
                    sdel.setEnabled(true);
                } else {
                    try {
                        if (!suName.getText().toString().isEmpty()) {
                            if (!anum.getText().toString().isEmpty()) {
                                if (!phnum.getText().toString().isEmpty()) {
                                    if (!sadd.getText().toString().isEmpty()) {
                                        if (!sdel.getText().toString().isEmpty()) {

                                        btnUpdate.setVisibility(View.INVISIBLE);
                                        btnDelete.setVisibility(View.INVISIBLE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        String s_Name = suName.getText().toString();
                                        int s_Num = Integer.parseInt(anum.getText().toString());
                                        int p_Num = Integer.parseInt(phnum.getText().toString());
                                        String s_Add = sadd.getText().toString();
                                        double s_Del = Double.parseDouble(sdel.getText().toString());

                                        Supplier supplier1 = new Supplier(s_Name, s_Num, p_Num, s_Add,s_Del);
                                        supplier1.setSupplierID(supplier.getSupplierID());
                                        updateData(supplier1);

                                        } else
                                            Toast.makeText(SupplierUpdate.this, "Delivery Charge cannot be empty", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(SupplierUpdate.this, "Address cannot be empty", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(SupplierUpdate.this, "Phone Number cannot be empty", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(SupplierUpdate.this, "Account Number cannot be empty", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(SupplierUpdate.this, "Name cannot be empty", Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        Toast.makeText(SupplierUpdate.this, "Invalid Supplier Details!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                supplierDatabaseHelper.deleteSupplier(supplier.getSupplierID());

                suName.setText("");
                anum.setText("");
                phnum.setText("");
                sadd.setText("");
                sdel.setText("");


                Toast.makeText(SupplierUpdate.this, "Supplier Deleted Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SupplierUpdate.this, SupplierView.class);
                startActivity(intent);

            }
        });



    }

    private void updateData(Supplier supplier) {
        if(supplierDatabaseHelper.updateSupplier(supplier)){
            Toast.makeText(SupplierUpdate.this,"Successfully Updated",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SupplierUpdate.this, SupplierView.class);
            startActivity(intent);
        }
        else
            Toast.makeText(SupplierUpdate.this, "Error Occurred..........", Toast.LENGTH_LONG).show();



    }


}

