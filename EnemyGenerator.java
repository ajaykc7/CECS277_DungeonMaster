/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The class helps to read the Enemy file and generate Enemy.
 * @author Ajay
 */
public class EnemyGenerator {

    private ArrayList<Enemy> enemyList;
    Random random = new Random();

    /**
     * Method to initialize the arrayList by reading the input from the EnemyList
     * file.
     */
    public EnemyGenerator() {

        ItemGenerator itmgenerator = new ItemGenerator();
        enemyList = new ArrayList<Enemy>();
        try {
            Scanner enemyreader = new Scanner(new File("EnemyList.txt"));
            do {
                String enemyline = enemyreader.nextLine();
                String[] enemytokens = enemyline.split(",");
                String enemyname = enemytokens[0];
                String enemyquip = enemytokens[1];
                String enemyhp = enemytokens[2];
                Item inheriteditem = itmgenerator.generateItem();
                //int level = random.nextInt(3) + 1;
                int level = 0;
                enemyList.add(new Enemy(enemyname, enemyquip, Integer.parseInt(enemyhp), level, inheriteditem.getValue(), inheriteditem));
            } while (enemyreader.hasNext());
            enemyreader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to return a random enemy from the arrayList
     * @return enemy at a random index point of the arrayList
     */
    public Enemy generateEnemy(int level) {
        
        ItemGenerator itmgenerator = new ItemGenerator();
        int index=0;
        //boolean isSuccess = false;
        //while (isSuccess == false) {
            index = random.nextInt(enemyList.size());
            //if (enemyList.get(index).getLevel() <= level) {
              //  isSuccess = true;
            //} else {
              //  isSuccess = false;
            //}
        //}
        Random random = new Random();
        int gold = random.nextInt(100);
        Item i = itmgenerator.generateItem();
        Enemy e = new Enemy(enemyList.get(index).getName(),enemyList.get(index).getQuip(),enemyList.get(index).getHp(),level,gold,i);
        return e;

    }
}
