package citybike;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private HashMap<Integer, Station> stationMap = new HashMap<>();
    private User user = new User("´Mikey","Klap");
    private Station station = new Station("Pilgramgasse");
    private Bike bike = new Bike("red", Bike.bikeState.CanBeRented);

    @org.junit.jupiter.api.Test
    void rentBike() {
        stationMap.put(station.getStationID(), station);
        station.addBike(bike);
        user.rentBike(bike, stationMap, 1);

        assertNotNull(user.getCurrentlyRentedBike());
        assertNull(stationMap.get(1).getStoredBikes()[0]);
    }

    @org.junit.jupiter.api.Test
    void returnBike() {
        rentBike();

        user.returnBike(stationMap, 1);

        assertNull(user.getCurrentlyRentedBike());
        assertNotNull(stationMap.get(1).getStoredBikes()[0]);
    }
}