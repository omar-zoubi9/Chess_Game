package GameManaging;

import GameManaging.Factories.PiecesFactory;
import InputManaging.InputManager;
import InputManaging.SquareID;

public class GameInitializer {
    PiecesFactory piecesFactory = new PiecesFactory();

    private void initializeBlackPawnsForNewGame(Square[][] board){
        int row = 6;
        for(int col=0 ; col <= 7 ; col++){
            board[row][col].setChessPiece(piecesFactory.makeBlackPawn());
        }
    }
    private void initializeWhitePawnsForNewGame(Square[][] board){
        int row = 1;
        for(int col=0 ; col <= 7 ; col++){
            board[row][col].setChessPiece(piecesFactory.makeWhitePawn());
        }
    }
    private void initializePawnsForNewGame(Square[][] board){
        initializeWhitePawnsForNewGame(board);
        initializeBlackPawnsForNewGame(board);
    }
    private void initializePiecesForNewGame(Square[][] board){
        initializePawnsForNewGame(board);
        SquareID kingSquareID = new SquareID();
        board[0][0].setChessPiece(piecesFactory.makeWhiteRook());
        board[0][7].setChessPiece(piecesFactory.makeWhiteRook());
        board[0][1].setChessPiece(piecesFactory.makeWhiteKnight());
        board[0][6].setChessPiece(piecesFactory.makeWhiteKnight());
        board[0][2].setChessPiece(piecesFactory.makeWhiteBishop());
        board[0][5].setChessPiece(piecesFactory.makeWhiteBishop());
        board[0][3].setChessPiece(piecesFactory.makeWhiteQueen());
        kingSquareID.setYCoordinate(0);
        kingSquareID.setXCoordinate(4);
        board[0][4].setChessPiece(piecesFactory.makeWhiteKing(kingSquareID));

        board[7][0].setChessPiece(piecesFactory.makeBlackRook());
        board[7][7].setChessPiece(piecesFactory.makeBlackRook());
        board[7][1].setChessPiece(piecesFactory.makeBlackKnight());
        board[7][6].setChessPiece(piecesFactory.makeBlackKnight());
        board[7][2].setChessPiece(piecesFactory.makeBlackBishop());
        board[7][5].setChessPiece(piecesFactory.makeBlackBishop());
        board[7][3].setChessPiece(piecesFactory.makeBlackQueen());
        kingSquareID.setYCoordinate(7);
        kingSquareID.setXCoordinate(4);
        board[7][4].setChessPiece(piecesFactory.makeBlackKing(kingSquareID));
    }
    public void initializeNewGame(Square[][] board, Player player1, Player player2){
        InputManager inputManager = new InputManager();
        player1.setName(inputManager.takeName(player1.getId()));
        player2.setName(inputManager.takeName(player2.getId()));

        initializePiecesForNewGame(board);
    }

}
