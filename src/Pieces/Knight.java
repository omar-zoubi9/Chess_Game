package Pieces;

import Pieces.MoveBehavior.MoveValidationTypes.MoveValidatorType;

public class Knight extends ChessPiece{
    public Knight(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public String getPieceName() {
        return "Knight";
    }
}
