package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.Rook;
import Pieces.MoveBehavior.Movement.Move;

public class RookMoveValidator implements MoveValidatorType {
    public static RookMoveValidator rookMoveValidator = null;
    private RookMoveValidator(){}
    public static RookMoveValidator getInstance(){
        if(rookMoveValidator == null)
            rookMoveValidator = new RookMoveValidator();
        return rookMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Rook currentRook = (Rook) move.getPieceToBeMoved();
        if (move.isDestinationOnTop()) {
            move.setXYDirectionToTop();
        } else if (move.isDestinationOnBot()) {
            move.setXYDirectionToBot();
        } else if (move.isDestinationOnRight()) {
            move.setXYDirectionToRight();
        } else if (move.isDestinationOnLeft()) {
            move.setXYDirectionToLeft();
        } else {
            return false;
        }
        if(move.isWayFreeTillDestination()){
            currentRook.setMovedToTrue();
            return true;
        }
        return false;
    }
}
