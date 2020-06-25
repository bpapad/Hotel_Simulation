package Classes;

public class Reservation {
    private int beds;
    private int type;
    private int floor;
    private int days;


    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "beds=" + beds +
                ", type=" + type +
                ", floor=" + floor +
                ", days=" + days +
                '}';
    }

}
