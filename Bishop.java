//written by Jieming Li li002396

public class Bishop {
    int row;
    int col;
    boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    //check diagonal movement and the destination must be null or the other color
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        return board.verifyDiagonal(this.row, this.col, endRow, endCol) &&
                (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
    }
}
