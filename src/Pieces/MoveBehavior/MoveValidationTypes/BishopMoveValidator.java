package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.MoveBehavior.Movement.Move;

public class BishopMoveValidator implements MoveValidatorType {
    public static BishopMoveValidator bishopMoveValidator = null;
    private BishopMoveValidator(){}
    public static MoveValidatorType getInstance(){
        if(bishopMoveValidator == null)
            bishopMoveValidator = new BishopMoveValidator();
        return  bishopMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        if (move.isDestinationOnDiagonalTopRight()) {
            move.setXYDirectionToDiagonalTopRight();
        } else if (move.isDestinationOnDiagonalTopLeft()) {
            move.setXYDirectionToDiagonalTopLeft();
        } else if (move.isDestinationOnDiagonalBotRight()) {
            move.setXYDirectionToDiagonalBotRight();
        } else if (move.isDestinationOnDiagonalBotLeft()) {
            move.setXYDirectionToDiagonalBotLeft();
        } else {
            return false;
        }
        return move.isWayFreeTillDestination();
    }
}
