package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import myClasses.*;
public class PlayerController{

    public static ArrayList<String []> dbPlayerList = new ArrayList();

    public static void savePlayer(Player player, String filename) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter("playerDatabase.txt", true));
        String playerName = player.getName();
        String password = player.getPassword();
        // writer.println(playerName + "," + password);
        String data = playerName + "," + password + "," + player.getScore();
        writer.println(data);
        System.out.println("New player successfully saved to database");
        writer.close();
    }

    public static void readData() throws IOException {
        dbPlayerList.clear();
        File dbFile = new File("playerDatabase.txt");
        try (Scanner reader = new Scanner(dbFile)) {
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] dbArray = currentLine.split(",");
                if (dbArray.length <= 3) { 
                    dbPlayerList.add(dbArray);
                } else {
                    System.out.println("Invalid data format: " + currentLine);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String []> getDbPlayerList(){
        return dbPlayerList;
    } 

    public static void updatePlayerScore(Player player, int score, boolean returningPlayer) throws IOException{
        ArrayList<String> lines = new ArrayList<>();
        Scanner reader = new Scanner(new File("playerDatabase.txt"));
        System.out.println("Now updating score for player " + player.getName());
         
        while (reader.hasNextLine()){
            String line="";
            String currentLine = reader.nextLine();
            String [] sArray = currentLine.split(",");
            System.out.print("This is the sArray name " + sArray[0]);
            if (sArray[0].equals(player.getName())){
                int highScore = Integer.parseInt(sArray[2]);
                if (score > highScore){
                    line = player.getName() + "," + player.getPassword() + "," + score;
                }
                else {
                    line = currentLine;
                }
                System.out.println("Should work for returning players");
            }
            else {
                line = currentLine;
            }
            lines.add(line);
            System.out.println("Added line to the arraylist lines");
        }
        reader.close();
        System.out.println("Before writing to testing file, here's the arraylist " + lines);

        PrintWriter writer = new PrintWriter(new FileWriter("playerDatabase.txt"));
        for (String s:lines){
            writer.println(s);
        }
        writer.close();
        System.out.println("Data wrote to testing");
    }
}
