package Pieces;

import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;
import InputManaging.SquareID;

public class King extends ChessPiece{
    SquareID kingSquareID;
    boolean moved = false;
    public King(int playerID, MoveValidatorType moveValidatorType, SquareID kingSquareID) {
        super(playerID , moveValidatorType);
        this.kingSquareID = kingSquareID;
    }
    public boolean isMoved(){
        return moved;
    }
    public void setMovedToTrue() {
        moved = true;
    }
    public String getPieceName() {
        return "King";
    }
}
