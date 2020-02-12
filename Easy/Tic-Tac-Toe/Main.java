package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] table = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        printTable(table);
        char player = 'X';
        String res = checkRes(table);
        while (res.equals("Game not finished")) {
            setNum(table,player);
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
            res = checkRes(table);
        }
        System.out.println(checkRes(table));
    }
    public static void setNum (char[][] chars, char player) {
        final Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the coordinates: ");
            String x1 = scanner.next();
            String y1 = scanner.next();
            int x,y;
            try {
                x = Integer.parseInt(x1) - 1;
                y = Integer.parseInt(y1) - 1;
                if (x > 2 || x < 0 || y < 0 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (chars[2 - y][x] == ' ') {
                        chars[2 - y][x] = player;
                        printTable(chars);
                        flag = false;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
    public static String checkRes(char[][] chars) {
        if (isWin(chars, 'X') && isWin(chars,'O') || isImpossible(chars)) {
            return "Impossible";
        }
        else if (!isWin(chars, 'X') && !isWin(chars,'O') && isEmpty(chars)) {
            return "Game not finished";
        }
        else if (!isEmpty(chars) && !isWin(chars,'X') && !isWin(chars, 'O')) {
            return "Draw";
        }
        if (isWin(chars, 'O')) {
            return "O wins";
        }
        else if (isWin(chars, 'X')) {
           return "X wins";
        }
        return "";
    }
    public static void printTable(char[][] chars) {
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(chars[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static boolean isEmpty(char[][] chars) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (chars[i][j] == ' ') {
                    return  true;
                }
            }
        }
        return false;
    }
    public static boolean isWin(char[][] chars, char value) {
        boolean horizontal = (value == chars[0][0] && value == chars[0][1] && value == chars[0][2]) ||
                (value == chars[1][0] && value == chars[1][1] && value == chars[1][2]) ||
                (value == chars[2][0] && value == chars[2][1] && value == chars[2][2]);
        boolean vertical = (value == chars[0][0] && value == chars[1][0] && value == chars[2][0]) ||
                (value == chars[0][1] && value == chars[1][1] && value == chars[2][1]) ||
                (value == chars[0][2] && value == chars[1][2] && value == chars[2][2]);
        boolean diagonal = (value == chars[0][0] && value == chars[1][1] && value == chars[2][2]) ||
                (value == chars[0][2] && value == chars[1][1] && value == chars[2][0]);
        return horizontal || vertical || diagonal;
    }
    public static boolean isImpossible(char[][] chars) {
        int countX = 0, countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (chars[i][j] == 'X') {
                    ++countX;
                } else if (chars[i][j] == 'O') {
                    ++countO;
                }
            }
        }
        return countX - countO >= 2 || countO - countX >= 2;
    }
}
