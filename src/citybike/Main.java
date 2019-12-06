package citybike;

import java.util.HashMap;

public class Main {
    private static HashMap<Integer, Bike.bikeState> bikeMap;
    private static HashMap<Integer, Station> stationMap;
    private static Bike[] bikeArray;
    private static User[] userArray;

    public static void main(String[] args) {

        createBikes();
        createStations();
        createUsers();
//        add bikes to stations
        for(int i = 0; i < bikeArray.length; i++){
            if(i < 3) stationMap.get(1).addBike(bikeArray[i]);
            else if(i < 7) stationMap.get(2).addBike(bikeArray[i]);
            else stationMap.get(3).addBike(bikeArray[i]);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < stationMap.get(i+1).getStoredBikes().length; j++) {
                System.out.println(stationMap.get(i+1).getStoredBikes()[j]);
            }
        }

        userArray[0].rentBike(bikeArray[0], stationMap, 1, bikeMap);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < stationMap.get(i+1).getStoredBikes().length; j++) {
                System.out.println(stationMap.get(i+1).getStoredBikes()[j]);
            }
        }
    }

    private static void createBikes(){

        bikeArray = new Bike[8];
        bikeArray[0] = new Bike("red", Bike.bikeState.CanNotBeRented);
        bikeArray[1] = new Bike("blue", Bike.bikeState.CanBeRented);
        bikeArray[2] = new Bike("green", Bike.bikeState.Discarded);
        bikeArray[3] = new Bike("pink", Bike.bikeState.InService);
        bikeArray[4] = new Bike("purple", Bike.bikeState.CanBeRented);
        bikeArray[5] = new Bike("black", Bike.bikeState.CanBeRented);
        bikeArray[6] = new Bike("yellow", Bike.bikeState.InService);
        bikeArray[7] = new Bike("orange", Bike.bikeState.CanNotBeRented);

        bikeMap = new HashMap<>();
        for(Bike bike:bikeArray){
            bikeMap.put(bike.getBikeID(), bike.getState());
        }
    }

    private static void createStations() {
        Station station1 = new Station("Karlsplatz");
        Station station2 = new Station("Kettenbrückengasse");
        Station station3 = new Station("Pilgramgasse");

        stationMap  = new HashMap<>();
        stationMap.put(station1.getStationID(), station1);
        stationMap.put(station2.getStationID(), station2);
        stationMap.put(station3.getStationID(), station3);
    }

    private static void createUsers(){
        userArray = new User[4];
        userArray[0] = new User("Mark","Opal");
        userArray[1] = new User("John","James");
        userArray[2] = new User("Robin","Litter");
        userArray[3] = new User("Oliver","Rock");
    }
}
