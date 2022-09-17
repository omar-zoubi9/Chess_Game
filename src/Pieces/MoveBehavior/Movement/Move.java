package Pieces.MoveBehavior.Movement;

import Pieces.ChessPiece;
import Pieces.Pawn;
import GameManaging.GameManager;
import GameManaging.Square;
import InputManaging.SquareID;

public class Move {
    public static Move move = null;
    private Move(){}
    public static  Move getInstance(){
        if(move == null)
            move = new Move();
        return move;
    }
    private Square[][] board;
    private SquareID srcSquare = new SquareID();
    private SquareID destSquare = new SquareID();
    public void setSrcSquare(SquareID srcSquare) {
        this.srcSquare.setYCoordinate(srcSquare.getYCoordinate());
        this.srcSquare.setXCoordinate(srcSquare.getXCoordinate());
    }
    public void setDestSquare(SquareID destSquare) {
        this.destSquare.setYCoordinate(destSquare.getYCoordinate());
        this.destSquare.setXCoordinate(destSquare.getXCoordinate());
    }
    XYMovement xyMovement = new XYMovement();
    public void setBoardToGameManagerBoard(){
        board = GameManager.getInstance().getBoard();
    }
    private int getSquaresToCheckCount(){
        if(isDestinationOnTop()){
            return destSquare.getYCoordinate() - srcSquare.getYCoordinate();
        }else if(isDestinationOnBot()){
            return srcSquare.getYCoordinate() - destSquare.getYCoordinate();
        }else if(isDestinationOnRight()){
            return destSquare.getXCoordinate() - srcSquare.getXCoordinate();
        }else if(isDestinationOnLeft()){
            return srcSquare.getXCoordinate() - destSquare.getXCoordinate();
        }
        return 0;
    }
    public SquareID getSrcSquare() {
        return srcSquare;
    }

    public SquareID getDestSquare() {
        return destSquare;
    }

    public boolean isWayFreeTillDestination() {
        int squaresToCheckCount = getSquaresToCheckCount();
        for(int i=0; i < squaresToCheckCount ; i++){
            srcSquare.setYCoordinate(srcSquare.getXCoordinate() + xyMovement.getDx());
            srcSquare.setYCoordinate(srcSquare.getYCoordinate() + xyMovement.getDy());

            if(board[srcSquare.getXCoordinate()][srcSquare.getYCoordinate()].hasAPiece()){
                //todo
                //print the piece in the way
                return false;
            }
        }
        return true;
    }
    public boolean isDestOneSquareAway() {
        if(srcSquare.getXCoordinate()+1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate() == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate() == destSquare.getXCoordinate() && srcSquare.getYCoordinate()+1 == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate()-1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate() == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate() == destSquare.getXCoordinate() && srcSquare.getYCoordinate()-1 == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate()+1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate()+1 == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate()+1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate()-1 == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate()-1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate()+1 == destSquare.getYCoordinate()){
            return true;
        }else if(srcSquare.getXCoordinate()-1 == destSquare.getXCoordinate() && srcSquare.getYCoordinate()-1 == destSquare.getYCoordinate()){
            return true;
        }
        return false;
    }
    public boolean isDestinationOnTop(){
        return srcSquare.getYCoordinate() < destSquare.getYCoordinate();
    }
    public boolean isDestinationOnBot(){
        return srcSquare.getYCoordinate() > destSquare.getYCoordinate();
    }
    public boolean isDestinationOnRight(){
        return srcSquare.getXCoordinate() < destSquare.getXCoordinate();
    }
    public boolean isDestinationOnLeft(){
        return srcSquare.getXCoordinate() > destSquare.getXCoordinate();
    }
    private boolean isOnFrontSlashDiagonal(){
        return srcSquare.getXCoordinate() - srcSquare.getYCoordinate() == destSquare.getXCoordinate() - destSquare.getYCoordinate();
    }
    public boolean isDestinationOnDiagonalTopRight(){
        return srcSquare.getYCoordinate() < destSquare.getYCoordinate() && srcSquare.getXCoordinate() < destSquare.getXCoordinate()
                && isOnFrontSlashDiagonal();
    }
    public boolean isDestinationOnDiagonalBotLeft(){
        return srcSquare.getYCoordinate() > destSquare.getYCoordinate() && srcSquare.getXCoordinate() > destSquare.getXCoordinate()
                && isOnFrontSlashDiagonal();
    }
    private boolean isOnBackSlashDiagonal(){
        return srcSquare.getXCoordinate() + srcSquare.getYCoordinate() == destSquare.getXCoordinate() + destSquare.getYCoordinate();
    }
    public boolean isDestinationOnDiagonalTopLeft(){
        return srcSquare.getYCoordinate() < destSquare.getYCoordinate() && srcSquare.getXCoordinate() > destSquare.getXCoordinate()
                && isOnBackSlashDiagonal();
    }
    public boolean isDestinationOnDiagonalBotRight(){
        return srcSquare.getYCoordinate() > destSquare.getYCoordinate() && srcSquare.getXCoordinate() < destSquare.getXCoordinate()
                && isOnBackSlashDiagonal();
    }
    public void setXYDirectionToTop() {
        xyMovement.setDx(0);
        xyMovement.setDy(1);
    }
    public void setXYDirectionToBot() {
        xyMovement.setDx(0);
        xyMovement.setDy(-1);
    }
    public void setXYDirectionToRight() {
        xyMovement.setDx(1);
        xyMovement.setDy(0);
    }
    public void setXYDirectionToLeft() {
        xyMovement.setDx(-1);
        xyMovement.setDy(0);
    }

    public void setXYDirectionToDiagonalTopRight() {
        xyMovement.setDx(1);
        xyMovement.setDy(1);
    }
    public void setXYDirectionToDiagonalTopLeft() {
        xyMovement.setDx(-1);
        xyMovement.setDy(1);
    }
    public void setXYDirectionToDiagonalBotRight() {
        xyMovement.setDx(1);
        xyMovement.setDy(-1);
    }
    public void setXYDirectionToDiagonalBotLeft() {
        xyMovement.setDx(-1);
        xyMovement.setDy(-1);
    }
    public boolean isThereAnEnemyOnDestination() {
        if(board[destSquare.getYCoordinate()][destSquare.getXCoordinate()].getPiece() != null){
            return board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()].getPiece().getPlayerID() !=
                    board[destSquare.getYCoordinate()][destSquare.getXCoordinate()].getPiece().getPlayerID();
        }
        return false;
    }

    public boolean isDestOneMoveAwayOnTopRight(){
        return srcSquare.getYCoordinate() + 1 == destSquare.getYCoordinate() &&
                srcSquare.getXCoordinate() + 1 == destSquare.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnTopLeft(){
        return srcSquare.getYCoordinate() + 1 == destSquare.getYCoordinate() &&
                srcSquare.getXCoordinate() - 1 == destSquare.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBotRight(){
        return srcSquare.getYCoordinate() - 1 == destSquare.getYCoordinate() &&
                srcSquare.getXCoordinate() + 1 == destSquare.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBotLeft(){
        return srcSquare.getYCoordinate() - 1 == destSquare.getYCoordinate() &&
                srcSquare.getXCoordinate() - 1 == destSquare.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnTop(){
        return srcSquare.getYCoordinate() + 1 == destSquare.getYCoordinate()
                && srcSquare.getXCoordinate() == destSquare.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnTop(){
        return srcSquare.getYCoordinate() + 2 == destSquare.getYCoordinate()
                && srcSquare.getXCoordinate() == destSquare.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBot(){
        return srcSquare.getYCoordinate() - 1 == destSquare.getYCoordinate()
                && srcSquare.getXCoordinate() == destSquare.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnBot(){
        return srcSquare.getYCoordinate() - 2 == destSquare.getYCoordinate()
                && srcSquare.getXCoordinate() == destSquare.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnRight(){
        return srcSquare.getYCoordinate() == destSquare.getYCoordinate() && destSquare.getXCoordinate()-srcSquare.getXCoordinate() == 2;
    }
    public boolean isDestTwoMovesAwayOnLeft(){
        return srcSquare.getYCoordinate() == destSquare.getYCoordinate() && destSquare.getXCoordinate()-srcSquare.getXCoordinate() == -2;
    }
    public ChessPiece getPieceToBeMoved(){
        return board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()].getPiece();
    }
    public boolean isDestinationFree(){
       return board[destSquare.getYCoordinate()][destSquare.getXCoordinate()] == null;
    }
    public boolean isThereAnEnemyPawnOneMoveAwayOnRight(){
        return board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()+1].getPiece() instanceof Pawn &&
                board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()].getPiece().getPlayerID() !=
                        board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()+1].getPiece().getPlayerID();
    }
    public boolean isThereAnEnemyPawnOneMoveAwayOnLeft(){
        return board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()-1].getPiece() instanceof Pawn &&
                board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()].getPiece().getPlayerID() !=
                        board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()-1].getPiece().getPlayerID();
    }
    public boolean isDestOnTopEnd(){
        return destSquare.getYCoordinate() == 7;
    }
    public boolean isDestOnBotEnd(){
        return destSquare.getYCoordinate() == 0;
    }
}
