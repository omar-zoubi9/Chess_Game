package GameManaging.ValidationChain;

import Pieces.MoveBehavior.Movement.Move;

public interface ValidationChain {
    boolean isValid(Move move);
    void setNextInChain(ValidationChain nextInChain);
}
