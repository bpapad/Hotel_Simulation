package Classes;

import java.util.Random;

public class Client {
    private static int id = 0 ;
    private Reservation Res;
    private int clientId;

    public Client(){
        clientId = id++;
    }

    public static int getId() {
        return id;
    }


    public int getClientId() {
        return clientId;
    }

    public Reservation getReservation() {
        return Res;
    }

    public void setReservation(Reservation Res){
        this.Res = Res;
    }

    /**
     * Using random values, each in its own scope, decideBooking() sets up a Reservation Class Object to
     * unique to the client, which is later used to request a booking from the Hotel Class.
     * @param totalFloors Number of floors the hotel has
     * @param roomTypes   Number of room types
     */
    public void decideBooking(int totalFloors, int roomTypes){
        Random rand = new Random();
        Reservation reservation = new Reservation();
        reservation.setDays(rand.nextInt(10)+5);
        reservation.setBeds(rand.nextInt(2)+2);
        reservation.setType(rand.nextInt(roomTypes)+1);
        reservation.setFloor(rand.nextInt(totalFloors));
        setReservation(reservation);
    }


    public void decideBooking(Reservation reservation){
        setReservation(reservation);
    }


}
