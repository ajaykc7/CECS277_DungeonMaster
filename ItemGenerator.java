/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The class helps to read the Item file and generate Items.
 * @author Ajay
 */
public class ItemGenerator implements Serializable{

    /**
     * ItemList - ArrayList that contains the list of item
     */
    private ArrayList<Item> itemList;

    /**
     * Method to initialize the arrayList by reading the input from the ItemList
     * file.
     */
    public ItemGenerator() {
        
        itemList = new ArrayList<Item>();
        try {
            Scanner itemreader = new Scanner(new File("ItemList.txt"));
            do {
                String itemline = itemreader.nextLine();
                String itemname = itemline.substring(0, itemline.indexOf(","));
                String itemvalue = itemline.substring(itemline.indexOf(",")+1);
                itemList.add(new Item(itemname, Integer.parseInt(itemvalue)));
                } while (itemreader.hasNext());
            itemreader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to return a random item from the arrayList
     * @return item at a random index point of the arrayList
     */
    public Item generateItem() {

        Random random = new Random();
        int index = random.nextInt(itemList.size());
        return itemList.get(index);
    }
}
