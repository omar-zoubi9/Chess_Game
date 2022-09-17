package GameManaging.SpecialMoveBehavior;

import Pieces.MoveBehavior.Movement.Move;
import GameManaging.Factories.PiecesFactory;
import GameManaging.GameManager;
import GameManaging.ValidationChain.ValidationChain;
import InputManaging.Helper;
import Pieces.ChessPiece;
import Pieces.MoveBehavior.MoveValidationTypes.BlackPawnMoveValidator;
import Pieces.MoveBehavior.MoveValidationTypes.WhitePawnMoveValidator;

import java.util.Scanner;

public class PawnPromotionValidator implements ValidationChain{
    Helper helper = new Helper();
    public static PawnPromotionValidator pawnPromotionValidator = null;
    private PawnPromotionValidator(){}
    public static PawnPromotionValidator getInstance(){
        if(pawnPromotionValidator == null)
            pawnPromotionValidator = new PawnPromotionValidator();
        return pawnPromotionValidator;
    }
    private ValidationChain nextInChain;
    public void promotePawn(Move move){
        GameManager gameManager = GameManager.getInstance();
        PiecesFactory piecesFactory = new PiecesFactory();
        ChessPiece chessPiece;
        helper.printPawnPromotionString();
        Scanner scanner = new Scanner(System.in);
        String pieceName = scanner.nextLine();
        if(!helper.isValidPromotionPiece(pieceName)){
            System.out.println("please enter a valid piece here are the valid pieces");
            helper.printPromotionPieces();
            promotePawn(move);
        }else{
            chessPiece = piecesFactory.makeChessPiece( pieceName , move.getPieceToBeMoved().getPlayerID());
            gameManager.setChessPiece(chessPiece , move.getSrcSquare());
        }
    }
    @Override
    public boolean isValid(Move move){
        if(move.getPieceToBeMoved().getPlayerID() == 1 && move.isDestOnTopEnd()){
            WhitePawnMoveValidator whitePawnMoveValidator = WhitePawnMoveValidator.getInstance();
            if(whitePawnMoveValidator.isValid(move)){
                helper.printPawnPromotionString();
                promotePawn(move);
                return true;
            }
        }else if(move.getPieceToBeMoved().getPlayerID() == 2 && move.isDestOnBotEnd()){
            BlackPawnMoveValidator blackPawnMoveChecker = BlackPawnMoveValidator.getInstance();
            if(blackPawnMoveChecker.isValid(move)){
                helper.printPawnPromotionString();
                promotePawn(move);
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
