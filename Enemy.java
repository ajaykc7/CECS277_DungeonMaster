/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

/**
 * The class Hero consists all the methods related to a Enemy and is inherited
 * from Character class.
 * @author Ajay
 */
public class Enemy extends Character{
    
    /**
     * items - ArrayList to store the items that Enemy has
     */
    private Item item;
    
    //Creating a new object of utility class to use methods inside the class
    Utility utility = new Utility();
    
    /*
    **
     * Originalhp - int variable representing the original hp of the enemy
     *
    private int Originalhp;
    */
    
    /**
     * Constructor to initialize the name, quip,hp, level, amount of gold and the 
     * item of the enemy
     * @param name String parameter that initializes the name of the enemy
     * @param quip String parameter that initializes the catch phrase of the enemy
     * @param hp int parameter that initializes the hp of the enemy
     * @param level int parameter that initializes the level of the enemy
     * @param goldValue int parameter that initializes the gold that enemy carries
     * @param i Item that enemy possesses
     */
    public Enemy (String name, String quip, int hp, int level, int goldValue, Item i){
        super(name,quip,hp,level,goldValue);
        item = i;
        //Originalhp=hp;
    }
    
    /**
     * Method to return the item the enemy has
     * @return the item that enemy carries
     */
    public Item getItem(){
        return item;
    }

    /**
     * Method to return the Original full hp of the enemy
     * @return int value representing the original hp of the enemy
     *
    public int getOriginalHp(){
        return Originalhp;
    }
    */
    
    /**
     * Method to attack the character c
     * @param c Character which is being attacked
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
        if(isHit){
            
            int hitPercent = utility.getHitPercent(getLevel());
            
            c.takeDamage(hitPercent);
            
            System.out.println("\n"+getName()+ " hits "+c.getName()+ " for "+ hitPercent+ " damage.");
        } else {
            System.out.println("\n"+c.getName()+" dodges the attack");
            System.out.println("\n"+c.getName()+" says "+ c.getQuip());
        }
    }
    
}
