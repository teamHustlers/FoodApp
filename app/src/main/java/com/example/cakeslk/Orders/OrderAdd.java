package com.example.cakeslk.Orders;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cakeslk.R;

public class OrderAdd extends AppCompatActivity {
    EditText sName, pQuantity, pAmount;
    Spinner spinner;
    Button btn_add;
    OrderDatabaseHelper orderDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);

        sName = findViewById(R.id.sName);
        spinner = findViewById(R.id.p_spinner);
        pQuantity = findViewById(R.id.p_Quantity);
        pAmount = findViewById(R.id.p_Amount);
        btn_add = findViewById(R.id.btn_add1);
        orderDatabaseHelper = new OrderDatabaseHelper(this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!sName.getText().toString().isEmpty()) {
                        if (!spinner.getSelectedItem().toString().isEmpty()) {
                            if (!pQuantity.getText().toString().isEmpty()) {
                                if (!pAmount.getText().toString().isEmpty()) {

                                    String s_name = sName.getText().toString();
                                    String p_type = spinner.getSelectedItem().toString();

                                    if (!p_type.equals("Please Select the Product")) {
                                        int p_quant = Integer.parseInt(pQuantity.getText().toString());
                                        double p_amount = Double.parseDouble(pAmount.getText().toString());

                                        Order order = new Order(s_name, p_type, p_quant, p_amount);
                                        addData(order);

                                    } else
                                        Toast.makeText(OrderAdd.this, "Product should be selected", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(OrderAdd.this, "Amount cannot be empty", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(OrderAdd.this, "Quantity cannot be empty", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(OrderAdd.this, "Product should be selected", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(OrderAdd.this, "Supplier Name should be inserted", Toast.LENGTH_LONG).show();
                } catch (NumberFormatException ex) {
                    Toast.makeText(OrderAdd.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addData(Order order) {

        if(orderDatabaseHelper.insertOrder(order)){
            Toast.makeText(OrderAdd.this, "Successfully inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(OrderAdd.this, OrderView.class);
            startActivity(intent);

        }else
            Toast.makeText(OrderAdd.this, "Error Occurred........", Toast.LENGTH_LONG).show();
    }


}