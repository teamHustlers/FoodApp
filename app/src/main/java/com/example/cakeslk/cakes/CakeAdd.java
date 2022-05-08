package com.example.cakeslk.cakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cakeslk.R;
public class CakeAdd extends AppCompatActivity {

    EditText cName,cPrice,cDescription,cQuantity;
    Spinner spinner;
    Button btn_add;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_add);

        cName=findViewById(R.id.pName);
        spinner=findViewById(R.id.spinner);
        cPrice=findViewById(R.id.pPrice);
        cDescription=findViewById(R.id.pDescription);
        cQuantity = findViewById(R.id.pQty);
        btn_add = findViewById(R.id.btn_add);
        databaseHelper = new DatabaseHelper(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!cName.getText().toString().isEmpty()) {
                        if (!spinner.getSelectedItem().toString().isEmpty()) {
                            if (!cPrice.getText().toString().isEmpty()) {
                                if (!cQuantity.getText().toString().isEmpty()) {
                                    if (!cDescription.getText().toString().isEmpty()) {

                                        String cake_Name = cName.getText().toString();
                                        String cake_Flavour = spinner.getSelectedItem().toString();

                                        if (!cake_Flavour.equals("Please select a Flavour")) {
                                            double cake_Price = Double.parseDouble(cPrice.getText().toString());
                                            int cake_quantity = Integer.parseInt(cQuantity.getText().toString());
                                            String cake_Description = cDescription.getText().toString();

                                            Cake cake = new Cake(cake_Name, cake_Flavour,cake_Price,cake_quantity, cake_Description);
                                            addData(cake);
                                        } else
                                            Toast.makeText(CakeAdd.this, "Flavour should be Selected!!!", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(CakeAdd.this, "Description cannot be Empty!!!", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(CakeAdd.this, "Quantity cannot be Empty!!!", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(CakeAdd.this, "price cannot be Empty!!!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(CakeAdd.this, "Flavour should be Selected!!!", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(CakeAdd.this, "Cake Name cannot be Empty!!!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(CakeAdd.this, "Invalid Numeric Input!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addData(Cake cake) {

        if (databaseHelper.insertCake(cake)) {
            Toast.makeText(CakeAdd.this, "Successfully Inserted!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CakeAdd.this, CakesView.class);
            startActivity(intent);
        } else
            Toast.makeText(CakeAdd.this, "Error while Inserting....", Toast.LENGTH_LONG).show();
    }

}