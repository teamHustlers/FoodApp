package com.example.cakeslk.cakes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cakeslk.MainActivity;
import com.example.cakeslk.R;

import java.util.ArrayList;

public class CakesView extends AppCompatActivity{

    DatabaseHelper databaseHelper;
    ArrayList<Cake> cakeList;
    ListView listView;
    Cake cake;
    Cursor data;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes_view);
        this.setTitle("Cake");

        databaseHelper = new DatabaseHelper(this);

        cakeList = new ArrayList<>();
        listView = findViewById(R.id.cakeListView);
        data=databaseHelper.displayCakes();
        int numRows = data.getCount();
        editText = findViewById(R.id.editText);

        if (numRows == 0)
            Toast.makeText(CakesView.this, "No Cakes to Display!", Toast.LENGTH_LONG).show();
        else {
            while (data.moveToNext()) {
                cake = new Cake(data.getString(1), data.getString(2), data.getDouble(3),data.getInt(4),data.getString(5));
                cake.setCakeId(data.getInt(0));
                cakeList.add(cake);
            }

            ArrayAdapter custom_obj = new CakeViewAdapter(this, cakeList);
            listView.setAdapter(custom_obj);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View vie, int i, long l) {
                Cake cake = (Cake) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(CakesView.this, CakeUpdate.class);
                intent.putExtra("Cake", (Parcelable) cake);
                startActivity(intent);
            }

        });

    }

    private void loadProducts(){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int textLength=charSequence.length();
                ArrayList<Cake> tempArrayList = new ArrayList<>();
                for(Cake p:cakeList){
                    if(textLength<=p.getCakeName().length()){
                        if(p.getCakeName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            tempArrayList.add(p);
                        }
                    }
                }

                if(tempArrayList.size()==0){
                    Toast.makeText(CakesView.this,"No Match Found!!!",Toast.LENGTH_SHORT).show();
                }else {
                    ArrayAdapter cus_arrayAdapter = new CakeViewAdapter(getApplicationContext(), tempArrayList);
                    final ListView custom_listView = findViewById(R.id.cakeListView);
                    custom_listView.setAdapter(cus_arrayAdapter);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        loadProducts();

    }

    public void setOnClick(View view){

        Intent intent = new Intent(this,CakeAdd.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(CakesView.this, MainActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.right_enter, R.anim.right_out);
    }
}
