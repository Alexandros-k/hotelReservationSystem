package model;

import java.util.Date;

public class Reservation {
    private int id;
    private Customer customer;
    private IRoom room;
    private Date checkinDate;
    private Date checkoutDate;

    public Reservation(int id, Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customer=" + customer +
                ", room=" + room +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
