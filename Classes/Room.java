package Classes;

public class Room {
    private int number;
    private int numBeds;
    private int type;
    private Client Cli;
    private int checkOutDate;


    public Room(int number, int numBeds, int type){
        setNumber(number);
        setNumBeds(numBeds);
        setType(type);
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    /**
     * getTypeName() takes the int type of the Room Class Object and returns a String describing the Room's type,
     * which is later used in the sout method.
     * @param type Room object type
     * @return     Description of said type
     */
    public String getTypeName(int type){
        return (type == 1)?"STD":"SUP";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = (type>1)?2:1;
    }

    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * setCheckOutDate() calculates the day the specific client leaves so that the room becomes vacant again
     * takes the clients reservation days and adds them to the daybooked and calcutes the day the client leaves
     * @param dayBooked The current day in the simulation loop
     */
    public void setCheckOutDate(int dayBooked) {
        this.checkOutDate = dayBooked + getClient().getReservation().getDays();
    }

    public Client getClient() {
        return Cli;
    }

    public void setClient(Client cli) {
        Cli = cli;
    }


    public void checkIn(Client Cli){
        setClient(Cli);
    }

    public void checkOut(){
        setClient(null);
    }

    /**
     * Check if the Room is occupied by a Client
     * @return true if Room is empty or false otherwise
     */
    public Boolean isEmpty(){
        return getClient() == null;
    }


}
