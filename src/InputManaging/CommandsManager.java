package InputManaging;

import Pieces.MoveBehavior.Movement.Move;
import GameManaging.GameManager;
import GameManaging.SpecialMoveBehavior.CastlingValidator;
import GameManaging.SpecialMoveBehavior.EnPassantValidator;
import GameManaging.SpecialMoveBehavior.PawnPromotionValidator;
import Pieces.ChessPiece;
import Pieces.MoveBehavior.NormalMoveValidator.NormalMoveValidator;

public class CommandsManager {
    Move move = Move.getInstance();
    private final Helper helper = new Helper();
    private CastlingValidator chain1 = CastlingValidator.getInstance();
    private EnPassantValidator chain2 = EnPassantValidator.getInstance();
    private PawnPromotionValidator chain3 = PawnPromotionValidator.getInstance();
    private NormalMoveValidator chain4 = NormalMoveValidator.getInstance();
    private boolean lastCommandExecutedIsMove;
    public CommandsManager(){
        chain1.setNextInChain(chain2);
        chain2.setNextInChain(chain3);
        chain3.setNextInChain(chain4);
    }
    private void initializeMoveObject(String[] moveCommand){
        move.setBoardToGameManagerBoard();
        SquareID squareID = new SquareID();
        squareID.setYCoordinate(moveCommand[1].charAt(0) - '1');
        squareID.setXCoordinate(moveCommand[1].charAt(1) - 'A');
        move.setSrcSquare(squareID);
        squareID.setYCoordinate(moveCommand[2].charAt(0) - '1');
        squareID.setXCoordinate(moveCommand[2].charAt(1) - 'A');
        move.setDestSquare(squareID);
    }
    public void doCommand(String[] command, int turnForPlayerWithID){
        GameManager gameManager = GameManager.getInstance();
        lastCommandExecutedIsMove = false;

        if(command.length == 0)
            throw new RuntimeException("command is missing");

        if(command[0].equalsIgnoreCase("help")){
            helper.printHelp();
        }else if(command[0].equalsIgnoreCase("move")){
            if (command.length != 3){
                helper.printHelpForWrongNonValidCommand();
                return;
            }
            initializeMoveObject(command);
            ChessPiece pieceToBeMoved = move.getPieceToBeMoved();
            if(pieceToBeMoved == null){
                System.out.println("source square doesn't hava a piece");
            }else if(pieceToBeMoved.getPlayerID() != turnForPlayerWithID){
                System.out.println("you can't move an enemy piece");
            }else if(chain1.isValid(move)){
                gameManager.movePiece(move.getSrcSquare() , move.getDestSquare());
                lastCommandExecutedIsMove = true;
            }
        }else{
            helper.printHelpForWrongNonValidCommand();
        }
    }
    public boolean isMoveTheLastExecutedCommand(){
        return lastCommandExecutedIsMove;
    }
}
