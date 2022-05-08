package com.example.cakeslk.Employers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cakeslk.R;


public class EmployeeUpdate extends AppCompatActivity {
    EditText ename, enumber, eaddress, esalary, ebonus;
    ProgressBar progressBar;
    Button btnUpdate, btnDelete;
    EmployeeDatabaseHelper employeeDatabaseHelper;
    Employee employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_update);

        employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        Intent intent = getIntent();
        employee = intent.getParcelableExtra("Employers");

        ename = findViewById(R.id.eName);
        enumber = findViewById(R.id.eNum);
        eaddress = findViewById(R.id.eAdd);
        esalary = findViewById(R.id.eSal);
        ebonus = findViewById(R.id.eBon);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        ename.setText(employee.getEmployeeName());
        enumber.setText(Integer.toString(employee.getEmpNum()));
        eaddress.setText(employee.getEmpAdd());
        esalary.setText(Double.toString(employee.getEmpSal()));
        ebonus.setText(Double.toString((employee.getEmpBon())));


        ename.setEnabled(false);
        enumber.setEnabled(false);
        eaddress.setEnabled(false);
        esalary.setEnabled(false);
        ebonus.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                    btnUpdate.setText("UPDATE");
                    ename.setEnabled(true);
                    enumber.setEnabled(true);
                    eaddress.setEnabled(true);
                    esalary.setEnabled(true);
                    ebonus.setEnabled(true);
                } else {
                    try {
                        if (!ename.getText().toString().isEmpty()) {
                            if (!enumber.getText().toString().isEmpty()) {
                                if (!eaddress.getText().toString().isEmpty()) {
                                    if (!esalary.getText().toString().isEmpty()){
                                        if (!ebonus.getText().toString().isEmpty()){

                                            btnUpdate.setVisibility(View.INVISIBLE);
                                            btnDelete.setVisibility(View.INVISIBLE);
                                            progressBar.setVisibility(View.VISIBLE);

                                            String emp_Name = ename.getText().toString();
                                            int emp_Number = Integer.parseInt(enumber.getText().toString());
                                            String emp_Add = eaddress.getText().toString();
                                            double emp_Sal = Double.parseDouble(esalary.getText().toString());
                                            double emp_Bon = Double.parseDouble(ebonus.getText().toString());

                                            Employee employee1 = new Employee(emp_Name, emp_Number, emp_Add, emp_Sal,emp_Bon);
                                            employee1.setEmpId(employee.getEmpId());
                                            updateData(employee1);

                                        }else
                                            Toast.makeText(EmployeeUpdate.this, "Bonus couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(EmployeeUpdate.this, "Salary couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(EmployeeUpdate.this, "Address couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(EmployeeUpdate.this, "Phone Number couldn't be Empty!!!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(EmployeeUpdate.this, "Name couldn't be Empty!!!", Toast.LENGTH_LONG).show();

                    } catch (NumberFormatException ex) {

                        Toast.makeText(EmployeeUpdate.this, "Invalid Details!!!", Toast.LENGTH_LONG).show();
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

                employeeDatabaseHelper.deleteEmployee(employee.getEmpId());

                ename.setText("");
                enumber.setText("");
                eaddress.setText("");
                esalary.setText("");
                ebonus.setText("");

                Toast.makeText(EmployeeUpdate.this, "Successfully Deleted!!!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(EmployeeUpdate.this, EmployeeView.class);
                startActivity(intent);
            }

        });
    }

    private void updateData(Employee employee) {
        if(employeeDatabaseHelper.updateEmployee(employee)){
            Toast.makeText(EmployeeUpdate.this, "Successfully Updated!!!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(EmployeeUpdate.this, EmployeeView.class);
            startActivity(intent);

        }else
            Toast.makeText(EmployeeUpdate.this, "Error.........!!!", Toast.LENGTH_LONG).show();
    }
}
