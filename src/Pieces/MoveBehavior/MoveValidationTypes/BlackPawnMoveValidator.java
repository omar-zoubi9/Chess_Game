package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.Pawn;
import Pieces.MoveBehavior.Movement.Move;

public class BlackPawnMoveValidator implements MoveValidatorType {
    public static BlackPawnMoveValidator blackPawnMoveValidator = null;
    private BlackPawnMoveValidator(){}
    public static BlackPawnMoveValidator getInstance(){
        if(blackPawnMoveValidator == null)
            blackPawnMoveValidator = new BlackPawnMoveValidator();
        return  blackPawnMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Pawn currentPawn = (Pawn) move.getPieceToBeMoved();
        if ( move.isDestOneMoveAwayOnBot() &&
                !move.isThereAnEnemyOnDestination()) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }else if(move.isDestTwoMovesAwayOnBot() && !currentPawn.isMoved() &&
                !move.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(true);
            currentPawn.setMovedToTrue();
            return true;
        } else if ( (move.isDestOneMoveAwayOnBotRight() || move.isDestOneMoveAwayOnBotLeft())
                && move.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }
        return false;
    }
}
