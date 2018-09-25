/* Nicolas dos Santos
Data Structures
Recursion Lab

Sudoku Solver:
This program solves a sudoku
puzzle using a backtracking
algorithm.

Last modified on 03/17


 */


package com.nicolasDosSantos;


public class Main {
    //Method calls another solve method with row 0 and col 0
    public static void solve(int[][] game) {
        solve(game, 0, 0);
    }

    private static boolean solve(int game[][], int row, int col) {

        //if column exceeds board length, go to next row and 0th column
        if (col >= game.length) {
            col = 0;
            row++;
            //If row exceeds board length puzzle is solved, return true
            if (row >= game.length) {
                return true;
            }
        }

        //if spot is already filled with a number skip it
        if (game[row][col] != 0) {
            solve(game, row, col + 1);
        }


        for (int i = 0; i <= game.length; i++) {
            if (isSafe(game, row, col, i)) {

                game[row][col] = i;
                //Goes through board and if possible to solve all cells, returns true and puzzle is solved
                if (solve(game, row, col + 1)) {
                    return true;
                } //try next number
            }
        }
        //Else we assign it to zero and backtrack
        game[row][col] = 0;
        return false;


    }


    //Return true if x is on a safe column, row and square
    private static boolean isSafe(int game[][], int row, int col, int x) {
        return isColSafe(game, row, col, x) && isRowSafe(game, row, col, x) && isSquareSafe(game, row, col, x);

    }

    //Verifies if col is safe
    private static boolean isColSafe(int game[][], int row, int col, int x) {
        for (int i = 0; i < game.length; i++) {
            if (game[row][i] == x) {
                return false;
            }
        }

        return true;
    }

    //Verifies if row is safe
    private static boolean isRowSafe(int game[][], int row, int col, int x) {
        for (int i = 0; i < game.length; i++) {
            if (game[i][col] == x) {
                return false;
            }
        }

        return true;
    }

    //verify if square is safe
    private static boolean isSquareSafe(int game[][], int row, int col, int x) {

        int rSquare = (row / 3) * 3;
        int rCol = (col / 3) * 3;

        //Loop through square  to verify if x has any other occurrences
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (game[rSquare + i][rCol + j] == x) {
                    return false;
                }
            }
        }

        //If x is unique within square return true
        return true;
    }

    //Prints array
    public static void printArray(int game[][]) {

        System.out.println("------------------------");
        for (int i = 0; i < game.length; i++) {
            System.out.print("|");
            for (int j = 0; j < game.length; j++) {
                if (j > 0 && j % 3 == 0) {
                    System.out.print("| " + game[i][j] + " ");
                } else {
                    System.out.print(game[i][j] + " ");
                }


            }
            System.out.print("|");
            System.out.println("");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("------------------------");
            }
        }
    }


    public static void main(String[] args) {
        int game[][] = {{8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0},

        };


        solve(game);
        printArray(game);


    }
}

