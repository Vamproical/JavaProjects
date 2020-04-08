package com.hyperskill.easy.flashcards;;
public class Main {
    public static void main(String[] args) {
        String importPath = "";
        String exportPath = "";
        for (int i = 0; i < args.length; i++) {
            int k = i;
            if (args[i].equals("-import")) {
                importPath = args[k + 1];
            }
            if (args[i].equals("-export")) {
                exportPath = args[k+1];
            }
        }
        Cards card = new Cards(importPath, exportPath);
        card.gameStart();
    }
}

