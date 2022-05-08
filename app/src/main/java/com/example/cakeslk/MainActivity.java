package com.example.cakeslk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cakeslk.Employers.EmployeeAdd;
import com.example.cakeslk.Employers.EmployeeView;
import com.example.cakeslk.Orders.OrderAdd;
import com.example.cakeslk.Orders.OrderView;
import com.example.cakeslk.Supplier.SupplierView;
import com.example.cakeslk.cakes.CakeUpdate;
import com.example.cakeslk.cakes.CakesView;
import com.example.cakeslk.login.Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout employee;
    private LinearLayout cake;
    private LinearLayout supplier;
    private LinearLayout product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Cakes.lk");

        employee = findViewById(R.id.employee_layout);
        employee.setOnClickListener(this);

        cake = findViewById(R.id.cake_layout);
        cake.setOnClickListener(this);

        supplier = findViewById(R.id.supplier_layout);
        supplier.setOnClickListener(this);

        product = findViewById(R.id.product_layout);
        product.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void onClick(View view){
        switch (view.getId()){

            case R.id.employee_layout:
                Intent intent1 = new Intent(MainActivity.this, EmployeeView.class);
                startActivity(intent1);
                break;

            case R.id.cake_layout:
                Intent intent2 = new Intent(MainActivity.this, CakesView.class);
                startActivity(intent2);
                break;

            case R.id.product_layout:
                Intent intent3 = new Intent(MainActivity.this, OrderView.class);
                startActivity(intent3);
                break;

            case R.id.supplier_layout:
                Intent intent4 = new Intent(MainActivity.this, SupplierView.class);
                startActivity(intent4);
                break;

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //sign out method
    void signOut() {
        Intent i = new Intent(MainActivity.this, Login.class);
        finishAffinity();
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
