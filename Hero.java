/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class Hero consists all the methods related to a Hero and is inherited
 * from Character class.
 *
 * @author Ajay
 */
public class Hero extends Character implements Serializable {

    /**
     * items - ArrayList to store the items that Hero has
     */
    private ArrayList<Item> items;

    /**
     * location - location of the hero in the map represented as a Point
     */
    private Point location;

    //Creating a new object of utility class to use methods inside the class
    Utility utility = new Utility();

    /**
     * Constructor to initialize the name, quip, and location of the hero
     *
     * @param name String parameter passed to initialize the name of the hero
     * @param quip String parameter passed to initialize the catchphrase of the
     * hero
     * @param start Point to initialize the starting location of the hero
     */
    public Hero(String name, String quip, Point start) {
        super(name, quip, 20, 1, 10);
        location = start;
        items = new ArrayList<Item>();
    }

    /**
     * Method to return the ArrayList of items that hero has
     *
     * @return ArrayList consisting the items that hero has
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Method to check if the user can pick up an item after a battle or not. If
     * the hero can pick up the item, then add it to the list and return true.
     * If not, then return false
     *
     * @param i Item that is to be picked up
     * @return boolean value to state if the hero can pick up the item or not
     */
    public boolean pickUpItem(Item i) {

        //if statement checks if the hero has more than 5 items in his bag or not
        if (items.size() < 5) {
            items.add(i);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to remove an item from the bag
     *
     * @param i item to be removed from the bag
     */
    public void removeItem(Item i) {
        items.remove(i);
    }

    /**
     * Method to remove an item from the bag
     *
     * @param index int value representing the index of the item to be removed
     * from the arraylist
     */
    public void removeItem(int index) {
        items.remove(index);
    }

    /**
     * Method to return the location of the user
     *
     * @return the location of the user represented as a Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Method to set the location of the user at a certain Point
     *
     * @param p the location to be set which is represented as a Point
     */
    public void setLocation(Point p) {
        location = p;
    }

    /**
     * Method to check if the user can go North or not. If yes, set a new
     * location of the user on the map
     *
     * @param l level that the user is in
     * @return char value representing the room type at a particular co-ordinate
     */
    public char goNorth(Level l) {
        int xcoordinate = (int) location.getX() - 1;
        if ((xcoordinate < 0) || (xcoordinate > 3)) {
            return 'x';
        } else {
            setLocation(new Point(xcoordinate, (int) location.getY()));
            return l.getRoom(location);
        }
    }

    /**
     * Method to check if the user can go South or not. If yes, set a new
     * location of the user on the map
     *
     * @param l level that the user is in
     * @return char value representing the room type at a particular co-ordinate
     */
    public char goSouth(Level l) {
        int xcoordinate = (int) location.getX() + 1;
        if ((xcoordinate < 0) || (xcoordinate > 3)) {
            return 'x';
        } else {
            setLocation(new Point(xcoordinate, (int) location.getY()));
            return l.getRoom(location);
        }
    }

    /**
     * Method to check if the user can go East or not. If yes, set a new
     * location of the user on the map
     *
     * @param l level that the user is in
     * @return char value representing the room type at a particular co-ordinate
     */
    public char goEast(Level l) {
        int ycoordinate = (int) location.getY() + 1;
        if ((ycoordinate < 0) || (ycoordinate > 3)) {
            return 'x';
        } else {
            setLocation(new Point((int) location.getX(), ycoordinate));
            return l.getRoom(location);
        }
    }

    /**
     * Method to check if the user can go West or not. If yes, set a new
     * location of the user on the map
     *
     * @param l level that the user is in
     * @return char value representing the room type at a particular co-ordinate
     */
    public char goWest(Level l) {
        int ycoordinate = (int) location.getY() - 1;
        if ((ycoordinate < 0) || (ycoordinate > 3)) {
            return 'x';
        } else {
            setLocation(new Point((int) location.getX(), ycoordinate));
            return l.getRoom(location);
        }
    }

    /**
     * Method overridden to attack a character
     *
     * @param c Character to be attacked
     */
    @Override
    public void attack(Character c) {

        /*isHit - boolean value to check if the attacker was successful in hitting
         *the target or not.
         */
        boolean isHit = utility.isHit();

        /*
        if statement to check if the user hit the attacker or not. 
        if yes - generate the hit percent
           no - character c says its catchphrase
        */
        if (isHit) {

            int hitPercent = 0;
            if (getLevel() <= 1) {
                hitPercent = utility.getHitPercent(2);
            } else {
                hitPercent = utility.getHitPercent(getLevel());
            }
            c.takeDamage(hitPercent);

            System.out.println("\n" + getName() + " hits " + c.getName() + " for " + hitPercent + " damage.");
        } else {
            System.out.println("\n" + c.getName() + " dodges the attack");
            System.out.println("\n" + c.getName() + " says " + c.getQuip());
        }

    }
}
