package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkinDate;
    private Date checkoutDate;

    public Reservation(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(room, that.room) &&
                Objects.equals(checkinDate, that.checkinDate) &&
                Objects.equals(checkoutDate, that.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkinDate, checkoutDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", customer=" + customer +
                ", room=" + room +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
