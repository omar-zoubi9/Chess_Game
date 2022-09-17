package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.MoveBehavior.Movement.Move;

public class QueenMoveValidator implements MoveValidatorType {
    public static QueenMoveValidator queenMoveValidator = null;
    private QueenMoveValidator(){}
    public static QueenMoveValidator getInstance(){
        if(queenMoveValidator == null)
            queenMoveValidator = new QueenMoveValidator();
        return queenMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        if (move.isDestinationOnTop()) {
            move.setXYDirectionToTop();
        } else if (move.isDestinationOnBot()) {
            move.setXYDirectionToBot();
        } else if (move.isDestinationOnRight()) {
            move.setXYDirectionToRight();
        } else if (move.isDestinationOnLeft()) {
            move.setXYDirectionToLeft();
        } else if (move.isDestinationOnDiagonalTopRight()) {
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
