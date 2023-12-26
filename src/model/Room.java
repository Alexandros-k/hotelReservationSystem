package model;

public class Room implements IRoom  {

    private int id;
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(int id, String roomNumber, Double price, RoomType roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public String getRoomPrice() {
        return null;
    }

    @Override
    public String getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }

    enum RoomType {
        SINGLE,
        DOUBLE
    }
}
