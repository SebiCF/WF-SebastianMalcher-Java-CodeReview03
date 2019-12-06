package citybike;

public class Bike {

    public enum bikeState {
        CanBeRented, CanNotBeRented, InService, Discarded
    }

    private static int idCount = 1;
    private int bikeID;
    private String bikeColor;
    private bikeState state;

    Bike(String bikeColor, bikeState state) {

        this.bikeID = idCount++;
        this.bikeColor = bikeColor;
        this.state = state;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Bike.idCount = idCount;
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getBikeColor() {
        return bikeColor;
    }

    public void setBikeColor(String bikeColor) {
        this.bikeColor = bikeColor;
    }

    public bikeState getState() {
        return state;
    }

    public void setState(bikeState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getBikeID()) + " " + getBikeColor();
    }
}
