package InputManaging;

import java.util.Scanner;
public class Helper {
    private final String gameManual = "game manual";
    private final String moveCommandManual = "move command manual";
    private final String commandManual = "command manual";
    private final String[] promotionPieces = {"Queen" , "Rook" , "Bishop" , "Knight"};
    private final String pawnPromotionString = "please enter a piece of your preference to promote pawn to: Queen , Rook , Bishop , Knight";
    private final String nonValidCommandHelp = "non-valid command here is\n" + commandManual;
    public void printHelp(){
        System.out.println("for the game manual enter 1 for the command manual enter 2 to continue the game enter 0");
        Scanner scanner = new Scanner(System.in);
        int input;
        input = scanner.nextInt();
        if(input == 0){
            return;
        }

        if(input == 1){
            System.out.println(gameManual);
        }else if(input == 2){
            System.out.println(commandManual);
        }else {
            System.out.println("Wrong value please try again");
            printHelp();
        }
    }
    public void printHelpForWrongMoveFormat(){
        System.out.println("Wrong move format");
        System.out.println(moveCommandManual);
    }
    public boolean isValidPromotionPiece(String pieceName){
        for (String promotionPiece : promotionPieces) {
            if (pieceName.equals(promotionPiece))
                return true;
        }
        return false;
    }
    public void printCommandManual() {
        System.out.println(gameManual);
    }
    public void printHelpForWrongNonValidCommand() {
        System.out.println(nonValidCommandHelp);
    }
    public void printPawnPromotionString() {
        System.out.println(pawnPromotionString);
    }
    public void printPromotionPieces() {
        for(int i=0 ; i < promotionPieces.length ; i++){
            System.out.print(promotionPieces[i]);
            if(i != promotionPieces.length-1)
                System.out.print(" , ");
            System.out.println();
        }
    }
}
