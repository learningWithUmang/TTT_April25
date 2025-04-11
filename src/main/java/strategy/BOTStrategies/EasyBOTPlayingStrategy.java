package strategy.BOTStrategies;

import enums.CellState;
import models.Board;
import models.Cell;
import models.Move;

import java.util.List;

public class EasyBOTPlayingStrategy implements BOTPlayingStrategy{


    @Override
    public Cell chooseCellToPlay(Board board) {
        for(List<Cell> cells: board.getBoard()){
            for(Cell cell: cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return cell;
                }
            }
        }
        return null;
    }
}
