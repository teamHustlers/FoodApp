package com.example.cakeslk.Orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cakeslk.R;

import java.util.ArrayList;


public class OrderViewAdapter extends ArrayAdapter {

    public OrderViewAdapter(@NonNull Context context, ArrayList<Order> Orders) {
        super(context, R.layout.order_custom_row, Orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View orderView = inflater.inflate(R.layout.order_custom_row, parent, false);

        Order order = (Order) getItem(position);
        String s_name = order.SupplierName;
        String p_type = order.ProductType;
        int p_quant = order.Quantity;
        double p_amount = order.Amount;


        //Total Amount Calculation
        CalculateAmount ca = new CalculateAmount();
        double amount = ca.calAmount(p_quant, p_amount);




        TextView vs_name = (TextView)orderView.findViewById(R.id.supplierName);
        TextView vp_type = (TextView)orderView.findViewById(R.id.productType);
        TextView vp_quant = (TextView)orderView.findViewById(R.id.quantity);
        TextView vp_amount = (TextView)orderView.findViewById(R.id.amount);
        TextView v_amount = (TextView)orderView.findViewById(R.id.tamount);
        ImageView orderIcon = (ImageView) orderView.findViewById(R.id.orderIcon);


        switch (p_type){
            case "Eggs and Milk":
                orderIcon.setImageResource(R.drawable.ic_baseline_product);
                break;

            case "Vanilla And Choclate Essense and Flavours":
                orderIcon.setImageResource(R.drawable.ic_baseline_product);
                break;
            case "Decorating Items":
                orderIcon.setImageResource(R.drawable.ic_baseline_product);
                break;

        }

        vs_name.setText(s_name);
        vp_type.setText(p_type);
        vp_quant.setText(""+Integer.toString(p_quant));
        vp_amount.setText("Rs. " +Double.toString(p_amount)+"0");
        v_amount.setText("Total Amount is : Rs. "+Double.toString(amount)+"0");



        return orderView;


    }

}
