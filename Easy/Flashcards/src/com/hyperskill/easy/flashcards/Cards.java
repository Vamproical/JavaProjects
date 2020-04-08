package com.hyperskill.easy.flashcards;;

import java.util.*;

public class Cards {

    final Scanner scanner = new Scanner(System.in);
    Map<String, String> cards = new LinkedHashMap<>();
    Map<String, Integer> cardsWithMistaken = new LinkedHashMap<>();
    List<String> log = new ArrayList<>();
    final String input;
    final String output;
    Cards(String input, String output) {
        this.input = input;
        this.output = output;
    }
    /*
     * @param check is the Map of Cards
     * @param term is termination that it needs to find in Cards
     * @return true if cards contains the termination
     */
    private boolean isTerm(Map<String, String> check, String term) {
        return check.containsKey(term);
    }
    /*
     * @param check is the Map of Cards
     * @param definition is definition that it needs to find in Cards
     * @return true if cards contains the definition
     */
    private boolean isDefinition(Map<String, String> check, String definition) {
        return check.containsValue(definition);
    }
    /*
     * @param check is the Map of Cards
     * @param definition is definition that it needs to find in Cards
     * @return String definition if cards contains this definition
     */
    private String getTerm(Map<String, String> check, String definition) {
        for (var str : check.entrySet()) {
            if (str.getValue().equals(definition)) {
                return str.getKey();
            }
        }
        return null;
    }
    /*
     * @param check is String that we need to find in cardsWithMistaken
     * @return the number of errors for check
     * @return 0 if check is not found
     */
    private int getCountWrongAnswer(String check) {
        return cardsWithMistaken.getOrDefault(check,0);
    }
    /*
    *Add or remove one card from Cards
     */
    private void add() {
            System.out.println("The card:");
            log.add("The card:");
            String term = scanner.nextLine();
            if (isTerm(cards, term)) {
                System.out.println("The card \"" + term + "\" already exists.");
                log.add("The card \"" + term + "\" already exists.");
                return;
            }
            System.out.println("The definition of the card:");
            log.add("The definition of the card:");
            String definition = scanner.nextLine();
            if (isDefinition(cards, definition)) {
                System.out.println("The definition \"" + definition + "\" already exists.");
                log.add("The definition \"" + definition + "\" already exists.");
                return;
            }
            int mistaken = 0;
            cards.put(term, definition);
            cardsWithMistaken.put(term,mistaken);
            System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            log.add("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }
    private void remove() {
        System.out.println("The card:");
        log.add("The card:");
        String removePair = scanner.nextLine();
        if(cards.containsKey(removePair)) {
            cards.remove(removePair);
            cardsWithMistaken.remove(removePair);
            System.out.println("The card has been removed.");
            log.add("The card has been removed.");
        }
        else {
            System.out.println("Can't remove \"" + removePair + "\": there is no such card.");
            log.add("Can't remove \"" + removePair + "\": there is no such card.");
        }

    }
    /*
    *ask for a definition of some random cards
     */
    private void ask() {
        System.out.println("How many times to ask?");
        log.add("How many times to ask?");
        int countAsk = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countAsk; i++) {
            askForRandom();
        }
    }
    private void askForRandom() {
        Random random = new Random();
        int ran = random.nextInt(cards.size());
        Map.Entry<String, String> result = resultRandom(ran);
        String compare;
        assert result != null;
        System.out.println("Print the definition of \"" + result.getKey() + "\"" + ":");
        log.add("Print the definition of \"" + result.getKey() + "\"" + ":");
        compare = scanner.nextLine();
        if (result.getValue().equals(compare)) {
            System.out.println("Correct answer.");
            log.add("Correct answer.");
        } else if (getTerm(cards, compare) != null) {
            System.out.println("Wrong answer. The correct one is " + "\"" + result.getValue() + "\", you've just written the definition of "
                    + "\"" + getTerm(cards, compare) + "\".");
            log.add("Wrong answer. The correct one is " + "\"" + result.getValue() + "\", you've just written the definition of "
                    + "\"" + getTerm(cards, compare) + "\".");
        } else {
            cardsWithMistaken.put(result.getKey(), getCountWrongAnswer(result.getKey()) + 1);
            System.out.println("Wrong answer. The correct one is " + "\"" + result.getValue() + "\"");
            log.add("Wrong answer. The correct one is " + "\"" + result.getValue() + "\"");
        }
    }
    private Map.Entry<String, String > resultRandom(int random) {
        for (var pair : cards.entrySet()) {
            if (random-- <= 0) {
                return pair;
            }
        }
        return null;
    }
    /*
    *import or export file with Cards
     */
    private void importCards() {
        System.out.println("File name:");
        log.add("File name:");
        String inputFile = scanner.nextLine();
        IOcard.load(cards,cardsWithMistaken,log,inputFile);
    }
    private void exportCards() {
        System.out.println("File name:");
        log.add("File name:");
        String inputFile = scanner.nextLine();
        IOcard.store(cards, cardsWithMistaken,log,inputFile);
    }
    /*
    * Save logs to the specified file
     */
    private void log() {
        System.out.println("File name:");
        log.add("File name:");
        String logInput = scanner.nextLine();
        IOcard.store((ArrayList<String>) log,logInput);
    }
    /*
    * Print the hardest card
     */
    private void hardCard() {
        if (cardsWithMistaken.size() == 0) {
            System.out.println("There are no cards with errors.");
            log.add("There are no cards with errors.");
        }
        else {
            ArrayList<String> maxMistakenName = new ArrayList<>();
            int max = 0;
            for (var entry: cardsWithMistaken.entrySet()) {
                if (entry.getValue() > max) {
                    maxMistakenName.clear();
                    maxMistakenName.add(entry.getKey());
                }
                else if (entry.getValue() == max) {
                    maxMistakenName.add(entry.getKey());
                }
                max = Math.max(max, entry.getValue());
            }
            if (maxMistakenName.size() == 1) {
                System.out.println("The hardest card is \"" + maxMistakenName.get(0) + "\". You have " + max + " errors answering it.");
                log.add("The hardest card is \"" + maxMistakenName.get(0) + "\". You have " + max + " errors answering it.");
            }
            else {
                System.out.print("The hardest cards are ");
                log.add("The hardest cards are ");
                for (String str: maxMistakenName) {
                    System.out.print("\"" + str + "\",");
                    log.add("\"" + str + "\",");
                }
                System.out.println(". You have " + max + " errors answering them.");
                log.add(". You have " + max + " errors answering them.");
            }
        }

    }
    /*
    * Reset the number of errors for each cards
     */
    private void reset() {
        cardsWithMistaken.clear();
        System.out.println("Card statistics has been reset.");
        log.add("Card statistics has been reset.");
    }

    public void gameStart() {
        String type;
        boolean flag = true;
        if (!input.equals("")) IOcard.load(cards,cardsWithMistaken,log,input);
        while (flag) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            log.add("Input the action (add, remove, import, export, ask, exit):");
            type = scanner.nextLine();
            switch (type) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "import":
                    importCards();
                    break;
                case "export":
                    exportCards();
                    break;
                case "ask":
                    ask();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    if(!output.equals("")) IOcard.store(cards, cardsWithMistaken,log,output);
                    log.add("Bye bye!");
                    flag = false;
                    break;
                case "log":
                    log();
                    break;
                case "hardest card":
                    hardCard();
                    break;
                case "reset stats":
                    reset();
                    break;
            }
        }
    }
}
