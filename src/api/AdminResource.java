package api;

import model.Customer;
import model.IRoom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {

    static List<IRoom> rooms = new ArrayList<IRoom>();

    private static AdminResource instance;

    public Customer getCustomer(String email){
        return null;
    }

    public void addRoom(List<IRoom> rooms){}

    public Collection<IRoom> getAllRooms(){
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return null;
    }

    public void displayAllReservations(){}
}
