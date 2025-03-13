package org.lamiclient;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import org.lamiclient.Database;

//Ui for app (luxury) = Slideshow of the cars, premium features listed with etc, dramatic tantalising design

//Exclusive ride share app whereby you choose driver + car you want to driven in


class Trip{
    String driver_name;
    String license_plate;
    boolean is_available;


    Trip(String driver_name,String license_plate,boolean is_available){
        this.license_plate = license_plate;
        this.driver_name = driver_name;
        this.is_available = is_available;
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

class Uber extends Trip{
    String quality;

    Uber(String driver_name,String license_plate,boolean is_available, String quality){
        super(driver_name,license_plate,is_available);
        this.quality = quality;
    }

//    Getting current time
    LocalTime currentTime = LocalTime.now();
    LocalTime TenPM = LocalTime.of(22, 00);
    LocalTime fourThirtyAM = LocalTime.of(4, 30);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);

    String[] excuses = {"Rain","Traffic","None","Complex Route"};
    int excuse = (int)(Math.random() * excuses.length);


    @Override
    void assigndriver(){
        if (currentTime.isAfter(TenPM) || currentTime.isBefore(fourThirtyAM)){
            System.out.println("Ubers take longer around this time...Please be patient.");
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
}

class Taxi extends Trip{
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

class Luxury extends Trip{

//    Future methods

//    Schedule Ride – Allows users to pre-book a luxury ride.
//Preferred Driver – Users can request a specific driver.
//Onboard Features – Differentiate based on in-car features (e.g., WiFi, bottled water, charging ports).
//Custom Route – Allows passengers to specify a route instead of the default GPS route.
//Privacy Mode – No talking, tinted windows, or music control for a quieter experience.
//Extra Security – Panic button or tracking for added safety.
//Exclusive Memberships – Some luxury services have loyalty programs or premium access tiers. FUTURE

    boolean preferred_driver;
    String[] onboard;
    boolean privacy;
    String membership;

    Luxury(String driver_name,String license_plate,boolean is_available,String[] onboard,boolean privacy,String membership){
        super(driver_name,license_plate,is_available);
        this.preferred_driver = preferred_driver;
        this.onboard = onboard;
        this.privacy = privacy;
        this.membership = membership;
    }

//    User UI ask if they prefer a certain driver. If yes, display list of current availiable drivers
    @Override
    void assigndriver(){

            if(is_available){
                System.out.println("Driver " + driver_name + " is picking you up");
                is_available = false;
            }else{
                System.out.println(driver_name + " is currently busy. Try another driver...");
            }
    }

    @Override
    double calc_fare(double distance){
        double fare = super.calc_fare(distance);
        System.out.println("Fare: R" + distance * 18.00);
        return fare;
    }




}
public class Rideshare {

    public static void injection(){

        Database.connection();
////        Insert.insertDriver();
//      try {
//                for(int i = 0; i < Database.ids.size(); i++){
//                    Database.insertDriver(ids.get(i),names.get(i),surnames.get(i), phoneNumbers.get(i),emails.get(i),passwords.get(i),isAvailable.get(i), licensePlates.get(i), cars.get(i),locations.get(i),completeRide.get(i),paymentIds.get(i) );
//            }
//        } catch (Exception e) {
//            System.out.println("Error inserting driver: " + e.getMessage());
//        }
    }

//    public static void main(String[] args) {
////        Client side so client focused
//
//        Scanner input = new Scanner(System.in);
//        System.out.println("Choose Ride Type:\n1.Uber\n2.Taxi\n3.Luxury");
//        int ride_type = input.nextInt();
//        int index = (int) (Math.random() * Database.);
//        input.nextLine();
//        if (ride_type == 1) {
//            System.out.println("Enter distance: ");
//            double distance = input.nextDouble();
//            if (ubers.get(index).is_available) {
//                ubers.get(index).assigndriver();
//                ubers.get(index).calc_fare(distance);
//                ubers.get(index).complete_ride();
//                ubers.get(index).is_available = true;
//            } else {
//                ubers.get(index).assigndriver();
//            }
//
//        } else if (ride_type == 3) {
////            Membership access determines certain perks and questions and ui, for now just basics
//            System.out.println("Good day " + luxuries.get(index).membership + " member!\nHelp us make your ride as comfortable as possible.");
//            // Inside the luxury ride section
//            System.out.println("Do you want to pick a driver (y/N)");
////            input.nextLine(); // Consume newline
//            String preferredDriver = input.nextLine();
//
//            Luxury selectedLuxury = null; // Store chosen luxury ride
//            ArrayList<Luxury> availableLuxuries = new ArrayList<>(); // Store available cars
//
//            if (preferredDriver.equalsIgnoreCase("y")) {
//                System.out.println("Available Luxury Rides:");
//
//                int i = 1; // Numbering for easier selection
//                for (Luxury luxury : luxuries) {
//                    if (luxury.is_available) {
//                        System.out.println(i + ". " + luxury.driver_name + " - " + luxury.license_plate + " - " + Arrays.toString(luxury.onboard));
//                        availableLuxuries.add(luxury); // Store the available luxury car
//                        i++;
//                    }
//                }
//
//                if (availableLuxuries.isEmpty()) {
//                    System.out.println("No available luxury rides at the moment.");
//                    return;
//                }else{
//                    System.out.println("Select Driver by name: ");
//                    String selectedDriver = input.nextLine();
//                    System.out.println("Enter distance: ");
//                    double route = input.nextDouble();
//                    for (Luxury driver : availableLuxuries){
//                        if (driver.driver_name.equalsIgnoreCase(selectedDriver)){
//                            driver.assigndriver();
//                            driver.calc_fare(route);
//                            driver.complete_ride();
//                        }
//                    }
//
//                }
//
//            }
//
//        }
//    }
}
