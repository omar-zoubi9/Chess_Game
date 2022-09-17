package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.MoveBehavior.Movement.Move;

public class KnightMoveValidator implements MoveValidatorType {
    private final int[] dx = {1 , 2, -1, -2, -2, -1,  2,  1};
    private final int[] dy = {2 , 1,  2,  1, -1, -2, -1, -2};
    public static KnightMoveValidator knightMoveValidator = null;
    private KnightMoveValidator(){}
    public static KnightMoveValidator getInstance(){
        if(knightMoveValidator == null)
            knightMoveValidator = new KnightMoveValidator();
        return knightMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        for(int i=0 ; i < 8 ; i++)
            if(move.getSrcSquare().getYCoordinate()+dy[i] == move.getDestSquare().getYCoordinate() &&
                    move.getSrcSquare().getXCoordinate()+dx[i] == move.getDestSquare().getXCoordinate())
                return true;
        return false;
    }
}
