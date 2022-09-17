package GameManaging;

import Pieces.ChessPiece;
import Pieces.King;
import InputManaging.CommandChecker;
import InputManaging.CommandsManager;
import InputManaging.InputManager;
import InputManaging.SquareID;

import java.io.IOException;

public class GameManager {
    private final InputManager inputManager = new InputManager();
    private final CommandsManager commandsManager = new CommandsManager();
    private final CommandChecker commandChecker = new CommandChecker();
    private final GameInitializer gameInitializer = new GameInitializer();
    boolean gameIsGoing = false;
    private static Player player1;
    private static Player player2;
    int turnForPlayerWithID = 1;
    private static Square[][] board;
    public static GameManager gameManager = null;
    private GameManager(){}
    public static GameManager getInstance(){
        if(gameManager == null){
            gameManager = new GameManager();
            player1 = new Player(1);
            player2 = new Player(2);
            board = new Square[8][8];
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 8 ; j++) {
                    board[i][j] = new Square();
                }
            }
        }
        return gameManager;
    }
    public boolean squareHasAPiece(SquareID squareToCheck){
        return board[squareToCheck.getYCoordinate()][squareToCheck.getXCoordinate()].getPiece() != null;
    }
    public void removePiece(SquareID fromSquare){
        if(board[fromSquare.getYCoordinate()][fromSquare.getXCoordinate()].getPiece() instanceof King)
            gameIsGoing = false;
        board[fromSquare.getYCoordinate()][fromSquare.getXCoordinate()].removePiece();
    }
    public void copyPiece(SquareID fromSquare , SquareID toSquare){
        board[toSquare.getYCoordinate()][toSquare.getXCoordinate()].setChessPiece(board[fromSquare.getYCoordinate()][fromSquare.getXCoordinate()].getPiece());
    }
    public void movePiece(SquareID fromSquare , SquareID toSquare){
        copyPiece(fromSquare , toSquare);
        removePiece(fromSquare);
    }
    private void changeTurn(int turnForPlayerWithID){
        if(turnForPlayerWithID == 1){
            this.turnForPlayerWithID=2;
        }else{
            this.turnForPlayerWithID=1;
        }
    }
    public boolean isPlayer1Turn(){
        return turnForPlayerWithID == 1;
    }
    public boolean isPlayer2Turn(){
        return turnForPlayerWithID == 2;
    }
    private void printBoardState() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if(!board[i][j].hasAPiece()){
                    System.out.print("empty ");
                    continue;
                }
                System.out.print(board[i][j].getPiece().getPlayerID() + board[i][j].getPiece().getPieceName() + " ");
            }
            System.out.println();
        }
    }
    public void startGame() throws IOException {
        gameInitializer.initializeNewGame(board , player1 , player2);

        gameIsGoing = true;
        while(gameIsGoing){
            printBoardState();
            String turnForPlayerWithName;
            if(turnForPlayerWithID == 1){
                turnForPlayerWithName = player1.getName();
            }else{
                turnForPlayerWithName = player2.getName();
            }
            String[] inputCommand = inputManager.takeInputCommand(turnForPlayerWithName);
            if(inputCommand.length == 1 && inputCommand[0].equalsIgnoreCase("exit")){
                System.out.println("Thanks for playing bye!");
                return;
            }
            boolean commandHasAValidFormat = commandChecker.checkCommand(inputCommand);
            if(!commandHasAValidFormat){
                continue;
            }else{
                commandsManager.doCommand(inputCommand , turnForPlayerWithID);
            }

            if(commandsManager.isMoveTheLastExecutedCommand())
                changeTurn(turnForPlayerWithID);
        }
    }
    public Square[][] getBoard() {
        return board;
    }

    public void setChessPiece(ChessPiece chessPiece, SquareID srcSquare) {
        board[srcSquare.getYCoordinate()][srcSquare.getXCoordinate()].setChessPiece(chessPiece);
    }
}
