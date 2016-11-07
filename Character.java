/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.io.Serializable;

/**
 * Abstract class inherited by the Hero and Enemy class
 * @author Ajay
 */
public abstract class Character implements Serializable{
    
    /**
     * name - String variable representing the name of the character
     */
    private String name;
    
    /**
     * quip - String variable representing the catch phrase of the character
     */
    private String quip;
    
    /**
     * level - int variable representing the level of the character 
     */
    private int level;
    
    /**
     * hp - int variable representing the hp of the character
     */
    private int hp;
    
    /**
     * gold l- int variable representing the amount of gold the character has
     */
    private int gold;
    
    /**
     * Constructor to initialize the name, quip, hp, level, and gold value of 
     * a character
     * @param name String parameter passed to initialize the name of the character
     * @param quip String parameter passed to initialize the catchphrase of the 
     * character
     * @param hp int parameter passed to initialize the hp of the character
     * @param level int parameter passed to initialize the level of the character
     * @param gold  int parameter passed to initialize the gold value of the 
     * character
     */
    public Character(String name, String quip, int hp, int level, int gold){
        this.name=name;
        this.quip=quip;
        this.level=level;
        this.hp=hp;
        this.gold=gold;
    }
    
    /**
     * Method to return the name of the character
     * @return String value representing the name of the character
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method to return the catchphrase of the character
     * @return String value representing the catch phrase of the character
     */
    public String getQuip(){
        return quip;
    }
    
    /**
     * Method to return the hp of the character
     * @return int value representing the hp of the character
     */
    public int getHp(){
        return hp;
    }
    
    /**
     * Method to return the level of the character
     * @return int value representing the level of the character
     */
    public int getLevel(){
        return level;
    }
    
    /**
     * Method to return the gold amount of the character
     * @return int value representing the gold value of the character
     */
    public int getGold(){
        return gold;
    }
    
    /**
     * Method to increase the level of the character
     */
    public void increaseLevel(){
        level++;
    }
    
    /**
     * Method to heal the character after using the Health Potion
     * @param h int parameter representing the hp after healing 
     */
    public void heal(int h){
        hp = h;
    }
    
    /**
     * Method to decrease the hp of the character after an attack
     * @param h int parameter representing the damage level after an attack
     */
    public void takeDamage(int h){
        hp = hp -h;
    }
    
    /**
     * Method to increase the gold level of a character
     * @param g int parameter representing the increase in gold level
     */
    public void collectGold(int g){
        gold = gold+g;
    }
    
    /**
     * Method to increase the hp of the character after level up
     * @param level int value representing the level of the character
     */
    public void increaseHp(int level){
        hp = 20*level;
    }
    /**
     * Method to display the general information of a character
     */
    public void display(){
        System.out.println("\n"+name+" is level "+level);
        System.out.println(name+" has a health of "+hp+" points");
    }
    
    /**
     * abstract method to attack a character c 
     * @param c character which is being attacked by the character
     */
    public abstract void attack(Character c);
}
