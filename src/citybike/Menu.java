package citybike;

import java.util.HashMap;
import java.util.Scanner;

class Menu {
    private static Scanner sc = new Scanner(System.in);
    private User user;

    Menu(User user) {
        this.user = user;
    }

    void menu(HashMap<Integer, Station> stationMap) {
        System.out.println("\nPlease select an action.");
        System.out.println("Enter 1 to rent a bike.");
        System.out.println("Enter 2 to return a bike.");
        System.out.println("Enter 3 to see current user information");
        System.out.println("Enter 0 to go back.");
        boolean nextMenu = false;
        while (!nextMenu) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (this.user.getCurrentlyRentedBike() != null) {
                        System.out.println("\nYou already have a bike rented. We currently only allow 1 bike per person.");
                        System.out.println("Press any number to continue.");
                        String input = sc.next();
                        menu(stationMap);
                        nextMenu = true;
                        break;
                    } else {
                        selectRentStation(stationMap);
                        nextMenu = true;
                        break;
                    }
                case 2:
                    selectReturnStation(stationMap);
                    nextMenu = true;
                    break;
                case 3:
                    showUserInformation(stationMap);
                    nextMenu = true;
                    break;
                case 0:
                    Main.userSelection();
                    nextMenu = true;
                    break;
                default:
                    System.out.println("Please select a valid action.");
            }
        }
    }

    void selectRentStation(HashMap<Integer, Station> stationMap) {
        boolean nextMenu = false;
        System.out.println("\nPlease enter the station you'd like to rent from.");
        while (!nextMenu) {
            for (int i = 0; i < stationMap.size(); i++) {
                System.out.println("Enter " + (i + 1) + " for " + stationMap.get(i + 1).getLocation());
                if (i == stationMap.size() - 1) {
                    System.out.println("Enter 0 to go back.");
                }
            }
            int choice = sc.nextInt();
            if (choice == 0) {
                menu(stationMap);
                nextMenu = true;
                break;
            }
            for (int i = 0; i < stationMap.size(); i++) {
                if (choice == stationMap.get(i + 1).getStationID()) {
                    System.out.println(stationMap.get(i + 1).toString());
                    selectBike(stationMap, (i + 1));
                    nextMenu = true;
                    break;
                } else if (i == (stationMap.size() - 1)) {
                    System.out.println("Please select a valid station.");
                }
            }
        }
    }

    private void selectBike(HashMap<Integer, Station> stationMap, int stationNumber) {
        System.out.println("Please enter the slot number of the bike you want to rent. Enter 0 to go back.");
        boolean nextMenu = false;
        while (!nextMenu) {
            int choice = sc.nextInt();
            if (choice == 0) {
                selectRentStation(stationMap);
                nextMenu = true;
                break;
            } else if (stationMap.get(stationNumber).getStoredBikes()[choice - 1] != null) {
                this.user.rentBike(stationMap.get(stationNumber).getStoredBikes()[choice - 1], stationMap, stationNumber);
                System.out.println("Press any number to continue.");
                String input = sc.next();
                menu(stationMap);
                nextMenu = true;
                break;
            } else {
                System.out.println("Please select a valid bike.");
            }
        }
    }

    void selectReturnStation(HashMap<Integer, Station> stationMap) {
        if (this.user.getCurrentlyRentedBike() == null) {
            System.out.println("You don't have a bike rented at the moment!");
            menu(stationMap);
            return;
        }
        boolean nextMenu = false;
        System.out.println("Please enter the station you'd like to return your bike to.");
        while (!nextMenu) {
            for (int i = 0; i < stationMap.size(); i++) {
                System.out.println("Enter " + (i + 1) + " for " + stationMap.get(i + 1).getLocation());
                if (i == stationMap.size() - 1) {
                    System.out.println("Enter 0 to go back.");
                }
            }
            int choice = sc.nextInt();
            if (choice == 0) {
                menu(stationMap);
                nextMenu = true;
                break;
            }
            for (int i = 0; i < stationMap.size(); i++) {
                if (choice == stationMap.get(i + 1).getStationID()) {
                    this.user.returnBike(stationMap, stationMap.get(i + 1).getStationID());
                    System.out.println("Press any number to continue.");
                    String input = sc.next();
                    menu(stationMap);
                    nextMenu = true;
                    break;
                } else if (i == (stationMap.size() - 1)) {
                    System.out.println("Please select a valid station.");
                }
            }
        }
    }

    private void showUserInformation(HashMap<Integer, Station> stationMap) {
        System.out.println(this.user.toString());
        System.out.println("Press any number to return to menu.");
        boolean nextMenu = false;
        String input = sc.next();
        menu(stationMap);
    }
}


