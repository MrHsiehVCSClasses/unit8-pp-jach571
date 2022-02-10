package u8pp;


public class Main 
{
    public static void main( String[] args ) {
        String[][]  boardD1WinSmall = {
            {"X", "O", "O"},
            {" ", "X", "O"},
            {"X", "O", "X"},
        };
        TicTacToeBoard smallD1Win = new TicTacToeBoard(boardD1WinSmall);
        System.out.println(smallD1Win.hasDiagonalWin());
    }
}