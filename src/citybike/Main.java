package citybike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<Integer, Bike> bikeMap;
    private static HashMap<Integer, Station> stationMap;
    private static ArrayList<User> userArray;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        createBikes();
        createStations();
        createUsers();
        initProgram();
    }

    private static void initProgram() {
        System.out.println("Enter 1 for renting process as instructed in the CodeReview, 2 for a little bonus, 0 to quit. (do 1st one first please or it might bug out :P)");
        boolean nextMenu = false;
        while (!nextMenu) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    codeReviewInstructions();
                    initProgram();
                case 2:
                    userSelection();
                    nextMenu = true;
                    break;
                case 0:
                    nextMenu = true;
                    break;
                default:
                    System.out.println("Please enter a correct input.");
            }
        }
    }

    static void userSelection() {
        System.out.println("Would you like to create a User or select an already created one? \nCreate New User: 1\nSelect User: 2\nGo back: 0");
        boolean nextMenu = false;
        while (!nextMenu) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    createUser();
                    nextMenu = true;
                    break;
                case 2:
                    selectUser();
                    nextMenu = true;
                    break;
                case 0:
                    initProgram();
                    nextMenu = true;
                    break;
                default:
                    System.out.println("Please enter a correct input.");
            }
        }
    }

    private static void createUser() {
        boolean nextMenu = false;
        boolean inputSurName = false;
        String name;
        String surName;
        while (!nextMenu) {
            System.out.println("Please enter name. Enter 0 to go back.");
            name = sc.next();
            if (name.equals("0")) {
                userSelection();
                nextMenu = true;
                break;
            }
            try {
                double d = Double.parseDouble(name);
                System.out.println("Numerical input detected, please use another name");
            } catch (NumberFormatException nfe) {
                inputSurName = true;
            }
            while (inputSurName) {
                System.out.println("Please enter SurName. Enter 0 to go back.");
                surName = sc.next();
                if (surName.equals("0")) {
                    createUser();
                    nextMenu = true;
                    inputSurName = false;
                    break;
                }
                try {
                    double d = Double.parseDouble(surName);
                    System.out.println("Numerical input detected, please use another name");
                } catch (NumberFormatException nfe) {
                    userArray.add(new User(name, surName));
                    userArray.get(userArray.size() - 1).accessUserMenu(stationMap);
                    nextMenu = true;
                    inputSurName = false;
                    break;
                }
            }
        }
    }

    private static void selectUser() {
        System.out.println("Please select a user. 0 to go back");
        for (User user : userArray) {
            System.out.println("\nEnter " + (user.getUserID() + 1) + " to log in with ");
            System.out.println(user.toString());
        }
        boolean nextMenu = false;
        while (!nextMenu) {
            int choice = sc.nextInt();
            if (choice == 0) {
                userSelection();
                nextMenu = true;
                break;
            }
            for (int i = 0; i < userArray.size(); i++) {
                if ((choice - 1) == userArray.get(i).getUserID()) {
                    userArray.get(i).accessUserMenu(stationMap);
                    nextMenu = true;
                    break;
                } else if (i == (userArray.size() - 1)) {
                    System.out.println("Please enter a valid input. ID is not correct.");
                }
            }
        }
    }

    private static void codeReviewInstructions() {
        System.out.println(userArray.get(0).toString());
        System.out.println(stationMap.get(1).toString());
        userArray.get(0).rentBike(stationMap.get(1).getStoredBikes()[0], stationMap, 1);
        System.out.println(userArray.get(0).toString());
        System.out.println(stationMap.get(1).toString());
        userArray.get(0).returnBike(stationMap, 2);
        System.out.println(userArray.get(0).toString());
        System.out.println(stationMap.get(2).toString());

        //reset arrays, this method fails otherwise
        userArray.get(0).rentBike(stationMap.get(2).getStoredBikes()[4], stationMap, 2);
        userArray.get(0).returnBike(stationMap, 1);
    }

    private static void createBikes() {
        ArrayList<Bike> bikeArray = new ArrayList<>();
        bikeArray.add(new Bike("red", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("blue", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("green", Bike.bikeState.Discarded));
        bikeArray.add(new Bike("pink", Bike.bikeState.InService));
        bikeArray.add(new Bike("purple", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("black", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("yellow", Bike.bikeState.Damaged));
        bikeArray.add(new Bike("green", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("blue", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("orange", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("purple", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("pink", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("black", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("red", Bike.bikeState.Damaged));
        bikeArray.add(new Bike("orange", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("blue", Bike.bikeState.Damaged));
        bikeArray.add(new Bike("yellow", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("red", Bike.bikeState.Discarded));
        bikeArray.add(new Bike("black", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("purple", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("red", Bike.bikeState.Damaged));
        bikeArray.add(new Bike("black", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("purple", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("green", Bike.bikeState.CanBeRented));
        bikeArray.add(new Bike("blue", Bike.bikeState.CanBeRented));

        bikeMap = new HashMap<>();
        for (Bike bike : bikeArray) {
            bikeMap.put(bike.getBikeID(), bike);
        }
    }

    private static void createStations() {

        Station station1 = new Station("Karlsplatz");
        Station station2 = new Station("Kettenbrückengasse");
        Station station3 = new Station("Pilgramgasse");
        Station station4 = new Station("Margaretengürtel");
        Station station5 = new Station("Längenfeldgasse");


        stationMap = new HashMap<>();
        stationMap.put(station1.getStationID(), station1);
        stationMap.put(station2.getStationID(), station2);
        stationMap.put(station3.getStationID(), station3);
        stationMap.put(station4.getStationID(), station4);
        stationMap.put(station5.getStationID(), station5);


        //        add bikes to stations
        for (int i = 0; i < stationMap.size(); i++) {
            for (int j = 1; j <= 5; j++) {
                if(j != 5){
                    stationMap.get(i+1).addBike(bikeMap.get(j+(i*5)));
                }
            }
        }
    }

    private static void createUsers() {

        userArray = new ArrayList<>();
        userArray.add(new User("Mark", "Opal"));
        userArray.add(new User("John", "James"));
        userArray.add(new User("Robin", "Litter"));
        userArray.add(new User("Oliver", "Rock"));
    }
}
