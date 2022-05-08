package com.example.cakeslk.Supplier;

import android.os.Parcel;
import android.os.Parcelable;

public class Supplier implements Parcelable {

    public int SupplierID;
    public String Suppliername;
    public int SupplierAccountNumber;
    public int PhoneNumber;
    public String Address;
    public double Delivery;


    public Supplier() {

    }

    public Supplier(String Sname, int supplierAccountnumber, int phnumber, String address, double delivery) {
        Suppliername = Sname;
        SupplierAccountNumber = supplierAccountnumber;
        PhoneNumber = phnumber;
        Address = address;
        Delivery = delivery;


    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public String getSuppliername() {
        return Suppliername;
    }

    public void setSuppliername(String supplierName) {
        Suppliername = supplierName;
    }
    public int getSupplierAccountNumber() {
        return SupplierAccountNumber;
    }

    public void setSupplierAccountNumber(int supplierAccountNumber) {
        SupplierAccountNumber = supplierAccountNumber;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getDelivery() {
        return Delivery;
    }

    public void setDelivery(double delivery) {
        Delivery = delivery;
    }


    protected Supplier(Parcel in) {
        SupplierID = in.readInt();
        Suppliername = in.readString();
        SupplierAccountNumber = in.readInt();
        PhoneNumber = in.readInt();
        Address = in.readString();
        Delivery=in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(SupplierID);
        dest.writeString(Suppliername);
        dest.writeInt(SupplierAccountNumber);
        dest.writeInt(PhoneNumber);
        dest.writeString(Address);
        dest.writeDouble(Delivery);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Supplier> CREATOR = new Creator<Supplier>() {
        @Override
        public Supplier createFromParcel(Parcel in) {
            return new Supplier(in);
        }

        @Override
        public Supplier[] newArray(int size) {
            return new Supplier[size];
        }
    };




}

