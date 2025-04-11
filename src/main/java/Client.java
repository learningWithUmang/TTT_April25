import controllers.GameController;
import enums.BOTDifficultyLevel;
import enums.GameState;
import enums.PlayerType;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Bot;
import models.Game;
import models.Player;
import models.Symbol;
import strategy.WinningStrategies.ColumnWinningStrategy;
import strategy.WinningStrategies.DiagonalWinningStrategy;
import strategy.WinningStrategies.RowWinningStrategy;
import strategy.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws BOTCountInvalidException, PlayerCountNotValidException {


        Player p1 = new Player("Umang", new Symbol('U',""), PlayerType.HUMAN);
        Player p2 = new Player("Nilesh", new Symbol('B',""), PlayerType.HUMAN);

        List<Player> players = List.of(p1,p2);

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        /*
        UI
        Row Col Diag
        Row, Col, Diag
         */
        GameController gameController = new GameController();

        Game game = gameController.startGame(3, players, winningStrategies);

        Scanner scanner = new Scanner(System.in);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){


            //show the board, print the current board
            //tell whose player's turn it is and ask him to choose his row,col where he/she wants to play
            //execute the move on the board if it's a valid move
            gameController.printBoard(game);
            gameController.makeMove(game);

            //start timer for 5 seconds
            //How - do this only for humans
            System.out.println("Do you want to undo y/n");
            String undoAnswer = scanner.next();

            //ask this unDo option only for humans
            //
            if(undoAnswer.equalsIgnoreCase("y")){ //Y
                gameController.unDo(game);
            }
            //ask if anybody wants to unDo the last Move
        }

        if(game.getGameState().equals(GameState.ENDED)){
            gameController.printBoard(game);
            System.out.println("Game has a winner 0and winner is " + game.getWinner().getName());
        }else{
            System.out.println("Game is a draw");
        }
    }
    /*
    Let;s end it here for today.
    HW - Go through whatever we've written so far nicely (Please)
    HW - Please try to continue the impl, we will complete it next class
     */
}
