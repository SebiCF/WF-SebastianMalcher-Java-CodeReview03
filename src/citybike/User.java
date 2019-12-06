package citybike;

import java.util.HashMap;

public class User {
    private int userID;
    private static int idCount = 0;
    private String name;
    private String surName;
    private Bike currentlyRentedBike;

    public User(String name, String surName) {
        this.userID = idCount++;
        this.name = name;
        this.surName = surName;
    }

    public void rentBike(Bike bikeToRent, HashMap<Integer, Station> stationMap, int chosenStation, HashMap<Integer, Bike.bikeState> bikeMap) {
        if (bikeToRent.getState() == Bike.bikeState.CanBeRented) {
            this.currentlyRentedBike = bikeToRent;
            for (int i = 0; i < stationMap.get(chosenStation).getStoredBikes().length ; i++) {
                if(stationMap.get(chosenStation).getStoredBikes()[i].getBikeID() == bikeToRent.getBikeID()){
                    stationMap.get(chosenStation).getStoredBikes()[i] = null;
                }
            }
            bikeToRent.setState(Bike.bikeState.CanNotBeRented);
            System.out.println("You rented the bike with an ID of "+ bikeToRent.getBikeID() + " and a color of " + bikeToRent.getBikeColor());
        }
        else System.out.println("Bike is currently unavailable, try another one");
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        User.idCount = idCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Bike getCurrentlyRentedBike() {
        return currentlyRentedBike;
    }

    public void setCurrentlyRentedBike(Bike currentlyRentedBike) {
        this.currentlyRentedBike = currentlyRentedBike;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", currentlyRentedBike=" + currentlyRentedBike +
                '}';
    }
}
