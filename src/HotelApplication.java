import api.AdminResource;
import api.HotelResource;
import model.*;
import service.ReservationService;
import ui.AdminMenu;
import ui.MainMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelApplication {
    public static void main(String[] args){
        boolean running = true;
        boolean adminRunning = true;
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        AdminResource adminResource = new AdminResource();
        HotelResource hotelResource = new HotelResource();
        String dateRegexPattern = "^(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-(\\d{4})$";
        String nameRegex = "^[A-Za-z][a-z]*$";
        String emailRegex = "^(.+)@(.+).(.+)$" ;
        Pattern patternDate = Pattern.compile(dateRegexPattern);
        Pattern patternName = Pattern.compile(nameRegex);
        Pattern patternEmail = Pattern.compile(emailRegex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try (Scanner scanner = new Scanner(System.in)){
            while (running) {
                try {
                    mainMenu.printMenu();
                    int userInput = Integer.parseInt(scanner.nextLine());
                    switch (userInput){
                        case 1:
                            reserveRoom(scanner, hotelResource, patternDate, dateFormat, patternEmail);
                            break;
                        case 2:
                            printAllReservations(scanner, patternEmail, hotelResource);
                            break;
                        case 3:
                            accountCreate(scanner, patternName, patternEmail, hotelResource);
                            break;
                        case 4:
                            while(adminRunning) {
                                try {
                                    adminMenu.printMenu();
                                    int adminInput = Integer.parseInt(scanner.nextLine());
                                    switch (adminInput) {
                                        case 1:
                                            printAllCustomers(adminResource);
                                            break;
                                        case 2:
                                            printAllRooms(adminResource);
                                            break;
                                        case 3:
                                            printAllReservations(adminResource);
                                            break;
                                        case 4:
                                            createNewRoom(scanner, hotelResource, adminResource);
                                            break;
                                        case 5:
                                            adminRunning = false;
                                            break;
                                        case 6:
                                            addRooms();
                                            addCustomers();
                                            addReservations();
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

    private static void createNewRoom(Scanner scanner, HotelResource hotelResource, AdminResource adminResource) {
        List<IRoom> rooms = new ArrayList<IRoom>();
        //add a room
        IRoom room1 = null;
        String roomNumberSelect;
        System.out.println("create a Room");
        do {
            System.out.println("please select a room number");
            roomNumberSelect = scanner.nextLine();
            room1 = hotelResource.getRoom(roomNumberSelect);
            if(room1 != null){
                System.out.println(" that room number already exists please select another one");
            }
        } while (room1 != null);

        System.out.println("please select a room type:");
        System.out.println("1. single");
        System.out.println("2. double");
        int roomTypeSelect = Integer.parseInt(scanner.nextLine());

        RoomType roomType = RoomType.SINGLE;
        if (roomTypeSelect == 1 ) {
            roomType = RoomType.SINGLE;
        } else if(roomTypeSelect == 2) {
            roomType = RoomType.DOUBLE;
        }

        System.out.println("select room price");
        System.out.println("1. free");
        System.out.println("2. paid");
        int option = Integer.parseInt(scanner.nextLine());
        IRoom room = null;
        if (option == 1) {
             room = new FreeRoom(roomNumberSelect, 0.0, roomType);
        } else if(option ==2){
            System.out.println("please enter price:");
            Double roomPrice = Double.parseDouble(scanner.nextLine());
             room = new FreeRoom(roomNumberSelect, roomPrice, roomType);
        }
        rooms.add(room);
        adminResource.addRoom(rooms);
    }

    private static void printAllReservations(AdminResource adminResource) {
        adminResource.displayAllReservations();


    }

    private static void printAllRooms(AdminResource adminResource) {
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        allRooms.forEach(room -> System.out.println(room.toString()));
        if (allRooms.isEmpty()) {
            System.out.println("there are no rooms");
        }
    }

    private static void printAllCustomers(AdminResource adminResource) {
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        allCustomers.forEach(customer1 -> System.out.println(customer1.toString()));
        if (allCustomers.isEmpty()) {
            System.out.println("there are no customers");
        }
    }

    private static void printAllReservations(Scanner scanner, Pattern patternEmail, HotelResource hotelResource) {
        String email = null;
        Boolean em = true;
        while(em) {
            System.out.println("please enter email:");
            String email1 = scanner.nextLine();
            Matcher matcher1 = patternEmail.matcher(email1);
            if (matcher1.matches()) {
                email = email1;
                em = false;
            } else {
                System.out.println("Not correct email format");
            }
        }
        Collection<Reservation> customerReservations = hotelResource.getCustomerReservations(email);
        customerReservations.forEach(reservation -> System.out.println(reservation.toString()));
        if (customerReservations.isEmpty()) {
            System.out.println("there are no customers");
        }
    }

    private static void reserveRoom(Scanner scanner, HotelResource hotelResource, Pattern patternDate, SimpleDateFormat dateFormat, Pattern patternEmail) throws ParseException {
        String email = null;
        Boolean em = true;
        System.out.println("Do you have an account?");
        while(em) {
            System.out.println("please enter email:");
            String email1 = scanner.nextLine();
            Matcher matcher1 = patternEmail.matcher(email1);
            if (matcher1.matches()) {
                email = email1;
                em = false;
            } else {
                System.out.println("Not correct email format");
            }
        }
        Customer customer = hotelResource.getCustomer(email);
        Date checkinDate = new Date();
        Date checkOutDate = new Date();
        if (!(customer == null)) {
            //find a room
            Collection<IRoom> freeRooms = new ArrayList<>();
            boolean checkingDate = true;
            boolean checkinoutgDate = true;
            do {
                while (checkingDate) {
                    System.out.println("select checking date based on this format MM-dd-yyyy");
                    String date = scanner.nextLine();
                    Matcher matcher1 = patternDate.matcher(date);
                    if (matcher1.matches()) {
                        checkinDate = dateFormat.parse(date);
                        checkingDate = false;
                    } else {
                        System.out.println("Not correct date format");
                    }
                }
                while (checkinoutgDate) {
                    System.out.println("select checkout date based on this format MM-dd-yyyy");
                    String date = scanner.nextLine();
                    Matcher matcher1 = patternDate.matcher(date);
                    if (matcher1.matches()) {
                        checkOutDate = dateFormat.parse(date);
                        checkinoutgDate = false;
                    } else {
                        System.out.println("Not correct date format");
                    }
                }
                freeRooms = hotelResource.findARoom(checkinDate, checkOutDate);
                if (freeRooms.isEmpty()) {
                    System.out.println(" there are no available rooms for these dates please choose again or see free rooms after 7 days");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(checkinDate);
                    calendar.add(Calendar.DAY_OF_YEAR, 7);
                    Date increasedCheckinDate = calendar.getTime();
                    calendar.setTime(checkOutDate);
                    calendar.add(Calendar.DAY_OF_YEAR,7);
                    Date increasedCheckoutDate = calendar.getTime();
                    freeRooms = hotelResource.findARoom(increasedCheckinDate, increasedCheckoutDate);
                }
            } while (freeRooms.isEmpty());
            freeRooms.forEach(room -> System.out.println(room.toString()));
            System.out.println("Select room number");
            String roomNumber = scanner.nextLine();
            IRoom room = hotelResource.getRoom(roomNumber);
            Reservation reservation = null;
            try {
                reservation = hotelResource.bookARoom(customer.getEmail(), room, checkinDate, checkOutDate);
                System.out.println("This is your reservation");
                System.out.println(reservation.toString());
            }catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("there is no such an account. Please create a new one");
        }
    }

    private static void accountCreate(Scanner scanner, Pattern patternName, Pattern patternEmail, HotelResource hotelResource) {
        System.out.println("create an account");
        String firstName = null;
        String lastName = null;
        String email = null;
        Boolean fn = true;
        Boolean ln = true;
        Boolean em = true;
        while(fn) {
            System.out.println("please enter first name:");
            String name = scanner.nextLine();
            Matcher matcher1 = patternName.matcher(name);
            if (matcher1.matches()) {
                firstName = name;
                fn = false;
            } else {
                System.out.println("Not correct date format");
            }
        }
        while(fn) {
            System.out.println("please enter last name:");
            String name = scanner.nextLine();
            Matcher matcher1 = patternName.matcher(name);
            if (matcher1.matches()) {
                lastName = name;
                ln = false;
            } else {
                System.out.println("Not correct date format");
            }
        }
        while(em) {
            System.out.println("please enter email:");
            String email1 = scanner.nextLine();
            Matcher matcher1 = patternEmail.matcher(email1);
            if (matcher1.matches()) {
                email = email1;
                em = false;
            } else {
                System.out.println("Not correct email format");
            }
        }
        hotelResource.createACustomer(email, firstName, lastName);
    }

    public static void  addRooms(){
        AdminResource adminResource = new AdminResource();
        Room room1 = new Room("001",100.0, RoomType.SINGLE);
        Room room2 = new Room("002",100.0, RoomType.DOUBLE);
        Room room3 = new Room("003",100.0, RoomType.SINGLE);
        Room room4 = new Room("004",100.0, RoomType.DOUBLE);
        Room room5 = new Room("005",100.0, RoomType.SINGLE);
        Room room6 = new Room("006",100.0, RoomType.DOUBLE);
        Room room7 = new Room("007",100.0, RoomType.SINGLE);
        Room room8 = new Room("008",100.0, RoomType.DOUBLE);
        ArrayList<IRoom> populateRooms = new ArrayList<IRoom>();
        populateRooms.add(room1);
        populateRooms.add(room2);
        populateRooms.add(room3);
        populateRooms.add(room4);
        populateRooms.add(room5);
        populateRooms.add(room6);
        populateRooms.add(room7);
        populateRooms.add(room8);
        adminResource.addRoom(populateRooms);
    }

    public static void addCustomers() {
        HotelResource hotelResource = new HotelResource();
        hotelResource.createACustomer("a1@a.a", "alex1", "kon1");
        hotelResource.createACustomer("a2@a.a", "alex2", "kon2");
        hotelResource.createACustomer("a3@a.a", "alex3", "kon3");
        hotelResource.createACustomer("a4@a.a", "alex4", "kon4");
        hotelResource.createACustomer("a5@a.a", "alex5", "kon5");
        hotelResource.createACustomer("a6@a.a", "alex6", "kon6");
    }

    public static void addReservations() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        HotelResource hotelResource = new HotelResource();
        try {
            hotelResource.bookARoom("a2@g.a",hotelResource.getRoom("002"),dateFormat.parse("01-01-2024"),dateFormat.parse("01-07-2024"));
            hotelResource.bookARoom("a3@g.a",hotelResource.getRoom("003"),dateFormat.parse("01-02-2024"),dateFormat.parse("01-08-2024"));
            hotelResource.bookARoom("a4@g.a",hotelResource.getRoom("004"),dateFormat.parse("01-03-2024"),dateFormat.parse("01-09-2024"));
            hotelResource.bookARoom("a5@g.a",hotelResource.getRoom("005"),dateFormat.parse("01-04-2024"),dateFormat.parse("01-10-2024"));
            hotelResource.bookARoom("a6@g.a",hotelResource.getRoom("006"),dateFormat.parse("01-05-2024"),dateFormat.parse("01-11-2024"));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}