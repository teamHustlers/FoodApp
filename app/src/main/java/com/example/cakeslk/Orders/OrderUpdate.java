package com.example.cakeslk.Orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cakeslk.R;

public class OrderUpdate extends AppCompatActivity{

    EditText sName, pQuantity, pAmount;
    Spinner spinner;
    ProgressBar progressBar;
    Button btnUpdate,btnDelete;
    OrderDatabaseHelper orderDatabaseHelper;
    Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update);

        orderDatabaseHelper = new OrderDatabaseHelper(this);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("Orders");

        sName = findViewById(R.id.sName);
        spinner = findViewById(R.id.p_spinner);
        pQuantity = findViewById(R.id.p_Quantity);
        pAmount = findViewById(R.id.p_Amount);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        sName.setText(order.getSupplierName());
        String productType = order.getProductType();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.product,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(productType!=null){
            int spinnerPos = adapter.getPosition(productType);
            spinner.setSelection(spinnerPos);
        }

        pQuantity.setText(Integer.toString(order.getQuantity()));
        pAmount.setText(Double.toString(order.getAmount()));

        sName.setEnabled(false);
        spinner.setEnabled(false);
        pQuantity.setEnabled(false);
        pAmount.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                    btnUpdate.setText("UPDATE");
                    sName.setEnabled(true);
                    spinner.setEnabled(true);
                    pQuantity.setEnabled(true);
                    pAmount.setEnabled(true);
                } else {
                    try {
                        if (!sName.getText().toString().isEmpty()) {
                            if (!spinner.getSelectedItem().toString().equals("Please select a Flavour")) {
                                if (!pQuantity.getText().toString().isEmpty()) {
                                    if (!pAmount.getText().toString().isEmpty()) {


                                        btnUpdate.setVisibility(View.INVISIBLE);
                                        btnDelete.setVisibility(View.INVISIBLE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        String s_name = sName.getText().toString();
                                        String p_type = spinner.getSelectedItem().toString();
                                        int p_quant = Integer.parseInt(pQuantity.getText().toString());
                                        double p_amount = Double.parseDouble(pAmount.getText().toString());

                                        Order order1 = new Order(s_name, p_type, p_quant, p_amount);
                                        order1.setOrderId(order.getOrderId());
                                        updateData(order1);

                                    } else
                                        Toast.makeText(OrderUpdate.this, "Amount Cannot be Empty", Toast.LENGTH_LONG).show();

                                } else
                                    Toast.makeText(OrderUpdate.this, "Quantity cannot be empty", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(OrderUpdate.this, "Product should be selected", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(OrderUpdate.this, "Supplier Name Cannot be Empty", Toast.LENGTH_LONG).show();

                    } catch (NumberFormatException ex) {
                        Toast.makeText(OrderUpdate.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
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

                orderDatabaseHelper.deleteOrder(order.getOrderId());

                sName.setText("");
                pQuantity.setText("");
                pAmount.setText("");

                Toast.makeText(OrderUpdate.this, "Order Deleted Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(OrderUpdate.this, OrderView.class);
                startActivity(intent);

            }
        });



    }

            private void updateData(Order order) {
                if(orderDatabaseHelper.updateOrder(order)){
                    Toast.makeText(OrderUpdate.this,"Successfully Updated",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(OrderUpdate.this, OrderView.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(OrderUpdate.this, "Error Occurred..........", Toast.LENGTH_LONG).show();



        }


  }