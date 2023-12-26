package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class ReservationService {

    private static ReservationService reservationService;

    private static Collection<Reservation> reservations;

    public void addRoom(IRoom room){}

    public IRoom getARoom(String roomId){
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkingDate, Date checkoutDate){
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        return null;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return null;
    }

    public void printAllReservation(){}

}
