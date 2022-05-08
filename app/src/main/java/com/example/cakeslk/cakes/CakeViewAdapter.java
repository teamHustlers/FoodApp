package com.example.cakeslk.cakes;

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

public class CakeViewAdapter extends ArrayAdapter {

    public CakeViewAdapter(@NonNull Context context, ArrayList<Cake> cakes) {
        super(context, R.layout.cake_custom_row, cakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View cakeView = inflater.inflate(R.layout.cake_custom_row, parent, false);

        Cake cake = (Cake) getItem(position);
        String cake_name = cake.CakeName;
        String cake_flavour = cake.Flavour;
        double cake_price = cake.Price;
        int cake_quantity = cake.Quantity;
        String cake_des = cake.Description;

        //Total Price Calculation
        TotalCalculation tc = new TotalCalculation();
        double total = tc.totalCalculate(cake_price,cake_quantity);

        TextView vcake_name = (TextView) cakeView.findViewById(R.id.cakeName);
        TextView vcake_flavour = (TextView) cakeView.findViewById(R.id.cakeFlavour);
        TextView vcake_price = (TextView) cakeView.findViewById(R.id.cakePrice);
        TextView vcake_quantity = (TextView) cakeView.findViewById(R.id.cakeQty);
        TextView vcake_total = (TextView)cakeView.findViewById(R.id.cakeTotal);
        ImageView cakeIcon = (ImageView) cakeView.findViewById(R.id.cakeIcon);

        switch (cake_flavour) {
            case "Vanilla":
                cakeIcon.setImageResource(R.drawable.ic_baseline_cake);
                break;

            case "Chocolate":
                cakeIcon.setImageResource(R.drawable.ic_baseline_cake);
                break;

            case "Strawberry":
                cakeIcon.setImageResource(R.drawable.ic_baseline_cake);
                break;
        }
        vcake_name.setText(cake_name);
        vcake_flavour.setText(cake_flavour);
        vcake_price.setText("Price : Rs " +Double.toString(cake_price)+"0");
        vcake_quantity.setText("Qty : "+Integer.toString(cake_quantity));
        vcake_total.setText("Total Amount : Rs "+Double.toString(total)+"0");
        return cakeView;
    }
}

