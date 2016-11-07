/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonmaster;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * The class controls the level pattern of the project
 *
 * @author Ajay
 */
public class Level implements Serializable {

    /**
     * level - char variable of two dimension representing the room type in each
     * level
     */
    private char[][] level;

    /**
     * Constructor to initialize the dimension of the room
     */
    public Level() {
        level = new char[4][4];
    }

    /**
     * Method to generate different level based on the level of the hero. The
     * method takes the input from the level files and stores it in the level
     * variable
     *
     * @param levelNum int value representing the level of the hero
     */
    public void generateLevel(int levelNum) {

        File level_file = null;

        switch (levelNum) {
            case 1:
                level_file = new File("Level1.txt");
                break;
            case 2:
                level_file = new File("Level2.txt");
                break;
            case 3:
                level_file = new File("Level3.txt");
                break;
            default:
                level_file = new File("Level1.txt");
                break;
        }

        try {
            Scanner filereader = new Scanner(level_file);
            int row = 0;
            do {
                String line = filereader.nextLine();
                String[] elements = line.split(" ");
                for (int column = 0; column < 4; column++) {
                    level[row][column] = (elements[column].charAt(0));
                }
                row++;
            } while (filereader.hasNext());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to display the map at a particular level
     *
     * @param p Point representing the location of the hero
     */
    public void displayMap(Point p) {

        int x_cordinate = (int) p.getX();
        int y_cordinate = (int) p.getY();
        System.out.println("\nDungeon Map");
        System.out.println("- - - - - - - - - -");
        for (int i = 0; i < 4; i++) {
            System.out.print("| ");
            for (int j = 0; j < 4; j++) {
                if ((x_cordinate == i) && (y_cordinate == j)) {
                    System.out.print("*   ");
                } else {
                    System.out.print(level[i][j] + "   ");
                }
            }
            System.out.print("|");
            System.out.println("\n");
        }
        System.out.println("- - - - - - - - - -");
    }

    /**
     * Method to return the starting location of the hero
     * @return Point representing the coordinate of the hero
     */
    public Point getStartLocation(){
        for(int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if (level[i][j]=='s'){
                    return new Point(i,j);
                }
                }
            }
        return new Point(0,0);
        }
    /**
     * Method to return the char value representing the room type at a particular 
     * point    
     * @param p Point representing the coordinate of the hero
     * @return char value representing the room type
     */
    public char getRoom(Point p) {

        return level[(int) p.getX()][(int) p.getY()];
    }
}
