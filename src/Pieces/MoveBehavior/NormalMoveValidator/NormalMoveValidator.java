package Pieces.MoveBehavior.NormalMoveValidator;

import Pieces.MoveBehavior.Movement.Move;
import GameManaging.ValidationChain.ValidationChain;
import Pieces.ChessPiece;

public class NormalMoveValidator implements ValidationChain {
    public static NormalMoveValidator normalMoveValidator;
    private ValidationChain nextInChain;
    private NormalMoveValidator(){}
    public static NormalMoveValidator getInstance(){
        if(normalMoveValidator == null)
            normalMoveValidator = new NormalMoveValidator();
        return normalMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        ChessPiece currentPiece = move.getPieceToBeMoved();
        if(currentPiece.moveValidatorType.isValid(move)){
            return true;
        }else if(nextInChain != null){
            return nextInChain.isValid(move);
        }
        System.out.println("invalid move ");
        return false;
    }

    @Override
    public void setNextInChain(ValidationChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
