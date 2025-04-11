package strategy.WinningStrategies;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagHashmap = new HashMap<>();
    private Map<Symbol, Integer> rightDiagHashmap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int N) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //check if {row, col} is part of the left diagonal
        if(row == col){
            leftDiagHashmap.put(symbol, leftDiagHashmap.getOrDefault(symbol,0) + 1);
            if(leftDiagHashmap.get(symbol) == N){
                return true;
            }
        }

        if(row + col == N - 1){
            rightDiagHashmap.put(symbol, rightDiagHashmap.getOrDefault(symbol, 0) + 1);
            if(rightDiagHashmap.get(symbol) == N){
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Move move, int N) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col){
            leftDiagHashmap.put(symbol,leftDiagHashmap.get(symbol) - 1);
        }
        if(row + col == N - 1){
            rightDiagHashmap.put(symbol,rightDiagHashmap.get(symbol) - 1);
        }
    }
}

/*
0,0
1,1
2,2,
3,3

[O O O]
[O O O]
[O O O]



leftDia = {{0,1]}
rightDia {}

 */
