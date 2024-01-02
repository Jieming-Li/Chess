//written by Jieming Li li002396

import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Board myBoard = new Board(8,8);
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        System.out.println(myBoard);
        //if status is true it is white's turn to play, otherwise it is black's turn to play
        boolean status = true;
        while (!myBoard.isGameOver()){
            if (status){
                System.out.println("It is currently white's turn to play");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner myScanner = new Scanner(System.in);
                String input = myScanner.nextLine();
                String[] inputArray = input.split(" ");
                if (myBoard.verifySourceAndDestination(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
                        Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]),false) &&
                        myBoard.movePiece(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
                                Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]))){
                    //switch status so it is the next person's turn to play
                    status = false;
                    System.out.println(myBoard);
                }else{
                    System.out.println("illegal move, please try again");
                }
            }else{
                System.out.println("It is currently black's turn to play");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner myScanner = new Scanner(System.in);
                String input = myScanner.nextLine();
                String[] inputArray = input.split(" ");
                if (myBoard.verifySourceAndDestination(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
                        Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]),true) &&
                        myBoard.movePiece(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
                                Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]))){
                    status = true;
                    System.out.println(myBoard);
                }else{
                    System.out.println("illegal move, please try again");
                }
            }
        }
        //check the status, if false then white has won
        if (!status){
            System.out.println("White has won the game!");
        }else{
            System.out.println("Black has won the game!");
        }
    }
}
