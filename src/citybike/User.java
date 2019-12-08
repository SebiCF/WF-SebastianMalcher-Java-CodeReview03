package citybike;

import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private int userID;
    private static int idCount = 0;
    private String name;
    private String surName;
    private Bike currentlyRentedBike;
    private Rent rent;
    private Menu userMenu;

    User(String name, String surName) {
        this.userID = idCount++;
        this.name = name;
        this.surName = surName;
        this.userMenu = new Menu(this);
    }

    void accessUserMenu(HashMap<Integer, Station> stationMap) {
        this.getUserMenu().menu(stationMap);
    }

    void rentBike(Bike bikeToRent, HashMap<Integer, Station> stationMap, int chosenStation) {
        if (bikeToRent.getState() == Bike.bikeState.CanBeRented) {
            this.currentlyRentedBike = bikeToRent;
            for (int i = 0; i < stationMap.get(chosenStation).getStoredBikes().length; i++) {
                if (stationMap.get(chosenStation).getStoredBikes()[i].getBikeID() == bikeToRent.getBikeID()) {
                    stationMap.get(chosenStation).getStoredBikes()[i] = null;
                    break;
                } else if (i == stationMap.get(chosenStation).getStoredBikes().length - 1) {
                    System.out.println("The bike you chose does not seem to be in this station. Please try another one!");
                }
            }
            bikeToRent.setState(Bike.bikeState.CanNotBeRented);
            this.rent = new Rent(bikeToRent.getBikeID());
            System.out.println("\nYou rented the bike with an ID of " + bikeToRent.getBikeID() + " and a color of " + bikeToRent.getBikeColor());
        } else {
            System.out.println("Bike is currently unavailable. Please try another one!");
            System.out.println("Press any number to continue.");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            this.getUserMenu().selectRentStation(stationMap);
        }
    }

    void returnBike(HashMap<Integer, Station> stationMap, int returnStation) {
        if (this.currentlyRentedBike != null) {
            for (int i = 0; i < stationMap.get(returnStation).getStoredBikes().length; i++) {
                if (stationMap.get(returnStation).getStoredBikes()[i] == null) {
                    this.currentlyRentedBike.setState(Bike.bikeState.CanBeRented);
                    stationMap.get(returnStation).getStoredBikes()[i] = this.currentlyRentedBike;
                    this.currentlyRentedBike = null;
                    this.rent.setRentEnd(new GregorianCalendar());
                    System.out.println("You successfully returned your bike to " + stationMap.get(returnStation).getLocation() + this.getRent().rentDurationToString());
                    break;
                } else if (i == stationMap.get(returnStation).getStoredBikes().length - 1) {
                    System.out.println("This station is already full! Please try another one!");
                    System.out.println("Press any number to continue.");
                    Scanner sc = new Scanner(System.in);
                    String input = sc.next();
                    this.getUserMenu().selectReturnStation(stationMap);
                }
            }
        } else System.out.println("You don't have a bike rented at the moment!");
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

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Menu getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(Menu userMenu) {
        this.userMenu = userMenu;
    }

    @Override
    public String toString() {
        String ifRentedBike = (this.getCurrentlyRentedBike() != null) ?
                "Rented bike details: " +
                        "\nID: " + getCurrentlyRentedBike().getBikeID() +
                        "\nColor: " + getCurrentlyRentedBike().getBikeColor() +
                        "\nRent Start: " + getRent().getRentStart().toZonedDateTime()
                        .format(DateTimeFormatter.ofPattern("d MMM uuuu kk:mm:ss"))
                : "No bike rented";

        return "------------------------------------------------------" +
                "\nName: " + name + "   Surname: " + surName +
                "\nUserID: " + userID +
                "\n" + ifRentedBike +
                "\n------------------------------------------------------"
                ;
    }

    public String currentlyRentedBikeToString() {
        String rentedBike = (this.getCurrentlyRentedBike() != null) ? "one bike " : "zero bikes ";
        String ifRentedBike = (this.getCurrentlyRentedBike() != null) ?
                "Rented bike details: " +
                        "\nID: " + getCurrentlyRentedBike().getBikeID() +
                        "\nColor: " + getCurrentlyRentedBike().getBikeColor()
                : "Please rent one of our awesome bikes!";


        return "You, " + this.getName() + " " + this.getSurName() + " (ID: '" + this.getUserID() + "')," +
                " have " + rentedBike + "rented at the moment. " +
                "\n" + ifRentedBike;
    }
}
