package org.lamiclient;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Taxi extends Trip {

    //    Metered fares, Taxi Fullness, Fullness would be dynamically entered by druver side logic
//    Suggest Taxi Ranks near location,dumbed down google maps
    int fullness;

    Taxi(String driver_name,String license_plate,boolean is_available,int fullness){
        super(driver_name,license_plate,is_available);
        this.fullness = fullness;
    }

    LocalTime currentTime = LocalTime.now();
    LocalTime eightThirtyPM = LocalTime.of(20, 30);
    LocalTime fourThirtyAM = LocalTime.of(4, 30);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);


//    Method to access user current location (maybe have this randomoized for demo purposes)
//    Method to find ranks at a 20min walking distance to you
//    Method to display them
//    After you choose rank you can see the taxis that have not yet reached maximum capacity

    @Override
    void assigndriver(){
//        If fullness is at full capacity, throw suggestions of drivers with less fullness
        if (currentTime.isAfter(eightThirtyPM) || currentTime.isBefore(fourThirtyAM)){
            System.out.println("No taxis available. Try an uber");
        }else{
            if(is_available){
                System.out.println("Driver " + driver_name + " is picking you up  in a Uber with plate number: " + license_plate);
                is_available = false;
            } else if (!is_available) {
                System.out.println(driver_name + " is currently busy. Try another driver...");
            }

        }
    }

    @Override
    double calc_fare(double distance){
//        For now, price just ,metered hardcoded, will have driver side database that specifies routes, metered costs and locations etc...
        double fare = Math.round(distance * 10.50);
        return fare;

    }

    void rank(){
//        Even more random generation?
//        Better = Real-time location system
//        Advanced = Integrating google maps API

    }

}

