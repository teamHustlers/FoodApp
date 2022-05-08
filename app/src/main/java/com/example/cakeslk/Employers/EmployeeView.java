package com.example.cakeslk.Employers;

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

public class EmployeeView extends AppCompatActivity {

    EmployeeDatabaseHelper employeeDatabaseHelper;
    ArrayList<Employee> employeeList;
    Cursor data;
    EditText editText;
    ListView listView;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        this.setTitle("Employers");

        employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        employeeList = new ArrayList<>();
        listView = findViewById(R.id.employeeListView);
        data = employeeDatabaseHelper.displayEmployee();
        int numRow = data.getCount();
        editText = findViewById(R.id.editText);

        if (numRow == 0) {
            Toast.makeText(EmployeeView.this, "No Employers to display", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                employee = new Employee(data.getString(1), data.getInt(2), data.getString(3), data.getDouble(4),data.getDouble(  5 ));
                employee.setEmpId(data.getInt(0));
                employeeList.add(employee);
            }
            ArrayAdapter cus_obje = new EmployeeViewAdapter(this, employeeList);
            listView.setAdapter(cus_obje);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee = (Employee) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(EmployeeView.this, EmployeeUpdate.class);
                intent.putExtra("Employers", (Parcelable) employee);
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
                ArrayList<Employee> tempArrayList = new ArrayList<>();
                for(Employee e:employeeList){
                    if(textLength <= e.getEmployeeName().length()){
                        if(e.getEmployeeName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            tempArrayList.add(e);
                        }
                    }
                }

                if(tempArrayList.size()==0){
                    Toast.makeText(EmployeeView.this, "No Match Found!!!!", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayAdapter e_arrayadapter = new EmployeeViewAdapter(getApplicationContext(), tempArrayList);
                    final ListView e_listView = findViewById(R.id.employeeListView);
                    e_listView.setAdapter(e_arrayadapter);


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

        Intent intent = new Intent(this, EmployeeAdd.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(EmployeeView.this, MainActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.right_enter, R.anim.right_out);
    }
}
