package Pieces;

import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;

public class Pawn extends ChessPiece{
    private boolean moved = false;
    boolean lastMoveWasDoubleMove = false;
    public Pawn(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public boolean isMoved() {
        return moved;
    }

    public void setMovedToTrue() {
        moved=true;
    }

    public void setLastMoveWasDoubleMove(boolean lastMoveWasDoubleMove) {
        this.lastMoveWasDoubleMove = lastMoveWasDoubleMove;
    }
    public boolean isLastMoveADoubleMove(){
        return lastMoveWasDoubleMove;
    }
    public String getPieceName() {
        return "Pawn";
    }
}
