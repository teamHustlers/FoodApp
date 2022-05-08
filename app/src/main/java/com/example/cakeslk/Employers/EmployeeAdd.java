package com.example.cakeslk.Employers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


import com.example.cakeslk.R;

public class EmployeeAdd extends AppCompatActivity {

    EditText ename, enumber , eaddress, esalary, ebonus;
    Button btn_add;
    EmployeeDatabaseHelper employeeDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add);

        ename = findViewById(R.id.eName);
        enumber = findViewById(R.id.eNum);
        eaddress = findViewById(R.id.eAdd);
        esalary = findViewById(R.id.eSal);
        ebonus = findViewById(R.id.eBon);
        btn_add = findViewById(R.id.btn_add);
        employeeDatabaseHelper = new EmployeeDatabaseHelper(this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!ename.getText().toString().isEmpty()) {
                        if (!enumber.getText().toString().isEmpty()) {
                            if (!eaddress.getText().toString().isEmpty()) {
                                if (!esalary.getText().toString().isEmpty()) {
                                    if (!ebonus.getText().toString().isEmpty()) {
                                        String emp_Name = ename.getText().toString();

                                        int emp_Number = Integer.parseInt(enumber.getText().toString());
                                        String emp_Add = eaddress.getText().toString();
                                        double emp_Sal = Double.parseDouble(esalary.getText().toString());
                                        double emp_Bon = Double.parseDouble(ebonus.getText().toString());

                                        Employee employee = new Employee(emp_Name, emp_Number, emp_Add, emp_Sal, emp_Bon);
                                        addData(employee);
                                    }else
                                        Toast.makeText(EmployeeAdd.this, "Bonus couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(EmployeeAdd.this, "Salary couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(EmployeeAdd.this, "Address couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(EmployeeAdd.this, "Phone Number couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(EmployeeAdd.this, "Name couldn't be Empty!!!", Toast.LENGTH_LONG).show();

                } catch(NumberFormatException ex){

                    Toast.makeText(EmployeeAdd.this, "Invalid Details!!!", Toast.LENGTH_LONG).show();
                }


            }



        });

    }


    private void addData(Employee employee) {

        if(employeeDatabaseHelper.insertEmployee(employee)){

            Toast.makeText(EmployeeAdd.this, "Successfully Added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EmployeeAdd.this, EmployeeView.class);
            startActivity(intent);

        }else
            Toast.makeText(EmployeeAdd.this, "ERROR!!!!!!", Toast.LENGTH_LONG).show();
    }
}
