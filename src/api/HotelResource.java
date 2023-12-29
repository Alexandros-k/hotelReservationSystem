package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

  CustomerService customerService = CustomerService.getInstance();
  ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkouOutDate){
        Customer customer = getCustomer(customerEmail);
        Reservation reservation = reservationService.reserveARoom(customer, room, checkInDate, checkouOutDate);
        return reservation;
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        Customer customer = customerService.getCustomer(customerEmail);
        Collection<Reservation> customerReservations = reservationService.getCustomerReservation(customer);
        return customerReservations;
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        Collection<IRoom> rooms = reservationService.findRooms(checkIn, checkOut);
        return rooms;
    }
}
