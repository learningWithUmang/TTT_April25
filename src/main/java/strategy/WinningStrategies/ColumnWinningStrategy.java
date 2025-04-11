package strategy.WinningStrategies;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    Map<Integer, Map<Symbol, Integer>> colHashmaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, int N) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //update the count of the symbol in row's hashmap
        if(!colHashmaps.containsKey(col)){
            colHashmaps.put(col, new HashMap<>());
        }

        Map<Symbol,Integer> mp = colHashmaps.get(col);

        if(!mp.containsKey(symbol)){
            mp.put(symbol, 1);
        }else{
            mp.put(symbol, mp.get(symbol) + 1);
        }

        int count = mp.get(symbol);

        if(count == N){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void handleUndo(Move move, int N){
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> colMap = colHashmaps.get(col);
        colMap.put(symbol, colMap.get(symbol) - 1);
    }
}
