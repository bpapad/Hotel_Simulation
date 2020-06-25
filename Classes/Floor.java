package Classes;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int number;
    private List<Room> rooms = new ArrayList<>();

    public Floor(int number){
        setNumber(number);
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Room> getRooms(){
        return rooms;
    }

    /**
     * Sets Up a Room object using given parameters and adds it to the Room List of the specific Floor object
     * @param roomNum Room number
     * @param bedNum  Number of Beds
     * @param type    Room type
     */
    public void setRoomData(int roomNum, int bedNum, int type){
        Room R = new Room(roomNum, bedNum, type);
        rooms.add(R);
    }

    /**
     * Sets Up multiple Room object using given parameters and adds them to the Room List of the specific Floor object
     * @param minRoomNum Min Range
     * @param maxRoomNum Max Range
     * @param bedNum     Number of Beds
     * @param type       Room type
     */
    public void setRoomData(int minRoomNum, int maxRoomNum, int bedNum, int type){
        for(int i = minRoomNum; i < maxRoomNum+1; i++){
            Room R = new Room(i, bedNum, type);
            rooms.add(R);
        }
    }

    /**
     * Searches for a vacant Room having the given parameters as beds and type
     * @param numBed number of beds required
     * @param type   type required
     * @return returns a suitable Room or NULL
     */
    public Room searchRoom(int numBed, int type){
        List<Room> AvailableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if(room.getNumBeds() == numBed && room.getType() == type && room.isEmpty()){
                AvailableRooms.add(room);
                break;
            }
        }
        if(AvailableRooms.size()>0){
            return AvailableRooms.get(0);
        }
        else{
            return null;
        }
    }

    /**
     * empties all rooms whose clients checkout the given day
     * @param today current simulation day
     */
    public void emptyRooms(int today){
        for (Room room : rooms) {
            if(room.getCheckOutDate() >= today){
                room.setClient(null);
            }
        }
    }

    /**
     * Returns the Rooms index in the Floor's Room List using its number as a parameter
     * @param roomNum Room Number
     * @return        Room index in the floor it exists
     */
    public int roomIndexFromNumber(int roomNum){
        Room R = null;
        for(Room room : rooms){
            if(room.getNumber() == roomNum){
                R = room;
                break;
            }
        }
        if(R != null){
            return rooms.indexOf(R);
        }
        else {
            return -1;
        }
    }

    /**
     * Counts all remaining vacant Rooms in this Floor
     * @return number of vacant Rooms in this Floor
     */
    public int getAvailableRooms(){
        int count=0;
        for (Room room:rooms) {
            if(room.isEmpty()) count++;
        }
        return count;
    }

}
