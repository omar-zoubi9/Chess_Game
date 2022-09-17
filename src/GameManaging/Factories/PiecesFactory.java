package GameManaging.Factories;

import Pieces.*;
import Pieces.MoveBehavior.MoveValidationTypes.*;
import InputManaging.SquareID;

public class PiecesFactory implements Factory {
    public ChessPiece makeChessPiece(String pieceType , int playerID){
        if(playerID != 1 && playerID != 2)
            throw new RuntimeException("wrong player ID valid IDs are 1 for white and 2 for black");

        ChessPiece chessPiece = null;
        pieceType = pieceType.toLowerCase();
        switch(pieceType) {
            case "bishop":
                if(playerID == 1){
                    chessPiece = this.makeWhiteBishop();
                }
                else if(playerID == 2)
                    chessPiece = this.makeBlackBishop();
                break;
            case "knight":
                if(playerID == 1){
                    chessPiece = this.makeWhiteKnight();
                }else if(playerID == 2)
                    chessPiece = this.makeBlackKnight();
                break;
            case "pawn":
                if(playerID == 1){
                    chessPiece = this.makeWhitePawn();
                }else if(playerID == 2)
                    chessPiece = this.makeBlackPawn();
                break;
            case "queen":
                if(playerID == 1){
                    chessPiece = this.makeWhiteQueen();
                }else if(playerID == 2)
                    chessPiece = this.makeBlackQueen();
                break;
            case "rook":
                if(playerID == 1){
                    chessPiece = this.makeWhiteRook();
                }else if(playerID == 2)
                    chessPiece = this.makeBlackRook();
                break;
            default:
                throw new RuntimeException("there is no valid piece named " + pieceType + '\n');
        }
        return chessPiece;
    }

    @Override
    public Bishop makeWhiteBishop() {
        return new Bishop(1 , BishopMoveValidator.getInstance());
    }

    @Override
    public Bishop makeBlackBishop() {
        return new Bishop(2 , BishopMoveValidator.getInstance());
    }

    @Override
    public King makeWhiteKing(SquareID kingSquareID) {
        return new King(1 , KingMoveValidator.getInstance() , kingSquareID);
    }

    @Override
    public King makeBlackKing(SquareID kingSquareID) {
        return new King(2 , KingMoveValidator.getInstance() , kingSquareID);
    }

    @Override
    public Knight makeWhiteKnight() {
        return new Knight(1 , KnightMoveValidator.getInstance());
    }

    @Override
    public Knight makeBlackKnight() {
        return new Knight(2 , KnightMoveValidator.getInstance());
    }

    @Override
    public Pawn makeWhitePawn() {
        return new Pawn(1 , WhitePawnMoveValidator.getInstance());
    }

    @Override
    public Pawn makeBlackPawn() {
        return new Pawn(2 , BlackPawnMoveValidator.getInstance());
    }

    @Override
    public Queen makeWhiteQueen() {
        return new Queen(1 , QueenMoveValidator.getInstance());
    }

    @Override
    public Queen makeBlackQueen() {
        return new Queen(2 , QueenMoveValidator.getInstance());
    }

    public Rook makeWhiteRook(){
        return new Rook(1 , RookMoveValidator.getInstance());
    }
    public Rook makeBlackRook(){
        return new Rook(2 , RookMoveValidator.getInstance());
    }
}
