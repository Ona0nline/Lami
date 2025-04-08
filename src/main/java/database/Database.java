package database;
import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class Database {
//    Establishing a database connection

    public static Connection connection(){
        Connection conn = null;
//        Sqlite connection string
        try{
            String url = "jdbc:sqlite:clients.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conn;
    }


}

class Insert {

    // Method to insert a driver
    public static void insertDriver(int id, String name, String surname, String phone_number, String email, String password,
                                    boolean is_available, String license_plate, String car, String location, boolean complete_ride, int payment_id) {
        String sql = "INSERT INTO drivers(id, name, surname, phone_number, email, password, is_available, license_plate, car, location, complete_ride, payment_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Database.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(true);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, phone_number);
            pstmt.setString(5, email);
            pstmt.setString(6, password);
            pstmt.setBoolean(7, is_available);
            pstmt.setString(8, license_plate);
            pstmt.setString(9, car);
            pstmt.setString(10, location);
            pstmt.setBoolean(11, complete_ride);
            pstmt.setInt(12, payment_id);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted for driver.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Insertion error for driver: " + e.getMessage());
        }
    }

    // Method to insert a user
    public static void insertUsers(int id, String name, String surname, String username, String email, String phone_number, String password, String location) {
        String sql = "INSERT INTO users(id, name, surname, username, email, phone_number, password, location) VALUES (?,?,?,?,?,?,?,?)";
        System.out.println("Attempting to insert user with ID: " + id);

        try (Connection conn = Database.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(true);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, username);
            pstmt.setString(5, email);
            pstmt.setString(6, phone_number);
            pstmt.setString(7, password);
            pstmt.setString(8, location);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted for user.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Insertion error for user: " + e.getMessage());
        }
    }

    public static void insertLami(int id, int driver_id, int license_plate_id, int car_id, String quality, String start_location, String end_location, double fare, String ride_status, String payment_status, int estimated_time, double distance){
        String sql = "INSERT INTO lami(id, driver_id, license_plate_id, car_id, quality,start_location, end_location, fare, ride_status, payment_status, estimated_time, distance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Inserting...");
        try (Connection conn = Database.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            conn.setAutoCommit(true);
            pstmt.setInt(1,id);
            pstmt.setInt(2,driver_id);
            pstmt.setInt(3,license_plate_id);
            pstmt.setInt(4,car_id);
            pstmt.setString(5,quality);
            pstmt.setString(6,start_location);
            pstmt.setString(7,end_location);
            pstmt.setDouble(8,fare);
            pstmt.setString(9,ride_status);
            pstmt.setString(10,payment_status);
            pstmt.setInt(11,estimated_time);
            pstmt.setDouble(12,distance);


            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted for user.");


        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

  }

    // Main method to test both insertions
    public static void main(String[] args) {
//        93 employed I guess..
        Random random = new Random();
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= 93; i++) {
            ids.add(i);
        }

        List<String> quality = new ArrayList<>();
        String[] quality_ops = {"A", "B", "C","D"};
        for (int i = 0; i < 93; i++) {
            quality.add(quality_ops[random.nextInt(4)]);
        }


        // List of start locations (random South African locations)
        List<String> startLocations = Arrays.asList(
                "Johannesburg", "Cape Town", "Durban", "Pretoria", "Port Elizabeth",
                "Bloemfontein", "East London", "Pietermaritzburg", "Kimberley", "Polokwane",
                "Nelspruit", "Rustenburg", "George", "Knysna", "Stellenbosch", "Soweto",
                "Sandton", "Randburg", "Midrand", "Centurion", "Paarl", "Worcester", "Oudtshoorn",
                "Mossel Bay", "Hermanus", "Somerset West", "Stellenbosch", "Franschhoek", "Clarens",
                "Dullstroom", "Hazyview", "White River", "Mbombela", "Plettenberg Bay", "Jeffreys Bay",
                "Graaff-Reinet", "Beaufort West", "Upington", "Kuruman", "Vryburg", "Mahikeng", "Lichtenburg",
                "Potchefstroom", "Klerksdorp", "Vereeniging", "Vanderbijlpark", "Sasolburg", "Welkom", "Bethlehem",
                "Phuthaditjhaba", "Qwaqwa", "Thaba Nchu", "Ladybrand", "Ficksburg", "Clocolan", "Harrismith", "Kroonstad",
                "Bothaville", "Villiers", "Frankfort", "Heilbron", "Parys", "Vredefort", "Koppies", "Lindley", "Reitz",
                "Petrus Steyn", "Senekal", "Marquard", "Clarens", "Fouriesburg", "Bethulie", "Aliwal North", "Barkly East",
                "Lady Frere", "Cofimvaba", "Queenstown", "Cathcart", "Stutterheim", "King William's Town", "Butterworth",
                "Mthatha", "Port St Johns", "Lusikisiki", "Mount Frere", "Mount Ayliff", "Matatiele", "Kokstad", "Harding",
                "Port Shepstone", "Margate", "Ramsgate", "Southbroom", "Umkomaas", "Amanzimtoti", "Umlazi", "Isipingo"
        );

        // List of end locations (random South African locations)
        List<String> endLocations = new ArrayList<>(startLocations);
        Collections.shuffle(endLocations); // Shuffle to randomize end locations

        // List of distances (random distances between 5 km and 200 km)
        List<Double> distances = new ArrayList<>();

        for (int i = 0; i < 93; i++) {
            distances.add(5 + random.nextDouble() * 195); // Random distance between 5 and 200 km
        }

        List<Integer> estimatedTimes = new ArrayList<>();
        for (double distance : distances) {
            double roundedDistance = Math.round(distance * 100.0) / 100.0; // Round to 2 decimal places
            estimatedTimes.add((int) Math.round(roundedDistance / 60)); // Convert to minutes
        }


        // List of fares (calculated as distance * 5.50, 10.50, or 12.50)
        List<Double> fares = new ArrayList<>();
        for (double distance : distances) {
            int fareType = random.nextInt(3); // Randomly choose fare type
            double fare = 0;
            switch (fareType) {
                case 0:
                    fare = Math.round(distance * 5.50 * 100.0) / 100.0;
                    break;
                case 1:
                    fare = Math.round(distance * 10.50 * 100.0) / 100.0;
                    break;
                case 2:
                    fare = Math.round(distance * 12.50 * 100.0) / 100.0;
                    break;
            }
            fares.add(fare);
        }

        List<String> ridestatuses = new ArrayList<>();
        String[] ridestatuses_ = {"Pending", "Begin", "Done"};
        for (int i = 0; i < 93; i++) {
            ridestatuses.add(ridestatuses_[random.nextInt(3)]);
        }

        // List of payment statuses (randomly chosen from "Pending", "Approved", or "Declined")
        List<String> paymentStatuses = new ArrayList<>();
        String[] statusOptions = {"Pending", "Approved", "Declined"};
        for (int i = 0; i < 93; i++) {
            paymentStatuses.add(statusOptions[random.nextInt(3)]);
        }


// Payment IDs (unique)

        // Test inserting a driver
        try {
            for (int i = 0; i < ids.size(); i++) {
                insertLami(ids.get(i), ids.get(i), ids.get(i), ids.get(i), quality.get(i), startLocations.get(i), endLocations.get(i), fares.get(i), ridestatuses.get(i), paymentStatuses.get(i), estimatedTimes.get(i), distances.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error inserting driver: " + e.getMessage());
        }

    }
}
