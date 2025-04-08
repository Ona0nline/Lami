package org.lamiclient;

import database.Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Lami extends Trip {
    private int id;
    private String driver_name;
    private String license_plate;
    private String car;
    private String quality;
    private String start_location;
    private String end_location;
    private double fare;
    private String ride_status;
    private String payment_status;
    private BigDecimal estimated_time;
    private BigDecimal distance;


    Lami(String driver_name,String license_plate,boolean is_available){
        super(driver_name,license_plate,is_available);
    }

//    Tekporary constructor overloading to deal with new attributes
    public Lami(int id, String driver_name, String license_plate, String car, String quality, String start_location, String end_location,
                double fare, String ride_status, String payment_status, BigDecimal estimated_time, BigDecimal distance){
        super();
        this.id = id;
        this.driver_name = driver_name;
        this.license_plate = license_plate;
        this.car = car;
        this.quality = quality;
        this.start_location = start_location;
        this.end_location = end_location;
        this.fare = fare;
        this.ride_status = ride_status;
        this.payment_status = payment_status;
        this.estimated_time = estimated_time;
        this.distance = distance;

    }

    public Lami(){

    }

    //    Getting current time
    LocalTime currentTime = LocalTime.now();
    LocalTime TenPM = LocalTime.of(22, 00);
    LocalTime fourThirtyAM = LocalTime.of(4, 30);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);

    String[] excuses = {"Rain","Traffic","None","Complex Route"};
    int excuse = (int)(Math.random() * excuses.length);

    private static ArrayList<Lami> fetchAvailableLamis() {
        ArrayList<Lami> lamis = new ArrayList<>();

        String sql = "SELECT lami.*, drivers.name AS driver_name, drivers.license_plate AS license_plate, drivers.car AS car " +
                "FROM lami " +
                "JOIN drivers ON lami.driver_id = drivers.id " +
                "WHERE drivers.is_available = 1 AND car IS NOT 'Taxi'";

        try (Connection conn = Database.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lamis.add(new Lami(
                        rs.getInt("id"),
                        rs.getString("driver_name"),
                        rs.getString("license_plate"),
                        rs.getString("car"),
                        rs.getString("quality"),
                        rs.getString("start_location"),
                        rs.getString("end_location"),
                        rs.getDouble("fare"),
                        rs.getString("ride_status"),
                        rs.getString("payment_status"),
                        rs.getBigDecimal("estimated_time"),
                        rs.getBigDecimal("distance")

                ));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return lamis;
    }


    @Override
    void assigndriver(){
        if (currentTime.isAfter(TenPM) || currentTime.isBefore(fourThirtyAM)){
            System.out.println("Lamis take longer around this time...Please be patient.");
        }else{
            Random randomLami = new Random();
            int index = randomLami.nextInt(Math.max(0,fetchAvailableLamis().size()));
            Lami chosen = fetchAvailableLamis().get(index);
            System.out.println(chosen.toString());
        }
    }

    @Override
    void complete_ride() {
        super.complete_ride();
    }

    void payment_type(){

    }

    @Override
    double calc_fare(double distance) {
        double fare = 0;
        System.out.println(excuses[excuse]);
        if (excuses[excuse].equals("None")){
            System.out.println("Fare: R" + super.calc_fare(distance));

        } else if (excuses[excuse].equals("Rain")) {
            System.out.println("Fare: R" + distance * 12.50 );
        } else if (excuses[excuse].equals("Traffic")) {
            System.out.println("Fare: R" + distance * 12.50 );

        } else if (quality.equals("F")){
            System.out.println("Grade F Vehicle. Fare significantly reduced :)");
            fare = distance;
            System.out.println("Fare: R" + distance * 5.50);

        }
        else if (excuses[excuse].equals("Complex Route")) {
            System.out.println("Fare: R" + distance * 12.50 );
        }
        return fare;

    }

    @Override
    public String toString () {

        return "[Name: " + driver_name + ", License Plate: " + license_plate + ", Car: " + car + ", Quality of vehicle: " + quality + "]";
    }
}

