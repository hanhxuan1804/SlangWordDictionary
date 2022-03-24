package com.studenthcmus;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	SlangWordApp app = new SlangWordApp();
    app.loadData("slang.txt");
    app.run();
    app.saveData("backup.txt");
    }
}

class SlangWordApp{
    SlangWordList slangWordList = new SlangWordList();
    public void loadData(String fileName){
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            String line = in.readLine();
            while(line != null){
                String[] tokens = line.split("`");
                slangWordList.add(tokens[0], tokens[1]);
                line = in.readLine();
            }
            in.close();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void saveData(String fileName){
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            for(String word : slangWordList.slangWords.keySet()){
                
                out.writeBytes(word + "`" + slangWordList.slangWords.get(word) + "\n");
            }
            out.close();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void run(){
        int id =0 ;
        do {
            clearScreen();
            System.out.println("Welcome to Slang Word List Application");
            System.out.println("1. Search by slang word");
            System.out.println("2. Search by definition");
          
            if(id<0||id>10) {
                System.out.print("Invalid choice! Please enter your choice again: ");
            }
            else
            {
                System.out.print("Enter your choice: ");
            }

            BufferedReader bReader;
            try {
                bReader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
                String choice = bReader.readLine();
                if(isNumeric(choice)) {
                    id = Integer.parseInt(choice);
                }
                else{
                    id=-1;
                }
                switch (id) {
                    case 1:
                        clearScreen();
                        SearchSlangWord();
                        break;
                    case 2:
                        clearScreen();
                        SearchDefinition();
                        break;
                    default:
                        break;
                }
        } catch (  IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        } while (id<0||id>5);
    }
       

    private void SearchDefinition() {
        clearScreen();
        System.out.print("Enter the definition you want to search: ");
        BufferedReader bReader;
        try {
            bReader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
            String definition = bReader.readLine();
            SlangWordList result = slangWordList.search(definition);
            if(result.size() == 0) {
                System.out.println("No result found!");
            }
            else {
                System.out.println("Found " + result.size() + " results:");
                System.out.println(result);
            }
            System.out.println("Press Enter to continue...");
            bReader.readLine();
            run();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void SearchSlangWord() {
        clearScreen();
        System.out.println("Search a slang word");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = scanner.nextLine();
        if(slangWordList.get(word) != null) {
            System.out.println("Definition: " + slangWordList.get(word));
        }
        else {
            System.out.println("Not found!");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        run();
    }

    private boolean isNumeric(String readLine) {
        if(readLine.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
