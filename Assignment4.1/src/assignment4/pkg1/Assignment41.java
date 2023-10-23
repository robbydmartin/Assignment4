package assignment4.pkg1;

import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author Robby Martin
 */
public class Assignment41 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File file = new File("pokemon.txt");

        displayMenu(file);

    }

    public static void displayMenu(File file) {

        System.out.println("\n------------ MENU ------------");
        System.out.println("1. Display Pokemon collection");
        System.out.println("2. Add a Pokemon to your collection");
        System.out.println("3. Update Pokemon information");
        System.out.println("4. Delete Pokemon from collection");
        System.out.println("5. Exit");
        System.out.println();

        Scanner input = new Scanner(System.in);

        System.out.print("Please select a menu option: ");
        int choice = input.nextInt();

        if (choice < 1 || choice > 5) {
            System.out.println();
            System.out.print("Your selection is invalid. Please select again\n");
            displayMenu(file);
        } else if (choice == 1) {
            displayCollection(file);
        } else if (choice == 2) {
            addPokemon(file);
        } else if (choice == 3) {
            updatePokemon(file);
        } else if (choice == 4) {
            deletePokemon(file);
        } else {
            System.out.println("Goodbye, Trainer!");
        }
    }

    public static void addPokemon(File file) {

        System.out.println("\n------------ Add a Pokemon ------------");

        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            Scanner input = new Scanner(System.in);
            System.out.print("Name: ");
            String name = input.next();
            System.out.print("Type: ");
            String type = input.next();
            System.out.print("HP: ");
            String hp = input.next();

            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.write(type);
            bufferedWriter.newLine();
            bufferedWriter.write(hp);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Exception");
        }

        System.out.println("Pokemon has been sucessfully added");
        displayMenu(file);
    }

    public static void displayCollection(File file) {

        System.out.println("\n------------ Your Collection ------------");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("pokemon.txt"));
            String name;
            String type;
            String hp;
            System.out.printf("%-20s%-20s%-20s\n", "NAME", "TYPE", "HP");
            while ((name = bufferedReader.readLine()) != null) {
                System.out.printf("%-20s", name);
                System.out.printf("%-20s", type = bufferedReader.readLine());
                System.out.printf("%-20s\n", hp = bufferedReader.readLine());
            }
            bufferedReader.close();

        } catch (Exception e) {
        }

        displayMenu(file);
    }

    public static void updatePokemon(File file) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the Pokemon you wish to update: ");
        String name = input.next();
        
        List<String> fileContents = new ArrayList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.contains(name)) {
                    System.out.print("Enter the new name: ");
                    String newName = input.next();
                    System.out.print("Enter the new type: ");
                    String newType = input.next();
                    System.out.print("Enter the new HP: ");
                    String newHP = input.next();

                    fileContents.add(newName);
                    fileContents.add(newType);
                    fileContents.add(newHP);
                    
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                            
                    continue;
                }
                fileContents.add(s);
            }
            bufferedReader.close();

        } catch (Exception e) {
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String s : fileContents) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println("IO exception");
        }
        
        displayMenu(file);
    }

    public static void deletePokemon(File file) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the Pokemon you wish to delete: ");
        String name = input.next();

        List<String> fileContents = new ArrayList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.contains(name)) {
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    continue;
                }
                fileContents.add(s);
            }
            bufferedReader.close();

        } catch (Exception e) {
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String s : fileContents) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println("IO exception");
        }

        System.out.println("Pokemon has been sucessfully deleted");
        displayMenu(file);
    }
}
