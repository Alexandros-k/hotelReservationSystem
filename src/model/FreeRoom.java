package model;

public class FreeRoom extends Room{

    private Double price;

    public FreeRoom(String roomNumber, Double price, RoomType roomType){
        super(roomNumber, price, roomType);
        this.price = 0.0;
    }
}
