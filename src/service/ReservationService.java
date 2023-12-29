package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService;

    private static List<Reservation> reservations =  new ArrayList<Reservation>();

    private static List<IRoom> rooms = new ArrayList<IRoom>();;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        reservationService = new ReservationService();
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkingDate, Date checkoutDate) {
        Reservation reservation = new Reservation(customer, room, checkingDate, checkoutDate);
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }else {
            throw new IllegalStateException ("this reservation already exists");
        }
        return reservation;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        ArrayList<IRoom> freeRooms = new ArrayList<IRoom>(rooms);
        for (Reservation reservation : reservations) {
            if (reservation.getCheckinDate().equals(checkInDate) &&
                reservation.getCheckoutDate().equals(checkOutDate)) {
                    freeRooms.remove(reservation.getRoom());
            }
        }
        return freeRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        ArrayList<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation() {
        reservations.forEach(reservation -> System.out.println(reservation));
    }

}
