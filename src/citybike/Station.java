package citybike;

public class Station {
    private int stationID;
    private static int stationCount = 1;
    private String location;
    private Bike[] storedBikes;

    public Station(String location) {
        this.stationID = stationCount++;
        this.location = location;
        this.storedBikes = new Bike[5];
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

    public void addBike(Bike thisBike) {
        for (int i = 0; i < storedBikes.length; i++) {
            if (storedBikes[i] == null) {
                this.storedBikes[i] = thisBike;
                break;
            } else if(i == storedBikes.length-1) {
                System.out.println("This station seems to be full! Please try another one!");
            }
        }
    }
}
