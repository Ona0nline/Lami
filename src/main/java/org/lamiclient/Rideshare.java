package org.lamiclient;

import database.Database;

import java.sql.*;
import java.util.*;

public class Rideshare {
    private int id;
    private String driver_name;
    private String surname;
    private boolean is_available;
    private String license_plate;
    private String car;

    public Rideshare(int id, String driver_name, String surname, boolean is_available, String license_plate, String car) {
        this.id = id;
        this.driver_name = driver_name;
        this.surname = surname;
        this.is_available = is_available;
        this.license_plate = license_plate;
        this.car = car;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Ride Type:\n1. Lami\n2. Taxi\n3. Luxury");
        int ride_type = input.nextInt();
        input.close();

        switch(ride_type){
            case 1:
                Lami lami = new Lami();
                lami.assigndriver();
        }


//        @Override
//        public String toString () {
//            return "[Name: " + driver_name + ", Surname: " + surname +
//                    ", Available: " + is_available + ", License Plate: " + license_plate +
//                    ", Car: " + car + "]";
//        }
    }
}
