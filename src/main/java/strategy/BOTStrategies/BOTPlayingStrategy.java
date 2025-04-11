package strategy.BOTStrategies;

import models.Board;
import models.Cell;
import models.Move;

public interface BOTPlayingStrategy {
    Cell chooseCellToPlay(Board board);
}
