package strategy.WinningStrategies;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer,Map<Symbol,Integer>> rowHashmaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, int N) {

        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        //update the count of the symbol in row's hashmap
        if(!rowHashmaps.containsKey(row)){
            rowHashmaps.put(row, new HashMap<>());
        }

        Map<Symbol,Integer> mp = rowHashmaps.get(row);

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
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = rowHashmaps.get(row);
        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }
}
/*
board
Move - (player, cell)
X - {2,1} , {2,2}

O - {2,0}

rowNo, symbol

[       ]
[      ]
[X      ] - {{x,3} , {O,2}}

int,hm

{{2,{}}


{0,X}

HM<int,symbol>

HM <int, HM<symbol, count>>
List<HM> = need to know how many hashmaps are needed? 5
[h1,h2,h3,h4,h5]



*/