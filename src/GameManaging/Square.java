package GameManaging;

import Pieces.ChessPiece;

public class Square {
    private ChessPiece piece;
    private boolean hasAPiece;
    public void setChessPiece(ChessPiece newPiece){
        piece = newPiece;
        hasAPiece = true;
    }
    public void removePiece(){
        piece = null;
        hasAPiece = false;
    }
    public boolean hasAPiece(){
        return hasAPiece;
    }
    public ChessPiece getPiece(){
        return piece;
    }
}
