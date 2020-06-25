import Classes.Client;
import Classes.Hotel;
import Classes.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    final static int NUM_FLOORS = 4;
    final static int NUM_FLOOR_ROOMS = 100;
    final static int NUM_ROOM_TYPES = 2;


    public static Hotel createHotel(String name){
        return new Hotel(name, NUM_FLOORS, NUM_FLOOR_ROOMS);
    }

    public static void runSimulation(Hotel myHotel){
        int today = 1;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert number of DAYS to simulate (recommended > 100) : ");
        int simulationDays = scanner.nextInt();
        int daysSimulated = simulationDays;
        do{
            System.out.println("DAY "+today+" : ===================================================================\n");
            myHotel.emptyRooms(today);
            List<Client> Clients = new ArrayList<>();
            int incClients = rand.nextInt(50)+10;
            for(int i=0; i<incClients; i++){
                Client client = new Client();
                client.decideBooking(NUM_FLOORS, NUM_ROOM_TYPES);
                Clients.add(client);
            }
            for (Client client:Clients) {
                myHotel.serviceClient(client,today);
            }
            myHotel.getInventory();
            today++;
            simulationDays--;
            System.out.println("\nToday's Clients : "+incClients);
            System.out.println("");
        }while(simulationDays>0);
        System.out.println("Days Simulated : "+daysSimulated);
        System.out.println("Total Clientele : "+Client.getId()+"\nClients Housed : "+Hotel.getClientsHoused()+"\nClients Not Housed : "+Hotel.getClientsNotHoused());

    }





    public static void main(String[] args) throws Exception{
        runSimulation(createHotel("Sunrise"));
    }

}

