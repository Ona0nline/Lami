package org.lamiclient;

public class Trip {
    String driver_name;
    String license_plate;
    boolean is_available;


    Trip(String driver_name,String license_plate,boolean is_available){
        this.license_plate = license_plate;
        this.driver_name = driver_name;
        this.is_available = is_available;
    }

    Trip(){

    }

    void assigndriver(){
        if(is_available){
            System.out.println("Driver " + driver_name + "is picking you up");
            is_available = false;
        }else{
            System.out.println(driver_name + " is currently busy. Try another driver...");
        }
    }

    void complete_ride(){
        System.out.println("Driver " + driver_name + " has completed the ride");
    }

    void payment_type(String type){
//        .equals better string comparison than ==
        if (type.equalsIgnoreCase("cash")){
            System.out.println("Exact change not guaranteed. Please have correct fare ready.");
        }else{
            System.out.println("Please enter banking details");
        }
    }

    double calc_fare(double distance){
        double fare = Math.round(distance * 10.50);
        return fare;
    }


}
