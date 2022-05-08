package com.example.cakeslk.Employers;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

    public int EmpId;
    public String EmployeeName;
    public int EmpNum;
    public String EmpAdd;
    public double EmpSal;
    public double EmpBon;

    public Employee() {
    }
    public Employee(String employeeName, int empNum, String empAdd, double empSal, double empBon) {
        EmployeeName = employeeName;
        EmpNum = empNum;
        EmpAdd = empAdd;
        EmpSal = empSal;
        EmpBon = empBon;

    }

    public int getEmpId() {return EmpId;}

    public void setEmpId(int empId) {EmpId = empId;}

    public String getEmployeeName() {return EmployeeName;}

    public void setEmployeeName(String employeeName) {EmployeeName = employeeName;}

    public int getEmpNum() {return EmpNum;}

    public void setEmpNum(int empNum) {EmpNum = empNum;}

    public String getEmpAdd() {return EmpAdd;}

    public void setEmpAdd(String empAdd) {EmpAdd = empAdd;}

    public double getEmpSal() { return EmpSal;}

    public void setEmpSal(double empSal) {EmpSal = empSal;}

    public double getEmpBon() {return EmpBon;}

    public void setEmpBon(double empBon) {EmpBon = empBon;}



    protected Employee (Parcel parcel){
        EmpId=parcel.readInt();
        EmployeeName=parcel.readString();
        EmpNum=parcel.readInt();
        EmpAdd=parcel.readString();
        EmpSal=parcel.readDouble();
        EmpBon=parcel.readDouble();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(EmpId);
        dest.writeString(EmployeeName);
        dest.writeInt(EmpNum);
        dest.writeString(EmpAdd);
        dest.writeDouble(EmpSal);
        dest.writeDouble(EmpBon);

    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

}
