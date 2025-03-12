package org.lamiclient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Database {
//    Establishing a database connection

    public static Connection connection(){
        Connection conn = null;
//        Sqlite connection string
        try{
            String url = "jdbc:sqlite:clients.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void main(String[] args) {
        connection();  // Test the connection
    }
}

class Insert extends Database{
    public static void insertDriver( int id,String name,String surname,String phone_number,String email,String password,boolean is_available,String license_plate,String car,String location,boolean complete_ride, int payment_id) throws SQLException {
         String sql = "INSERT INTO taxi( id,name,surname,phone_number,email,password,is_available,license_plate,car, location, complete_ride,payment_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Database.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            conn.setAutoCommit(true);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,surname);
            pstmt.setString(4,phone_number);
            pstmt.setString(5,email);
            pstmt.setString(6,password);
            pstmt.setBoolean(7,is_available);
            pstmt.setString(8,license_plate);
            pstmt.setString(9,car);
            pstmt.setString(10,location);
            pstmt.setBoolean(11,complete_ride);
            pstmt.setInt(12,payment_id);

            pstmt.executeUpdate();
            System.out.println("Driver data injected");

    }catch(SQLException e){
            System.out.println(e.getMessage());

    }
    }

    public static void main(String[] args) {
        connection();  // Test connection

        try {
            Insert.insertDriver(1, "Sifiso", "Jonga", "+27 81 773 4590", "sifisoj@gmail.com", "",
                    true, "CA 349 821", "Toyota Corolla", "Cape Town", false, 101);
        } catch (SQLException e) {
            System.out.println("Insertion error: " + e.getMessage());
        }
    }


}
