package com.example.cakeslk.cakes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cakeslk.R;


public class CakeUpdate extends AppCompatActivity {

    EditText cName,cPrice,cDescription,cQuantity;
    Spinner spinner;
    ProgressBar progressBar;
    Button btnUpdate,btnDelete;
    DatabaseHelper databaseHelper;
    Cake cake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_update);

        databaseHelper=new DatabaseHelper(this);

        Intent intent = getIntent();
        cake = intent.getParcelableExtra("Cake");

        cName = findViewById(R.id.pName);
        cDescription = findViewById(R.id.pDescription);
        cPrice = findViewById(R.id.pPrice);
        cQuantity = findViewById(R.id.pQty);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        cName.setText(cake.getCakeName());
        String flavour = cake.getFlavour();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(flavour!=null){
            int spinnerPos=adapter.getPosition(flavour);
            spinner.setSelection(spinnerPos);
        }
        cPrice.setText(Double.toString(cake.getPrice()));
        cQuantity.setText(Integer.toString(cake.getQuantity()));
        cDescription.setText(cake.getDescription());

        cName.setEnabled(false);
        spinner.setEnabled(false);
        cPrice.setEnabled(false);
        cQuantity.setEnabled(false);
        cDescription.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                    btnUpdate.setText("UPDATE");
                    cName.setEnabled(true);
                    spinner.setEnabled(true);
                    cPrice.setEnabled(true);
                    cQuantity.setEnabled(true);
                    cDescription.setEnabled(true);
                }
                else{

                    try{

                        if(!cName.getText().toString().isEmpty()){
                            if(!spinner.getSelectedItem().toString().equals("Please select a Category")){
                                if (!cPrice.getText().toString().isEmpty()){
                                    if (!cQuantity.getText().toString().isEmpty()){
                                        if(!cDescription.getText().toString().isEmpty()){

                                            btnUpdate.setVisibility(View.INVISIBLE);
                                            btnDelete.setVisibility(View.INVISIBLE);
                                            progressBar.setVisibility(View.VISIBLE);

                                            String cake_Name=cName.getText().toString();
                                            String cake_Flavour=spinner.getSelectedItem().toString();
                                            double cake_Price=Double.parseDouble(cPrice.getText().toString());
                                            int cake_Quantity= Integer.parseInt(cQuantity.getText().toString());
                                            String cake_Description=cDescription.getText().toString();

                                            Cake cake1 = new Cake(cake_Name,cake_Flavour,cake_Price,cake_Quantity,cake_Description);
                                            cake1.setCakeId(cake.getCakeId());
                                            updateData(cake1);
                                        }
                                        else
                                            Toast.makeText(CakeUpdate.this,"Description cannot be Empty!!!",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(CakeUpdate.this,"Quantity cannot be Empty!!!",Toast.LENGTH_LONG).show();
                                }
                                else
                                    Toast.makeText(CakeUpdate.this,"Price cannot be Empty!!!",Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(CakeUpdate.this,"Flavour should be Selected!!!",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(CakeUpdate.this,"Cake Name cannot be Empty!!!",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Toast.makeText(CakeUpdate.this,"Invalid Numeric Input!!!",Toast.LENGTH_LONG).show();
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

                databaseHelper.deleteCake(cake.getCakeId());

                cName.setText("");
                cPrice.setText("");
                cQuantity.setText("");
                cDescription.setText("");

                Toast.makeText(CakeUpdate.this,"Cake Successfully Deleted!!!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CakeUpdate.this,CakesView.class);
                startActivity(intent);
            }
        });
    }


    public void updateData(Cake cake){

        if(databaseHelper.updateCake(cake)){
            Toast.makeText(CakeUpdate.this,"Successfully Updated!!!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CakeUpdate.this,CakesView.class);
            startActivity(intent);
        }

        else
            Toast.makeText(CakeUpdate.this,"Error while Updating....",Toast.LENGTH_LONG).show();
    }

}