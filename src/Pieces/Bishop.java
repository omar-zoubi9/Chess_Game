package Pieces;

import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;

public class Bishop extends ChessPiece{
    public Bishop(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public String getPieceName() {
        return "Bishop";
    }
}
