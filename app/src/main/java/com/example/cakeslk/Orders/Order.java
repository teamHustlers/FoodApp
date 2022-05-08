package com.example.cakeslk.Orders;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {


    public int OrderId;
    public String SupplierName;
    public String ProductType;
    public int Quantity;
    public double Amount;


    public Order(){

    }
    public Order(String suplierName, String productType, int quantity, double amount){
        SupplierName = suplierName;
        ProductType = productType;
        Quantity = quantity;
        Amount = amount;



    }
    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String suplierName) {
        SupplierName = suplierName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    protected Order(Parcel parcel) {
        OrderId = parcel.readInt();
        SupplierName = parcel.readString();
        ProductType = parcel.readString();
        Quantity = parcel.readInt();
        Amount = parcel.readDouble();


    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(OrderId);
        dest.writeString(SupplierName);
        dest.writeString(ProductType);
        dest.writeInt(Quantity);
        dest.writeDouble(Amount);

    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
