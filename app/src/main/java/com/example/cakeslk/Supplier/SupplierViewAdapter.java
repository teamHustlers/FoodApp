package com.example.cakeslk.Supplier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cakeslk.R;

import java.util.ArrayList;

public class SupplierViewAdapter extends ArrayAdapter {

    public SupplierViewAdapter(@NonNull Context context, ArrayList<Supplier> Supplier) {
        super(context, R.layout.supplier_custom_row, Supplier);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View supplierView = inflater.inflate(R.layout.supplier_custom_row, parent, false);

        Supplier supplier = (Supplier) getItem(position);
        String s_Name = supplier.Suppliername;
        int s_Num = supplier.SupplierAccountNumber;
        int p_Num = supplier.PhoneNumber;
        String s_Add = supplier.Address;
        double s_del = supplier.Delivery;

        //Calculation for Transport
        CalculateDelivery cd = new CalculateDelivery();
        double totaltrans = cd.caltransport(s_Add,s_del);

        TextView vs_name = (TextView)supplierView.findViewById(R.id.suName);
        TextView vs_Num = (TextView)supplierView.findViewById(R.id.anum);
        TextView vp_Num = (TextView)supplierView.findViewById((R.id.phnum));
        TextView vs_Add = (TextView)supplierView.findViewById(R.id.sadd);
        TextView vs_del = (TextView)supplierView.findViewById(R.id.sdel);
        TextView v_total=(TextView)supplierView.findViewById(R.id.Del_Char);

        vs_name.setText(s_Name);
        vs_Num.setText(""+Integer.toString(s_Num));
        vp_Num.setText(""+Integer.toString(p_Num));
        vs_Add.setText(s_Add);
        vs_del.setText("Rs. " +Double.toString(s_del));
        v_total.setText("Total Transport Charge Rs. " +Double.toString(totaltrans));

        return supplierView;

    }
}
