/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.io.Serializable;
import java.util.Random;

/**
 * This class is a utility class that inherits method that are used to determine
 * the result of an attack
 * @author Ajay
 */
public class Utility implements Serializable {
    
    //random - Random object created to generate random numbers
    Random random;
    
    /**
     * Constructor to create a new object of Random class
     */
    public Utility(){
        random = new Random();
    }
    
    /**
     * Method to determine if the attacker was successful in attacking the target
     * @return boolean value representing the success of an attack
     */
    public Boolean isHit(){
        int number = random.nextInt(3)+1;
        if(number <=2){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method to generate the hit percent after every attack
     * @param attack int value representing the level of the attacker
     * @return int value representing the damage after an attack
     */
    public int getHitPercent(int attack){
        int number = random.nextInt(2*attack)+1;
        return number;
    }
}
