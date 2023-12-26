package model;

public class FreeRoom extends Room{

    private Double price;

    public FreeRoom(int id, String roomNumber, Double price, RoomType roomType){
        super(id, roomNumber, price, roomType);
        this.price = 0.0;
    }
}
