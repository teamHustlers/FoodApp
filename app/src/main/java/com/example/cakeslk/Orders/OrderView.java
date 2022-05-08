package com.example.cakeslk.Orders;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.cakeslk.MainActivity;
import com.example.cakeslk.R;


import java.util.ArrayList;

public class OrderView extends AppCompatActivity {

        OrderDatabaseHelper orderDatabaseHelper;
        ArrayList<Order> orderList;
        ListView listView;
        Order order;
        Cursor data;
        EditText editText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_order_view);
                this.setTitle("Orders");

                orderDatabaseHelper = new OrderDatabaseHelper(this);

                orderList = new ArrayList<>();
                listView = findViewById(R.id.orderListView);
                data = orderDatabaseHelper.displayOrder();
                int numRows = data.getCount();
                editText = findViewById(R.id.editText);

                if (numRows == 0)
                       Toast.makeText(OrderView.this, "No Orders to Display", Toast.LENGTH_LONG).show();
                  else{
                          while(data.moveToNext()){
                                  order = new Order(data.getString(1), data.getString(2), data.getInt(3), data.getDouble(4));
                                  order.setOrderId(data.getInt(0));
                                  orderList.add(order);
                          }
                          ArrayAdapter custom_object = new OrderViewAdapter(this,orderList);
                          listView.setAdapter(custom_object);
                }
                  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                          @Override
                          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                  Order order = (Order) adapterView.getItemAtPosition(i);

                                  Intent intent = new Intent(OrderView.this, OrderUpdate.class);
                                  intent.putExtra("Orders",(Parcelable) order);
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
                                ArrayList<Order> tempArrayList = new ArrayList<>();
                                for(Order p:orderList){
                                        if(textLength<=p.getSupplierName().length()){
                                                if(p.getSupplierName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                                        tempArrayList.add(p);
                                                }
                                        }
                                }
                                if(tempArrayList.size()==0){
                                        Toast.makeText(OrderView.this, "No Match Found!!!!", Toast.LENGTH_SHORT).show();
                                }else{
                                        ArrayAdapter p_arrayadapter = new OrderViewAdapter(getApplicationContext(), tempArrayList);
                                        final ListView p_listView = findViewById(R.id.orderListView);
                                        p_listView.setAdapter(p_arrayadapter);


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

                Intent intent = new Intent(this, OrderAdd.class);
                startActivity(intent);
        }

        @Override
        public void onBackPressed() {
                super.onBackPressed();
                Intent i = new Intent(OrderView.this, MainActivity.class);
                startActivity(i);
                this.overridePendingTransition(R.anim.right_enter, R.anim.right_out);
        }
}



