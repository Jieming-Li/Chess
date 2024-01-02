//written by Jieming Li li002396

import java.lang.Math;

public class Knight {
    int row;
    int col;
    boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    //check the location of the destination by checking the difference of row and column
    // and the destination must be null or the other color
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        return (((Math.abs(endRow-this.row)==2 && Math.abs(endCol-this.col)==1) ||
        (Math.abs(endRow-this.row)==1 && Math.abs(endCol-this.col)==2)) && (board.getPiece(endRow, endCol) == null ||
                board.getPiece(endRow, endCol).getIsBlack() != this.isBlack));
        }

}
