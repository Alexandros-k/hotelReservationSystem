import ui.AdminMenu;
import ui.MainMenu;

import java.util.Scanner;

public class HotelApplication {
    public static void main(String[] args) {
        boolean running = true;
        boolean adminRunning = true;
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        try (Scanner scanner = new Scanner(System.in)){
            while (running) {
                try {
                    mainMenu.printMenu();
                    int userInput = Integer.parseInt(scanner.nextLine());
                    switch (userInput){
                        case 1:
                            System.out.println("case1");
                            break;
                        case 2:
                            System.out.println("case2");
                            break;
                        case 3:
                            System.out.println("case3");
                            break;
                        case 4:
                            while(adminRunning) {
                                try {
                                    adminMenu.printMenu();
                                    int adminInput = Integer.parseInt(scanner.nextLine());
                                    switch (adminInput) {
                                        case 1:
                                            System.out.println("case1");
                                            break;
                                        case 2:
                                            System.out.println("case2");
                                            break;
                                        case 3:
                                            System.out.println("case3");
                                            break;
                                        case 4:
                                            adminMenu.printMenu();
                                            break;
                                        case 5:
                                            adminRunning = false;
                                            break;
                                        default:
                                            System.out.println("Please select a number from 1-5");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("please select a number from 1-5 not letter");
                                }
                            }
                            adminRunning = true;
                            break;
                        case 5:
                            running = false;
                            break;
                        default:
                            System.out.println("Please select a number from 1-5");
                    }
                } catch(Exception ex){
                    System.out.println("please select a number not letter");
                }
            }
        }
    }
}