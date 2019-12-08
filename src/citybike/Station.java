package citybike;

import java.util.Arrays;

public class Station {
    private int stationID;
    private static int stationCount = 1;
    private String location;
    private Bike[] storedBikes;

    Station(String location) {
        this.stationID = stationCount++;
        this.location = location;
        this.storedBikes = new Bike[5];
    }

    void addBike(Bike thisBike) {
        for (int i = 0; i < storedBikes.length; i++) {
            if (storedBikes[i] == null) {
                this.storedBikes[i] = thisBike;
                break;
            } else if (i == storedBikes.length - 1) {
                System.out.println("This station is already full. Please try another one!");
            }
        }
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Bike[] getStoredBikes() {
        return storedBikes;
    }

    public void setStoredBikes(Bike[] storedBikes) {
        this.storedBikes = storedBikes;
    }

    @Override
    public String toString() {
        int freeSlots = 0;
        StringBuilder bikes = new StringBuilder();
        for (int i = 0; i < storedBikes.length; i++) {
            if (storedBikes[i] == null) {
                bikes.append("\n------------------------------------------------------").append("\nBike slot: ").append(i + 1).append("\nEmpty");
                freeSlots++;
            } else {
                bikes.append("\n------------------------------------------------------").append("\nBike slot: ").append(i + 1).append("\nBike ID: ").append(storedBikes[i].getBikeID()).append("    Bike Color: ").append(storedBikes[i].getBikeColor()).append("\nBike Status: ").append(storedBikes[i].getState());
            }
        }
        return "******************************************************" +
                "\nStation: " + location +
                "\nEmpty slots: " + freeSlots +
                "\nStored bikes:" + bikes +
                "\n******************************************************";
    }
}
