package utility;
import objects.Shelter;
import java.util.Scanner;
import java.util.ArrayList;

import objects.Pet;

public class Ink {
  private Scanner input = new Scanner(System.in);
  private int choice;
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_CYAN = "\u001B[36m";
    public static String ANSI_WHITE = "\u001B[37m";
  
  public void printWelcome() {
    System.out.println(ANSI_YELLOW + "\n\n\n WELCOM TO ADOPT PET PROGRAM");
  } // printWelcome()

  public void printGoodday() {
    System.out.println(ANSI_GREEN + " Have a great day pet lover ");
  } // printGoodday()

  public int validateMainMenu() {
    boolean valid = false;

    while(!valid) {
        System.out.println(ANSI_PURPLE + "╔════════════════════╗");
        System.out.printf(ANSI_PURPLE + "║");
        System.out.println(ANSI_GREEN +  "      MAIN MENU"+ ANSI_PURPLE + "     ║");
        System.out.println(ANSI_PURPLE + "╚════════════════════╝" + ANSI_RED);
        System.out.println(ANSI_PURPLE + "╔════════════════════╗");
        System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(1) " + ANSI_BLUE + "View Pets       " + ANSI_PURPLE + "║" );
        System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(2) " + ANSI_BLUE + "Shelter Details " + ANSI_PURPLE + "║" );
        System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(3) " + ANSI_BLUE + "Book Appointment" + ANSI_PURPLE + "║" );
        System.out.println(ANSI_PURPLE + "║" + ANSI_YELLOW + "(4) " + ANSI_BLUE + "EXIT            " + ANSI_PURPLE + "║" );
        System.out.println(ANSI_PURPLE + "╚════════════════════╝" + ANSI_RED);
      try {
        choice = input.nextInt();
        if(choice >= 1 && choice <=4) {
          valid = true; // escapes loop only if choice is correct dt and range
        }
        else {
          System.out.println("Please enter a 1 - 4");
        }
      } 
      catch (Exception e) { // runs on exception
        System.out.println("That's not a number!");
      } 
      finally { // always runs!
        input.nextLine();
      }
    } // while
    return choice;
  } // printMenu()

  public void printPetDetails(Pet pet) {
    System.out.printf("Name: %s\n", pet.getName());
    System.out.printf("Age: %d\n", pet.getAge());
    System.out.printf("Breed: %s\n", pet.getBreed());
    System.out.printf("Color: %s\n", pet.getColor());
    System.out.printf("Owner: %s\n", pet.getOwner());
    System.out.printf("Is Adopted: %b\n", pet.getIsAdopted());
    System.out.println("Adopt this pet? Y/N");
  } // printPetDetails()
  
  public void printAvailablePets(ArrayList<Pet> pets) {
    for(int i = 0; i < pets.size(); i++) {
        if(!pets.get(i).getIsAdopted()) {
            String name = pets.get(i).getName();
            String type = pets.get(i).getType();
            int age = pets.get(i).getAge();
            
            // Set a fixed width for each field to ensure alignment
            String formattedName = String.format("%-15s", name); // 15 characters wide
            String formattedType = String.format("%-10s", type); // 10 characters wide
            String formattedAge = String.format("%-3d", age);    // 3 characters wide
    
            System.out.println(ANSI_PURPLE + "╔════════════════════════════════════════════════════╗" + ANSI_BLUE);
            System.out.printf(ANSI_PURPLE + "║" + ANSI_YELLOW + "(%d)" + ANSI_BLUE + " Name: " + ANSI_GREEN + "%s" + ANSI_BLUE + " Type: " + ANSI_GREEN + "%s" + ANSI_BLUE + " Age: " + ANSI_GREEN + "%s" + ANSI_PURPLE + " ║\n", 
                              i + 1, formattedName, formattedType, formattedAge);
            System.out.println(ANSI_PURPLE + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);
            
        }

    } // for
  } // printAvailablePets()

  public void printShelterHours(Shelter shelter) {
    System.out.println(ANSI_PURPLE + "╔═══════════════════════════════════════╗" + ANSI_BLUE);
    System.out.printf(ANSI_PURPLE +  "║" + ANSI_GREEN + "Shelter Address: %s\n",shelter.getAddress() + ANSI_PURPLE +"     ║");
    System.out.println(ANSI_PURPLE + "╚═══════════════════════════════════════╝" + ANSI_RED);
    String[] hours = shelter.getHours();
    for(int i = 0; i < hours.length; i++) {
        System.out.println(ANSI_PURPLE + "╔═══════════════════════╗" );
      System.out.printf(ANSI_PURPLE + "║" + ANSI_YELLOW + "(%d)" + ANSI_BLUE + " %s\n", i + 1, hours[i]+ ANSI_PURPLE+ "    ║");
      System.out.println(ANSI_PURPLE + "╚═══════════════════════╝" + ANSI_RED);
    } // for
   //System.out.println(ANSI_BLUE + "Please Select your appintment day");
    
  } // printShelterDetails

  

  }
