//written by Jieming Li

import java.lang.Math;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board(int row, int col) {
        board = new Piece[col][row];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[col][row];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[col][row] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startCol][startRow]!=null && board[startCol][startRow].isMoveLegal(this, endRow, endCol)){
            this.setPiece(endRow,endCol,board[startCol][startRow]);
            board[endCol][endRow].setPosition(endRow,endCol); //set the piece to the destination and change its location information
            board[startCol][startRow] = null;
            return true;
        }else{
            return false;
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        // to keep track of if either king has been captured
        boolean whiteLost = true;
        boolean blackLost = true;
        //iterate through the array, if find king then return false
        for (int i = 0; i<this.board.length; i++){
            for (int j = 0; j<this.board[0].length; j++) {
                if (board[i][j] != null) {
                    char character = board[i][j].getCharacter();
                    if (character == '\u265a') {
                        blackLost = false;
                    }else if(character == '\u2654'){
                        whiteLost = false;
                    }
                }
            }
        }
        if (!blackLost && !whiteLost) {
            return false;
        }else{
            return true;
        }
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String stringBoard = "  ";
        for (int r = 0; r<8; r++){
            stringBoard = stringBoard + r + " ";
        }
        stringBoard += "\n";
        for (int i = 0; i < this.board[0].length; i++) {
            stringBoard += i;
            for (int j = 0; j < this.board.length; j++) {
                if (board[j][i] == null) {
                    stringBoard = stringBoard + "|" + " ";
                } else {
                    stringBoard = stringBoard + "|" + board[j][i].getCharacter();
                }
            }
            stringBoard = stringBoard + "|" + "\n";
        }
        return stringBoard;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i<this.board.length; i++){
            for (int j = 0; j<this.board[0].length; j++){
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        return (0 <= startRow && startRow < 8 && 0 <= startCol && startCol < 8 && 0 <= endRow && endRow < 8
                && 0 <= endCol && endCol < 8 && board[startCol][startRow] != null
                && board[startCol][startRow].getIsBlack() == isBlack
                && (board[endCol][endRow] == null || board[endCol][endRow].getIsBlack() != isBlack));
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDifference = startRow - endRow;
        int colDifference = startCol - endCol;
        return (-1 <= rowDifference && rowDifference <= 1 && -1 <= colDifference && colDifference <= 1);
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        //check if they are on the same row, then find if there is a piece in between
        if (endRow - startRow == 0){
            if (startCol < endCol){
                for (int i = startCol + 1; i < endCol; i++){
                    if (board[i][startRow] != null){
                        return false;
                    }
                }
                return true;
            }else if (endCol < startCol){
                for (int i = endCol + 1; i < startCol; i++){
                    if (board[i][startRow] != null){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        //check if they are on the same column, then find if there is a piece in between
        if (endCol - startCol == 0){
            if (startRow < endRow){
                for (int i = startRow + 1; i < endRow; i++){
                    if (board[startCol][i] != null){
                        return false;
                    }
                }
                return true;
            }else if (endRow < startRow){
                for (int i = endRow + 1; i < startRow; i++){
                    if (board[startCol][i] != null){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        //check if the start position and the end position are on the same diagonal that goes the
        // up left to the down right direction
        if (startCol - startRow == endCol - endRow) {
            for (int i = Math.min(startCol, endCol) + 1; i < Math.max(startCol, endCol); i++) {
                if (board[i][Math.min(startRow, endRow) + i - Math.min(startCol, endCol)] != null) {
                    return false;
                }
            }
            return true;
            // the other case where it goes down left to up right direction
        }else if (startCol + startRow == endCol + endRow) {
            for (int i = Math.min(startCol, endCol) + 1; i < Math.max(startCol, endCol); i++) {
                if (board[i][Math.max(startRow, endRow) - i + Math.min(startCol, endCol)] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
