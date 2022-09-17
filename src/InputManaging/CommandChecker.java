package InputManaging;

public class CommandChecker {
    private final Helper helper = new Helper();
    private boolean isMoveCommandValid(String[] moveCommand){
        if(moveCommand[1].length() != 2 || moveCommand[2].length() != 2){
            helper.printHelpForWrongMoveFormat();
            return false;
        }

        if(moveCommand[1].charAt(0)-'1' < 0 || moveCommand[1].charAt(0)-'1' > 7 ||
                moveCommand[2].charAt(0)-'1' < 0 || moveCommand[2].charAt(0)-'1' > 7 ||
                moveCommand[1].charAt(1)-'A' < 0 || moveCommand[1].charAt(1)-'F' > 7 ||
                moveCommand[2].charAt(1)-'A' < 0 || moveCommand[2].charAt(1)-'F' > 7){
            helper.printHelpForWrongMoveFormat();
            return false;
        }
        return true;
    }
    public boolean checkCommand(String[] command){
        if(command.length == 3 && command[0].equalsIgnoreCase("move")){
            return isMoveCommandValid(command);
        }else if(command.length == 1 && command[0].equalsIgnoreCase("help")){
            return true;
        }
        System.out.println("invalid command");
        return false;
    }
}