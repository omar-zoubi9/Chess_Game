package GameManaging.SpecialMoveBehavior;

import Pieces.MoveBehavior.Movement.Move;
import GameManaging.GameManager;
import GameManaging.ValidationChain.ValidationChain;
import InputManaging.SquareID;
import Pieces.ChessPiece;
import Pieces.King;
import Pieces.Rook;

public class CastlingValidator implements ValidationChain {
    public static CastlingValidator castlingValidator = null;
    private CastlingValidator(){}
    public static CastlingValidator getInstance(){
        if(castlingValidator == null)
            castlingValidator = new CastlingValidator();
        return castlingValidator;
    }
    private ValidationChain nextInChain;

    @Override
    public boolean isValid(Move move){
        GameManager gameManager = GameManager.getInstance();

        if(gameManager.squareHasAPiece(move.getDestSquare()) && nextInChain == null)
            return false;
        if(gameManager.squareHasAPiece(move.getDestSquare()))
            return nextInChain.isValid(move);
        ChessPiece pieceToCheck = move.getPieceToBeMoved();
        SquareID rookSrcSquare = new SquareID();
        SquareID rookDestSquare = new SquareID();

        if(gameManager.isPlayer1Turn() && pieceToCheck instanceof King && !((King) pieceToCheck).isMoved()){
            if(move.isDestTwoMovesAwayOnRight() && move.isDestinationFree() && gameManager.getBoard()[0][7].getPiece() instanceof Rook
                    && !((Rook) gameManager.getBoard()[0][7].getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(0);
                rookSrcSquare.setXCoordinate(7);
                rookDestSquare.setYCoordinate(0);
                rookDestSquare.setXCoordinate(5);
                gameManager.movePiece(rookSrcSquare , rookDestSquare);
                return true;
            }else if(move.isDestTwoMovesAwayOnLeft() && gameManager.getBoard()[0][3].getPiece() == null && gameManager.getBoard()[0][0].getPiece() instanceof Rook
                    && !((Rook) gameManager.getBoard()[0][0].getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(0);
                rookSrcSquare.setXCoordinate(0);
                rookDestSquare.setYCoordinate(0);
                rookDestSquare.setXCoordinate(3);

                gameManager.movePiece(rookSrcSquare , rookDestSquare);
                return true;
            }
        }else if(gameManager.isPlayer2Turn() && pieceToCheck instanceof King && !((King) pieceToCheck).isMoved()){
            if(move.isDestTwoMovesAwayOnRight() && gameManager.getBoard()[7][5].getPiece() == null && gameManager.getBoard()[7][7].getPiece() instanceof Rook
                    && !((Rook) gameManager.getBoard()[7][7].getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(7);
                rookSrcSquare.setXCoordinate(7);
                rookDestSquare.setYCoordinate(7);
                rookDestSquare.setXCoordinate(5);

                gameManager.movePiece(rookSrcSquare , rookDestSquare);
                return true;
            }else if(move.isDestTwoMovesAwayOnLeft() && gameManager.getBoard()[7][3].getPiece() == null && gameManager.getBoard()[7][0].getPiece() instanceof Rook
                    && !((Rook) gameManager.getBoard()[0][0].getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(7);
                rookSrcSquare.setXCoordinate(0);
                rookDestSquare.setYCoordinate(7);
                rookDestSquare.setXCoordinate(3);

                gameManager.movePiece(rookSrcSquare , rookDestSquare);
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
