package GameManaging.SpecialMoveBehavior;

import Pieces.MoveBehavior.Movement.Move;
import GameManaging.GameManager;
import GameManaging.ValidationChain.ValidationChain;
import InputManaging.SquareID;
import Pieces.ChessPiece;
import Pieces.Pawn;

public class EnPassantValidator implements ValidationChain {
    public static EnPassantValidator enPassantValidator = null;
    private ValidationChain nextInChain;
    private EnPassantValidator(){}
    public static EnPassantValidator getInstance(){
        if(enPassantValidator == null)
            enPassantValidator = new EnPassantValidator();
        return enPassantValidator;
    }

    private boolean isEnemyPawnEligibleForEnPassant(Pawn pawnToCheck){
        return pawnToCheck.isLastMoveADoubleMove();
    }
    @Override
    public boolean isValid(Move move){
        ChessPiece pieceToCheck = move.getPieceToBeMoved();
        SquareID enemySquare = new SquareID();
        GameManager gameManager = GameManager.getInstance();
        if(gameManager.isPlayer1Turn() && pieceToCheck instanceof Pawn){
            if(move.isDestOneMoveAwayOnTopRight() && move.isThereAnEnemyPawnOneMoveAwayOnRight()){
                enemySquare.setYCoordinate(move.getSrcSquare().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquare().getXCoordinate()+1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) move.getPieceToBeMoved()))
                    gameManager.removePiece(enemySquare);
                return true;
            }else if(move.isDestOneMoveAwayOnTopLeft() && move.isThereAnEnemyPawnOneMoveAwayOnLeft()){
                enemySquare.setYCoordinate(move.getSrcSquare().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquare().getXCoordinate()-1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) move.getPieceToBeMoved()))
                    gameManager.removePiece(enemySquare);
                return true;
            }
        }else if(gameManager.isPlayer2Turn() && pieceToCheck instanceof Pawn){
            if(move.isDestOneMoveAwayOnBotRight() && move.isThereAnEnemyPawnOneMoveAwayOnRight()){
                enemySquare.setYCoordinate(move.getSrcSquare().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquare().getXCoordinate()+1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) move.getPieceToBeMoved()))
                    gameManager.removePiece(enemySquare);
                return true;
            }else if(move.isDestOneMoveAwayOnBotLeft() && move.isThereAnEnemyPawnOneMoveAwayOnLeft()){
                enemySquare.setYCoordinate(move.getSrcSquare().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquare().getXCoordinate()-1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) move.getPieceToBeMoved()))
                    gameManager.removePiece(enemySquare);
                return true;
            }
        }
        if(nextInChain != null){
            return nextInChain.isValid(move);
        }
        return false;
    }

    @Override
    public void setNextInChain(ValidationChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
