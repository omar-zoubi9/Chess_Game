package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.Pawn;
import Pieces.MoveBehavior.Movement.Move;

public class WhitePawnMoveValidator implements MoveValidatorType {
    public static WhitePawnMoveValidator whitePawnMoveValidator = null;
    private WhitePawnMoveValidator(){}
    public static WhitePawnMoveValidator getInstance(){
        if(whitePawnMoveValidator == null)
            whitePawnMoveValidator = new WhitePawnMoveValidator();
        return whitePawnMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Pawn currentPawn = (Pawn) move.getPieceToBeMoved();
        if ( move.isDestOneMoveAwayOnTop() &&
                !move.isThereAnEnemyOnDestination()) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }else if(move.isDestTwoMovesAwayOnTop() && !currentPawn.isMoved() &&
                    !move.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(true);
            currentPawn.setMovedToTrue();
            return true;
        } else if ( (move.isDestOneMoveAwayOnTopRight() || move.isDestOneMoveAwayOnTopLeft())
                && move.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }
        return false;
    }
}
