package com.hyperskill.easy.flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOcard {
    /*
    * @param insertFile is the file with cards
    * @param insertFileMistaken is the file with the number of errors on each map
    * @param log is the file with logs
    * @param importPath is the path where the cards save
    * @return exception if the file is missing
    */
    public static void load(Map<String,String> insertFile,Map<String,Integer> insertFileMistaken, List<String> log, String importPath) {
        int counter = 0;
        String question, answer;
        String  mistaken = "0";
        try (final Scanner scan = new Scanner(new File(importPath))) {
            while (scan.hasNext()) {
                question = scan.nextLine();
                answer = scan.nextLine();
                mistaken = scan.nextLine();
                insertFile.put(question, answer);
                insertFileMistaken.put(question,Integer.valueOf(mistaken));
                counter++;
            }
            System.out.println(counter + " cards have been loaded.");
            log.add(counter + " cards have been loaded.");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
            log.add("File not found.");
        }
    }
    /*
     * @param store is the file with cards
     * @param insertFile is the file with the number of errors on each map
     * @param log is the file with logs
     * @param exportPath is the path where the cards need to save
     * @return exception if the file is missing
     */
    public static void store(Map<String, String> store, Map<String,Integer> insertFile, List<String> log,String exportPath) {
        File file = new File(exportPath);
        try (var writer = new FileWriter(file)){
            for(var Store: store.entrySet()) {
                writer.write(Store.getKey() + "\n");
                writer.write(Store.getValue() + "\n");
                writer.write(insertFile.get(Store.getKey()) + "\n");

            }
            System.out.println(store.size() + " cards have been saved.");
            log.add(store.size() + " cards have been saved.");
        } catch (IOException e){
            System.out.println("Error while writing file");
            log.add("Error while writing file");
        }
    }
    /*
     * @param outputLog is the file with logs
     * @param outputFile is the path where logs need to save
     * @return exception if the file is missing
     */
    public static void store(ArrayList<String> outputLog, String outputFile) {
        File file = new File(outputFile);
        try (var writer = new FileWriter(file)){
            for(String str: outputLog) {
                writer.write(str + "\n");

            }
            System.out.println("The log has been saved.");
        } catch (IOException e){
            System.out.println("Error while writing file");
        }
    }
}
