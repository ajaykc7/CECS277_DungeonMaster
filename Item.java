/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.io.Serializable;

/**
 * The class inherits all the variable and method regarding an item class
 * @author Ajay
 */
public class Item implements Serializable{
    
    /**
     * name - String variable representing the name of the item
     */
    private String name;
    
    /**
     * goldValue - int variable representing the gold value of the item
     */
    private int goldValue;
    
    /**
     * Constructor to initialize the name and gold value of the item
     * @param name String argument representing the name of the item
     * @param value int argument representing the gold value of the item
     */
    public Item(String name, int value){
        this.name = name;
        goldValue = value;
    }
    
    /**
     * Method to return the name of the item
     * @return String value representing the name of the item
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method to return the value of the item
     * @return int value representing the value of the item
     */
    public int getValue(){
        return goldValue;
    }
}
