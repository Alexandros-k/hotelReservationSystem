package test;

import model.Customer;
import model.Room;
import model.RoomType;
import service.ReservationService;

public class Tester {
    public static void main(String[] args) {
      /*  Customer customer = new Customer("first", "second", "j@domain.com");
        Customer customer1 = new Customer("first", "second", "email");
*/
        Room room7 = new Room("008",100.0, RoomType.SINGLE);
        Room room8 = new Room("008",100.0, RoomType.DOUBLE);

        ReservationService instance = ReservationService.getInstance();
        instance.addRoom(room7);
        instance.addRoom(room8);

  //      System.out.println(customer1);
    }
}
