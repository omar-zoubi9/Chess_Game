package Pieces.MoveBehavior.MoveValidationTypes;

import Pieces.MoveBehavior.Movement.Move;

public interface MoveValidatorType {
    boolean isValid(Move move);
}

