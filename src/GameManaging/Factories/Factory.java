package GameManaging.Factories;

import Pieces.*;
import InputManaging.SquareID;

public interface Factory {
    Bishop makeWhiteBishop();
    Bishop makeBlackBishop();
    King makeWhiteKing(SquareID kingSquareID);
    King makeBlackKing(SquareID kingSquareID);
    Knight makeWhiteKnight();
    Knight makeBlackKnight();
    Pawn makeWhitePawn();
    Pawn makeBlackPawn();
    Queen makeWhiteQueen();
    Queen makeBlackQueen();
    Rook makeWhiteRook();
    Rook makeBlackRook();
}
