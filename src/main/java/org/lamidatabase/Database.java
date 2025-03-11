package org.lamidatabase;
//Potential tables:
//Users: id, name + surname, username, email, phone number, location
//Trip history: id,user_id,transaction_history_id,timestamp
//Transaction history: id,user_id, action,validity, bank, account_number, amount
//Drivers: id, name + surname, phone number, email, password,is_available,license_plate,car,complete_ride,payment_id
//Payments: id, user_id,driver_id,payment_type,timestamp,amount,transaction_id

//Taxi: id, driver_id,license_plate_id, car_id,fare,rank_id,capacity
//Rank: id, location, route_id
//Routes: id, start_location, end_location, stops, main_road, fare, duration

//Uber: id, driver_id,license_plate_id, car_id,fare,rank_id,ride_status,payment_status,ride_type,estimated_time,distance,

//Luxury: id,preferred_driver (Bool), driver_id,license_plate_id, car_id, on_board_features(list),added_security, membership_status,fare

public class Database {
}
