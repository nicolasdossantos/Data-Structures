package com.nicolasDosSantos;


public class Board {
    private int board[][];
    private int queens;


    //constructor
    public Board(){
        queens = 0;
        board = new int[8][8];
    }


    public void start(){
        solve(0);

    }


    private boolean solve(int queens) {
       //Base case 8 queens are placed on the board print it
        if (queens == 8) {
            System.out.println("Done");
            printBoard();

            return true;

        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //If move is safe place a queen on board
                    if (isSafe(i, j) == 0) {
                        this.placeQueen(i, j, 0);
                        queens++;
                        if (solve(queens)) {
                            return true;
                        } else {
                            this.placeQueen(i, j, 1);
                            queens--;
                        }
                    }
                }
            }
            return false;
        }
    }

        private int isSafe(int row, int col) {
            int deltaRow;
            int deltaCol;


            for (int i = 0; i < 8; i++) {

                if (get(row, i) == 1) {
                    return -1;
                }

                if (get(i, col) == 1) {
                    return -1;
                }

            }

            for (int j = 0; j < 8; j++) {
                if (get(row - j, col - j) == 1) {
                    return -1;
                }
                if (get(row - j, col + j) == 1) {
                    return -1;
                }
                if (get(row + j, col - j) == 1) {
                    return -1;
                }

                if (get(row + j, col + j) == 1) {
                    return -1;
                }


//                deltaRow = Math.abs(get(row - j, col -j);
//                deltaCol = Math.abs(col - j);
//
//                if (deltaRow == deltaCol) {
//                    return -1;
//                }
////

            }
            return 0;
        }



    private void placeQueen(int row, int col, int move){
        if(move == 0){
            board[row][col] = 1;
            queens ++;

        } else if(move == 1){
            board[row][col] = 0;

        }else {
            System.out.println("ERROR");
        }
    }

    private int get(int row, int col){
       if(row < 0 || col < 0 || row > 7 || col >7){
           return -1;
       }


        return board[row][col];
    }

    private void printBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
               if(this.get(i, j) == 1){
                   System.out.print("| Q");
               }else{
                   System.out.print("|  ");
               }


            }
            System.out.println("|");
        }

    }
}
