package com.example.cakeslk.Supplier;

public class CalculateDelivery {
    public  double caltransport(String address, double delivery){
        double totaltrans;
if(address.equals("Colombo")){

    totaltrans = delivery ;
}
else{
    totaltrans = delivery + 200;
}

return totaltrans;
    }


}
