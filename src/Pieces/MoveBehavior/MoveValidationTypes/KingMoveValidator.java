package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.King;
import Pieces.MoveBehavior.Movement.Move;

public class KingMoveValidator implements MoveValidatorType {
    public static KingMoveValidator kingMoveValidator = null;
    private KingMoveValidator(){}
    public static KingMoveValidator getInstance(){
        if(kingMoveValidator == null)
            kingMoveValidator = new KingMoveValidator();
        return kingMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        King currentKing = (King) move.getPieceToBeMoved();
        if(move.isDestOneSquareAway()){
            currentKing.setMovedToTrue();
            return true;
        }
        return false;
    }
}
