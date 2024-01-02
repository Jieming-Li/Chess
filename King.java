//written by Jieming Li li002396

public class King {
    int row;
    int col;
    boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //check if the destination is adjacent to the starting point and the destination is null or the other color
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        return board.verifyAdjacent(row,col,endRow,endCol) && (board.getPiece(endRow, endCol) == null ||
                board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
    }
}
