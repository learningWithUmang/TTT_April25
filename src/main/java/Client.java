import controllers.GameController;
import enums.PlayerType;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Game;
import models.Player;
import models.Symbol;
import strategy.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws BOTCountInvalidException, PlayerCountNotValidException {


        Player p1 = new Player("Umang", new Symbol('U',""), PlayerType.HUMAN);
        Player p2 = new Player("Umang", new Symbol('A',""), PlayerType.HUMAN);
        Player p3 = new Player("Umang", new Symbol('X',""), PlayerType.HUMAN);

        List<Player> players = List.of(p1,p2,p3);

        List<WinningStrategy> winningStrategies = new ArrayList<>();

        GameController gameController = new GameController();

        gameController.startGame(5, players, winningStrategies);


        System.out.println();
    }
    /*
    Let;s end it here for today.
    HW - Go through whatever we've written so far nicely (Please)
    HW - Please try to continue the impl, we will complete it next class
     */
}
