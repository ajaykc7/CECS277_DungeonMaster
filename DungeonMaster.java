/*
 NAME:- Ajay Kc
 DATE: - 18th February, 2016
 PROGRAM: - PROJECT #2
 DESCRIPTION:- This program generates a user interactive game where user is a 
 Hero and goes through different level of Dungeon and fights with monstorsThe program 
 incorporates the concept of Inheritance, Polymorphism, Interface, and File Handling 
 */
package dungeonmaster;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main class of the project. The class checks if the user has
 * played the game before or not. If yes, it starts from where the user left
 * last time. If not, the program prompts the user to input his name and start a
 * new game
 *
 * @author Ajay
 */
public class DungeonMaster {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        //itmgenerator - object of itemGenerator class
        ItemGenerator itmgenerator = new ItemGenerator();

        //enemygenerator - object of EnemyGenerator class
        EnemyGenerator enemygenerator = new EnemyGenerator();

        //hero = an instance of hero class
        Hero hero = null;

        //utility - object of Utility class
        Utility utility = new Utility();

        System.out.println("|---------------------------|");
        System.out.println("|                           |");
        System.out.println("|      DUNGEON MASTER       |");
        System.out.println("|                           |");
        System.out.println("|___________________________|");
        File herofile = new File("Hero.dat");

        boolean isGameOver = false;

        //while statement executes until user wants to Exit the game
        while (isGameOver == false) {

            /*if statement checks if the hero.dat file exists or not.
             If yes - Read the object from the file 
             no - Create a new object of hero class
             */
            if (herofile.exists()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(herofile));
                    hero = (Hero) ois.readObject();
                    ois.close();
                } catch (IOException e) {
                    System.out.println("\nError processing file");
                } catch (ClassNotFoundException ex) {
                    System.out.println("\nClass not found");
                }

            } else {

                System.out.print("\nWhat is your name, traveler ? ");
                String playername = scn.nextLine();

                hero = new Hero(playername, "Long live the queen", new Point(3, 0));

            }

            //level - object of Level class
            Level level = new Level();

            // level_hero - int variable representing the level of the hero
            int level_hero = hero.getLevel();

            //Method called to generate a particular level based on the hero's level
            level.generateLevel(level_hero);

            //roomlocation - char variable representing the room type at a particular coordinate
            char roomlocation;

            /*totaloption - int variable representing the total options that hero has
             based on their location
             */
            int totaloption;

            /*selection - int variable representing the selection that user makes
             to select his action
             */
            int selection = 0;
            Boolean selectionchecker_one = true;

            //while statement checks if the user inputted the valid selection
            while (selectionchecker_one) {

                //item - ArrayList of type item that stores the items carried by hero
                ArrayList<Item> item = hero.getItems();

                roomlocation = level.getRoom(hero.getLocation());

                //potionchecker - boolean variable to check if the hero has Health Potion or not
                Boolean potionchecker = false;

                //for statement to check if hero has Health Potion or not
                for (int i = 0; i < item.size(); i++) {
                    if (item.get(i).getName().equals("Health Potion")) {
                        potionchecker = true;
                        break;
                    } else {
                        potionchecker = false;
                    }

                }
                //method called to display the map and the hero's coordinate
                level.displayMap(hero.getLocation());

                /*if statement to check the hero is in the starting location of
                 the map. The statements decide if the hero can do other actions 
                 based on his location on the map
                 */
                if (roomlocation == 's') {

                    System.out.println("\nWhat do you want to do? ");
                    System.out.println("\n1. Enter shop ");
                    System.out.println("2. Move");
                    System.out.println("3. Exit");
                    if (potionchecker) {
                        System.out.println("4. Use Health Potion");
                        totaloption = 4;
                    } else {
                        totaloption = 3;
                    }
                } else {
                    System.out.println("\nWhat do you want to do? ");
                    System.out.println("2. Move");
                    System.out.println("3. Exit");
                    if (potionchecker) {
                        System.out.println("4. Use Health Potion");
                        totaloption = 4;
                    } else {
                        totaloption = 3;
                    }
                }

                try {
                    selection = scn.nextInt();
                    if ((selection < 1) || (selection > totaloption)) {
                        System.out.println("\n Please make the appropriate selection");
                    } else {

                        //if statement to check if the hero can enter the shop or not
                        if ((selection == 1) && (roomlocation == 's')) {
                            boolean isValidInput = false;

                            //while statement checks if the hero inputted the valid input or not
                            while (isValidInput == false) {
                                System.out.println("\n1. Buy items");
                                System.out.println("2. Sell items");

                                try {

                                    int action = scn.nextInt();
                                    if ((action < 1) || (action > 2)) {
                                        System.out.println("\nInvalid option. Please make the correct selection");
                                    } else {
                                        if (action == 2) {

                                            /*if statement checks if the user has any item to sell or not.
                                             If yes - sell the item
                                             no - tell the user
                                             */
                                            if (item.size() == 0) {
                                                System.out.println("You do not have any items");
                                            } else {
                                                boolean selectionchecker_two = true;
                                                int sellselection = 0;

                                                //while statement checks if the user inputted the valid option or not
                                                while (selectionchecker_two) {
                                                    System.out.println("\nWhat do you want to sell?\n");

                                                    for (int i = 0; i < item.size(); i++) {
                                                        System.out.println(i + 1 + ". " + item.get(i).getName());
                                                    }

                                                    try {
                                                        //sellselection - int variable representing the index in the arraylist that has the item that user wants to sell
                                                        sellselection = scn.nextInt();

                                                        //if statement checks if the user inputed the valid option or not
                                                        if ((sellselection < 1) || (sellselection > item.size())) {
                                                            System.out.println("\n Please make the appropriate selection");
                                                        } else {
                                                            System.out.println("Do you want to sell " + item.get(sellselection - 1).getName() + " for " + item.get(sellselection - 1).getValue() + " gold? (Y/N)");
                                                            String selloption;
                                                            boolean selectionchecker = true;

                                                            //while statement checks if the user inputted the valid option or not
                                                            while (selectionchecker) {
                                                                selloption = scn.next();

                                                                /* if statement to check if the user wants to sell an item or not
                                                                 if yes - sell the item, collect gold value and remove the item from the bag
                                                                 no - go back to the map
                                                                 */
                                                                if (selloption.toUpperCase().equals("Y")) {
                                                                    hero.collectGold(item.get(sellselection - 1).getValue());
                                                                    hero.removeItem(item.get(sellselection - 1));
                                                                    selectionchecker = false;
                                                                    selectionchecker_two = false;
                                                                } else if (selloption.toUpperCase().equals("N")) {
                                                                    selectionchecker = false;
                                                                    selectionchecker_two = false;
                                                                } else {
                                                                    System.out.println("\nPlease make the correct selection");
                                                                }
                                                            }
                                                        }
                                                    } catch (InputMismatchException ime) {
                                                        System.out.println("\nWrong input. Please try again");
                                                        scn.next();
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("Do you want to buy Health Potion for 30 gold?(Y/N)");

                                            String buyoption;
                                            boolean selectionchecker = true;

                                            //while statement checks if the user inputted the valid option or not
                                            while (selectionchecker) {
                                                buyoption = scn.next();

                                                /* if statement to check if the user wants to buy health potion or not
                                                 if yes - check the bag, include the health potion and decrease the gold value
                                                 no - go back to the map
                                                 */
                                                if (buyoption.toUpperCase().equals("Y")) {

                                                    //if statement to check if the user has enough gold to buy health potion or not
                                                    if (hero.getGold() < 30) {
                                                        System.out.println("\nYou do not have enough gold to buy the health potion");
                                                    } else {

                                                        //isBuySuccess - boolean variable to check if the hero can add the item or not
                                                        boolean isBuySuccess = hero.pickUpItem(new Item("Health Potion", 25));

                                                        //if the hero can add the item, reduce the gold value. If not, tell the user 
                                                        if (isBuySuccess) {
                                                            hero.collectGold(-30);
                                                        } else {
                                                            System.out.println("\n Your bag pack is full. Cannot buy the item");
                                                        }
                                                    }
                                                    selectionchecker = false;
                                                } else if (buyoption.toUpperCase().equals("N")) {
                                                    selectionchecker = false;
                                                } else {
                                                    System.out.println("\nPlease make the correct selection");
                                                }
                                            }
                                        }
                                        isValidInput = true;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("\nInvalid option. Please make the correct selection");
                                    scn.next();
                                }
                            }
                        } else if (selection == 2) {

                            //directionoption - int variable representing the direction the user wants to move
                            int directionoption;
                            boolean selectionchecker_three = true;

                            //while statement checks if the user inputted the valid option or not
                            while (selectionchecker_three) {

                                level.displayMap(hero.getLocation());
                                System.out.println("\nChoose Direction");
                                System.out.println("\n1. North");
                                System.out.println("2. South");
                                System.out.println("3. East");
                                System.out.println("4. West");

                                try {
                                    directionoption = scn.nextInt();
                                    //if statement checks if the user inputted the valid option or not
                                    if ((directionoption < 1) || (directionoption > 4)) {
                                        System.out.println("\n Please make the appropriate selection");
                                    } else {

                                        //room - char variable representing the room type based on user's direction option
                                        char room;

                                        if (directionoption == 1) {
                                            room = hero.goNorth(level);
                                        } else if (directionoption == 2) {
                                            room = hero.goSouth(level);
                                        } else if (directionoption == 3) {
                                            room = hero.goEast(level);
                                        } else {
                                            room = hero.goWest(level);
                                        }

                                        boolean runRoomTask = true;
                                        //while statement checks if the user was able to go the valid room or not
                                        while (runRoomTask) {
                                            level.displayMap(hero.getLocation());
                                            switch (room) {

                                                // if room has m - then fight the monster
                                                case 'm':

                                                    //encounteredenemy - enemey object representing the enemy randomly generated in the room based on the hero's level
                                                    Enemy encounteredenemy = enemygenerator.generateEnemy(hero.getLevel());
                                                    System.out.println("\n " + hero.getName() + " has encountered a " + encounteredenemy.getName());
                                                    boolean isSuccess = false;
                                                    //while statement checks if the user can run away from a battle or not and also to check the validity of the input
                                                    while (isSuccess == false) {

                                                        System.out.println("\n" + hero.getName() + " has " + hero.getHp());
                                                        System.out.println("\n" + encounteredenemy.getName() + " has " + encounteredenemy.getHp());

                                                        System.out.println("\nWhat do you want to do?");
                                                        System.out.println("1. Attack");
                                                        System.out.println("2. Run Away");

                                                        try {
                                                            selection = scn.nextInt();

                                                            //if statement checks if the user inputted the valid option or not
                                                            if ((selection < 1) || (selection > 2)) {
                                                                System.out.println("\nInvalid input. Please make a correct selection");
                                                            } else {
                                                                // if selection ==1, attack the monster
                                                                if (selection == 1) {

                                                                    //method called to attack the encounteredenemy
                                                                    hero.attack(encounteredenemy);

                                                                    /*if statement checks if the monster is alive after the attack or not. 
                                                                     If yes - attack the hero
                                                                     */
                                                                    if (encounteredenemy.getHp() > 0) {
                                                                        encounteredenemy.attack(hero);
                                                                    }

                                                                    /*if statement checks if the user was killed or not. IT also checks if the monster is dead or not
                                                                     if hero is dead - close the program
                                                                     if monster is dead - collect the gold and item and move forward
                                                                     */
                                                                    if (hero.getHp() <= 0) {
                                                                        isSuccess = true;
                                                                        selectionchecker_three = false;
                                                                        selectionchecker_one = false;
                                                                        runRoomTask = false;
                                                                        System.out.println("\n You were killed.");
                                                                        System.out.println("GAME OVER");
                                                                        isGameOver = true;
                                                                    } else if (encounteredenemy.getHp() <= 0) {
                                                                        isSuccess = true;
                                                                        selectionchecker_three = false;
                                                                        runRoomTask = false;
                                                                        System.out.println("\n" + hero.getName() + " killed " + encounteredenemy.getName());

                                                                        //method called to collect gold from the dead enemy
                                                                        hero.collectGold(encounteredenemy.getGold());

                                                                        //method called to restore the enemy's hp for next battle
                                                                        //encounteredenemy.heal(encounteredenemy.getOriginalHp());
                                                                        System.out.println("\nYou earned " + encounteredenemy.getGold() + " gold coins");
                                                                        System.out.println("You found " + encounteredenemy.getItem().getName() + " beside the " + encounteredenemy.getName() + " corpse");
                                                                        boolean optionchecker = true;

                                                                        //while statement checks if the user inputted the valid option or not
                                                                        while (optionchecker) {

                                                                            System.out.println("\n1. Keep it");
                                                                            System.out.println("2. Sell it for " + encounteredenemy.getItem().getValue() + " gold coins");

                                                                            try {
                                                                                int option = scn.nextInt();

                                                                                if ((option < 1) || (option > 2)) {
                                                                                    System.out.println("\nWrong input. Please make a valid selection");
                                                                                } else {
                                                                                    optionchecker = false;
                                                                                    switch (option) {
                                                                                        case 1:

                                                                                            //canPickUp - boolean variable to check if the user can add the item to the bagpack or not
                                                                                            boolean canPickUp = hero.pickUpItem(encounteredenemy.getItem());

                                                                                            /*
                                                                                             if canPickUp = true, pick up the item
                                                                                             canPickUp =false, tell the user
                                                                                             */
                                                                                            if (canPickUp) {
                                                                                                System.out.println("\nYou added " + encounteredenemy.getItem().getName() + " to your bag pack");
                                                                                            } else {
                                                                                                System.out.println("\nYour bag pack is full. Could not pick the item up");
                                                                                            }
                                                                                            break;
                                                                                        case 2:
                                                                                            hero.collectGold(encounteredenemy.getItem().getValue());
                                                                                            System.out.println("\nYou earned " + encounteredenemy.getItem().getValue());
                                                                                            break;
                                                                                        default:
                                                                                            break;
                                                                                    }
                                                                                }
                                                                            } catch (InputMismatchException ime) {
                                                                                System.out.println("\nWrong input. Please make a valid selection");
                                                                                scn.next();
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    //if statement to check if the user can run from the battle or not. 
                                                                    if (utility.isHit() == false) {

                                                                        System.out.println("\nYou were successful in escaping");
                                                                        isSuccess = true;

                                                                        Random random = new Random();
                                                                        boolean isRunRoomSuccess = false;

                                                                        //while statement runs until the user is directed to a valid room after escaping a monster
                                                                        while (isRunRoomSuccess == false) {
                                                                            int number = random.nextInt(4) + 1;
                                                                            if (number == 1) {
                                                                                room = hero.goNorth(level);
                                                                            } else if (number == 2) {
                                                                                room = hero.goSouth(level);
                                                                            } else if (number == 3) {
                                                                                room = hero.goEast(level);
                                                                            } else {
                                                                                room = hero.goWest(level);
                                                                            }
                                                                            if (room == 'x') {
                                                                                isRunRoomSuccess = false;
                                                                            } else {
                                                                                isRunRoomSuccess = true;
                                                                            }
                                                                        }
                                                                        runRoomTask = true;
                                                                    } else {
                                                                        System.out.println("\n You failed to escape");
                                                                    }
                                                                }
                                                            }
                                                        } catch (InputMismatchException ime) {
                                                            System.out.println("\nInvalid option. Please make a correct selection");
                                                            scn.next();
                                                        }
                                                    }
                                                    break;

                                                //if room is i - collect the item if the hero's bag pack is not full
                                                case 'i':

                                                    //founditem - Item object that has a random item
                                                    Item founditem = itmgenerator.generateItem();
                                                    System.out.println(hero.getName() + " has found a " + founditem.getName());

                                                    //pickItemsuccess - boolean variable to check if the user can pick up the item or not
                                                    boolean pickItemsuccess = hero.pickUpItem(founditem);
                                                    if (pickItemsuccess) {
                                                        System.out.println("\n" + founditem.getName() + " is in your bag pack");
                                                    } else {
                                                        System.out.println("\nYour bag pack is full. Unable to pick" + founditem.getName() + " up.");
                                                    }
                                                    selectionchecker_three = false;
                                                    runRoomTask = false;
                                                    break;

                                                //if room is f - save the progress, increase hero's level and exit the level
                                                case 'f':

                                                    //method called to increase hero's level
                                                    hero.increaseLevel();

                                                    //method called to increase hero's hp
                                                    hero.increaseHp(hero.getLevel());

                                                    System.out.println("\nCongratulations. You found the exit.");
                                                    hero.display();
                                                    System.out.println("\nSaving Progress");
                                                    selectionchecker_three = false;
                                                    selectionchecker_one = false;
                                                    runRoomTask = false;

                                                    //if statement to check if the user has played all the levels or not
                                                    if (hero.getLevel() > 3) {
                                                        System.out.println("\n Mission accomplished. Game Over");
                                                        isGameOver = true;
                                                    } else {
                                                        System.out.println("\n Do you want to continue? (Y/N)");

                                                        //checker - boolean variable to check if the user wants to continue with the game or not
                                                        boolean checker = true;

                                                        //while statement checks if the user inputted the valid option or not
                                                        while (checker) {
                                                            String option = scn.next();

                                                            /*if user wants to continue - direct the execution of the program back to the beginning
                                                             if user wants to exit - exit the game
                                                             */
                                                            if (option.toUpperCase().equals("Y")) {
                                                                isGameOver = false;
                                                                checker = false;
                                                            } else if (option.toUpperCase().equals("N")) {
                                                                isGameOver = true;
                                                                checker = false;
                                                                System.out.println("\n Thank you for playing. Come back again");
                                                            } else {
                                                                System.out.println("\nInvalid option. Please make the correct selection");

                                                            }
                                                        }
                                                    }
                                                    try {

                                                        //oos - ObjestOutputStream object instantiated to save the record to the hero.date file
                                                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(herofile));

                                                        //method called to set the location of the hero to the start
                                                        hero.setLocation(level.getStartLocation());
                                                        oos.writeObject(hero);
                                                        oos.close();
                                                    } catch (IOException e) {
                                                        System.out.println("Error processing file");
                                                    }
                                                    break;
                                                case 's':
                                                    selectionchecker_three = false;
                                                    runRoomTask = false;
                                                    break;
                                                default:
                                                    runRoomTask = false;
                                                    System.out.println("\nCannot move in this direction. Please try a different dirrection");
                                                    break;
                                            }
                                        }
                                    }
                                } catch (InputMismatchException ime) {
                                    System.out.println("Invalid input. Please make correct selection");
                                    scn.next();
                                }

                            }
                            //if selection = 3, save the record and then exit the game    
                        } else if (selection == 3) {
                            try {
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(herofile));

                                oos.writeObject(hero);
                                oos.close();
                            } catch (IOException e) {
                                System.out.println("Error processing file");
                            }
                            System.out.println("\n Thank you for playing. Come back again");
                            selectionchecker_one = false;
                            isGameOver = true;
                            //if selection = 4 , use the health potion    
                        } else if (selection == 4) {
                            int potionindex = 0;

                            //for statement to check if the user has health potion in the bag or not
                            for (int i = 0; i < item.size(); i++) {
                                if (item.get(i).getName().equals("Health Potion")) {
                                    potionindex = i;
                                }
                            }

                            //method to remove health potion from the bag pack after use
                            hero.removeItem(potionindex);

                            //method to increae the hp of the user
                            hero.heal(hero.getLevel() * 20);
                            System.out.println("\n " + hero.getName() + " has been healed. HP full");
                        } else {
                            System.out.println("Invalid option. Pleae enter a valid option");
                        }
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("\nWrong input. Please try again");
                    scn.next();
                }
            }
        }
    }
}
