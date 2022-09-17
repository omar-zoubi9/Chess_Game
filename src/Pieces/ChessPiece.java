package Pieces;


import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;

public abstract class ChessPiece {
    private final int playerID;
    public MoveValidatorType moveValidatorType;
    public ChessPiece(int playerID , MoveValidatorType moveValidatorType) {
        this.moveValidatorType = moveValidatorType;
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }
    public abstract String getPieceName();
}
