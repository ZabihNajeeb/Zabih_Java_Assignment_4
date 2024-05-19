import utility.*;
import objects.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Adopt_a_pet {
    private static Scanner input = new Scanner(System.in);
    private static Ink ink = new Ink();
    private static User user;
    private static Shelter shelter = new Shelter();
    private static Pet pet;
    private static String name, email, phone, selectDayOfWeek;
    private static int choice;
    private static boolean isDone = false;
    private static boolean goBack = false;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static final String APPOINTMENTS_FILE = "appointments.txt";

    public static void main(String[] args) {
        ink.printWelcome();
        createPets(); // helper pets
        loadAppointments(); // Load appointments from file

        while (!isDone) {
            choice = ink.validateMainMenu();

            switch (choice) {
                case 1: // print available pets
                    printPetStats();
                    ink.printAvailablePets(shelter.getPets());
                    handlePetSelection();
                    break;
                case 2: // print shelter details
                    ink.printShelterHours(shelter);
                    waitForGoBack();
                    break;
                case 3: // Book an appointment menu
                    handleAppointmentMenu();
                    break;
                case 4:
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            goBack = false;
        }

        saveAppointments(); // Save appointments to file
        ink.printGoodday();
    }

    private static void handlePetSelection() {
        while (!goBack) {
            System.out.println("Enter a pet number to see details (1-5)\nOr "+ANSI_GREEN + "0" + ANSI_RESET + " to go back:");
            int pick = input.nextInt();
            if (pick != 0) {
                ink.printPetDetails(shelter.getPet(pick - 1));
                System.out.println("Do you want to adopt this pet? (Y/N)");
                String answer = input.next();
                if (answer.equalsIgnoreCase("Y")) {
                    createUser();
                    shelter.adopt(pick - 1, user.getName());
                    goBack = true;
                }
            } else {
                goBack = true;
            }
        }
    }

    private static void waitForGoBack() {
        System.out.println(ANSI_RESET + "Press any key to go back to the main menu.");
        input.nextLine(); // consume the leftover newline
        input.nextLine(); // wait for the user input
        goBack = true;
    }

    private static void handleAppointmentMenu() {
        while (!goBack) {
            System.out.println(ANSI_PURPLE + "╔══════════════════════════╗");
            System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(1) " + ANSI_BLUE + "Book an Appointment   " + ANSI_PURPLE + "║");
            System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(2) " + ANSI_BLUE + "Go back to main menu  " + ANSI_PURPLE + "║");
            System.out.println(ANSI_PURPLE + "╚══════════════════════════╝" + ANSI_RED);
            int appointmentChoice = input.nextInt();

            switch (appointmentChoice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    goBack = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void createPets() {
        pet = new Pet("spot", "dog", 3, "black", "hound");
        shelter.addPet(pet);
        pet = new Pet("cleveland", "dog", 7, "brown", "boxer");
        shelter.addPet(pet);
        pet = new Pet("monster", "cat", 1, "calico", "calico");
        shelter.addPet(pet);
        pet = new Pet("Tiger", "cat", 4, "White", "calico");
        shelter.addPet(pet);
        pet = new Pet("Chgar", "dog", 5, "Ablaq", "Germani");
        shelter.addPet(pet);
      
    }

    private static void printPetStats() {
        int dogCount = 0;
        int catCount = 0;
        for (Pet pet : shelter.getPets()) {
            if (pet.getType().equalsIgnoreCase("dog")) {
                dogCount++;
            } else if (pet.getType().equalsIgnoreCase("cat")) {
                catCount++;
            }
        }
        System.out.println(ANSI_GREEN + "TOTAL PETS: " + ANSI_YELLOW + shelter.getPets().size());
        System.out.println(ANSI_BLUE + "Dogs: " + ANSI_YELLOW + dogCount + ANSI_BLUE + "  Cats:  " + ANSI_YELLOW + catCount);
    }

    public static void createUser() {
        input.nextLine(); // consume the leftover newline
        System.out.println(ANSI_GREEN + "Booking Appiontment...");
        System.out.println(ANSI_PURPLE + "********************************************************");
        System.out.println(ANSI_GREEN + "What is your name?" + ANSI_RED);
        name = input.nextLine();
        System.out.println(ANSI_GREEN + "What is your email?" + ANSI_RED);
        email = input.nextLine();
        System.out.println(ANSI_GREEN + "What is your phone?" + ANSI_RED);
        phone = input.nextLine();
        ink.printShelterHours(shelter);
        int day;
        while (true) {
            System.out.println(ANSI_BLUE + "Please select your appointment day:");
            day = input.nextInt();
            if (day >= 1 && day <= 5) {
                break;
            } else {
                System.out.println(ANSI_RED + "Invalid choice. Please select a day that is in range (1-5)." + ANSI_RESET);
            }
        }
        input.nextLine(); // consume newline
        String[] hours = shelter.getHours();
        selectDayOfWeek = hours[day - 1];
        user = new User(name, email, phone, selectDayOfWeek);
        System.out.println(ANSI_WHITE + "********************************************************\n\n" + ANSI_RED);
        System.out.println(ANSI_BLUE + "Dear " + ANSI_GREEN + name + ANSI_BLUE + " your appointment is booked for " + ANSI_GREEN + selectDayOfWeek );
        System.out.println(ANSI_WHITE + "********************************************************\n" + ANSI_RED);
    }

    private static void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE))) {
            for (Pet pet : shelter.getPets()) {
                if (pet.getIsAdopted()) {
                    writer.write(pet.getName() + "," + pet.getOwner());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments to file: " + e.getMessage());
        }
    }

    private static void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String petName = parts[0];
                    String ownerName = parts[1];
                    Pet pet = shelter.getPet(petName);
                    if (pet != null) {
                        pet.setIsAdopted();
                        pet.setOwner(ownerName);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Appointments file not found. No existing appointments.");
        } catch (IOException e) {
            System.out.println("Error loading appointments from file: " + e.getMessage());
        }
    }
}

