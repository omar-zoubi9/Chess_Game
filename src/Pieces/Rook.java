package Pieces;

import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;

public class Rook extends ChessPiece{
    boolean moved = false;
    public Rook(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public boolean isMoved(){
        return moved;
    }
    public void setMovedToTrue(){
        moved = true;
    }
    public String getPieceName() {
        return "Rook";
    }
}
