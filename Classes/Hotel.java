package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hotel {
    private String name;
    private List<Floor> floors = new ArrayList<>();
    private static int clientsNotHoused = 0;
    private static int clientsHoused = 0;

    /**
     * sets up all floors and room for the hotel
     * @param name Hotel name
     * @param floorTotalNum Hotel floors
     * @param roomTotalNum hotel rooms in each floor
     */
    public Hotel(String name, int floorTotalNum, int roomTotalNum){
        setName(name);
        setFloors(floorTotalNum);
        for (Floor floor:floors) {
            setFloorRooms(floor.getNumber(), roomTotalNum);
        }
    }

    /**
     *
     * @return Total clients who booked a room during the simulation
     */
    public static int getClientsHoused() {
        return clientsHoused;
    }

    /**
     *
     * @return Total clients whose booking was turned down due to the rooms requested being unavailable
     */
    public static int getClientsNotHoused() {
        return clientsNotHoused;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fills the List of Floors with Floor objects
     * @param floorTotalNum floors in the hotel
     */
    public void setFloors(int floorTotalNum) {
        for(int i=0; i<floorTotalNum; i++){
            Floor floor = new Floor(i);
            this.floors.add(floor);
        }
    }

    public Floor getFloor(int floorNum){
        return floors.get(floorNum);
    }

    /**
     * Generates requested rooms for each Floor
     * @param floorNum Floors in the hotel
     * @param roomTotalNum Rooms on each Floor
     */
    public void setFloorRooms(int floorNum, int roomTotalNum){
        Random R = new Random();
        for(int i=0; i<roomTotalNum; i++) {
            switch (floorNum){
                case 0:
                    getFloor(floorNum).setRoomData(100+i, R.nextInt(2) + 2, R.nextInt(2) + 1);
                    break;
                case 1:
                    getFloor(floorNum).setRoomData(200+i, R.nextInt(2) + 2, R.nextInt(2) + 1);
                    break;
                case 2:
                    getFloor(floorNum).setRoomData(300+i, R.nextInt(2) + 2, R.nextInt(2) + 1);
                    break;
                case 3:
                    getFloor(floorNum).setRoomData(400+i, R.nextInt(2) + 2, R.nextInt(2) + 1);
                    break;
            }
         }
    }

    /**
     * Prints the details of each reservation request and searches for available Room (1/3 requests is without a specific floor declaration)
     * @param client Client object with a Reservation object request on hold
     * @return       Available Room found returns, or null if none is available
     */
    public Room searchRoom(Client client){
        Random r = new Random();
        Room avRoom;
        if(r.nextInt(3) == 0){
            //Random R = new Random();
            System.out.println("Searching Room for Client : "+(client.getClientId()+1));
            System.out.println("Requesting : "+client.getReservation().getBeds()+" Beds, "+client.getReservation().getTypeName(client.getReservation().getType())+" Type, for "+client.getReservation().getDays()+" Days (floor unspecified)");
            avRoom = floors.get(r.nextInt(floors.size())).searchRoom(client.getReservation().getBeds(),client.getReservation().getType());
        }
        else{
            System.out.println("Searching Room for Client : "+(client.getClientId()+1));
            System.out.println("Requesting : "+client.getReservation().getBeds()+" Beds, of "+client.getReservation().getTypeName(client.getReservation().getType())+" Type, on the "+(client.getReservation().getFloor()+1)+" Floor, for "+client.getReservation().getDays()+" Days");
            avRoom = floors.get(client.getReservation().getFloor()).searchRoom(client.getReservation().getBeds(), client.getReservation().getType());
        }
        return avRoom;
    }

    /**
     * Using these parameters a specific Room is Booked for the requested days
     * @param room Room available to be booked
     * @param client Client with Reservation ready to be completed
     * @param startDate Day in simulation when the reservation takes place and the checkoutdate is generated for later use
     */
    public void bookRoom(Room room, Client client, int startDate){
        room.checkIn(client);
        room.setCheckOutDate(startDate);
    }

    /**
     * Checks if any of the occupied Room's checkout date is today and if it is, the Room becomes vacant to be used lated in the simulation
     * Also prints the total checkout sor each Floor
     * @param today Current day in the simulation
     */
    public void emptyRooms(int today){
        List<Integer> temp = new ArrayList<>();
        System.out.println(" ");
        for (Floor floor: floors) {
            System.out.printf("Floor "+(floor.getNumber()+1)+" Checkouts : ");
            for (Room room: floor.getRooms()) {
                if(room.getCheckOutDate() == today){
                    room.checkOut();
                    temp.add(room.getNumber());
                }
            }
            if (temp.size()==0) {
                System.out.printf("No checkouts today.");
            }
            else{
                System.out.printf("Rooms -> ");
                for (Integer t:temp){
                    System.out.printf(t+" ");
                }
                temp.clear();
            }System.out.println(" ");
        }
        System.out.println("");
    }

    /**
     * If an availability is found, the Clients reservation is occurring, else the Client leaves
     * @param cli Client requesting a Reservation
     * @param startDate current simulation day
     */
    public void serviceClient(Client cli, int startDate){
        Room room = searchRoom(cli);
        if(room == null) {
            System.out.println("No room available.. Client left..\n");
            clientsNotHoused++;
        }
        else{
            bookRoom(room, cli, startDate);
            System.out.println("Booked Room : "+room.getNumber()+" until day "+room.getCheckOutDate()+"\n");
            clientsHoused++;
        }
    }

    /**
     * Available Rooms at the current simulation day
     */
    public void getInventory(){
        for (Floor floor:floors) {
            System.out.println("Floor "+(floor.getNumber()+1)+" Available Rooms : "+floor.getAvailableRooms()+" / "+floor.getRooms().size());
        }
    }


}
