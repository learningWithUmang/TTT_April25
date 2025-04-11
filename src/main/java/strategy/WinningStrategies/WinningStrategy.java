package strategy.WinningStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {

    //checkWinnerAlgo in O(1) - hashmap
    boolean checkWinner(Move move, int N);

    void handleUndo(Move move, int N);
}


/*

 */