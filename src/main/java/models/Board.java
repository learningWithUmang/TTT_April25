package models;
import java.util.*;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    Board(int dimension){
        this.dimension = dimension;
        this.board = new ArrayList<>();
        //d = 5, 5*5  , board = [[c1,c2,c3] , [...], [...]]
        //[[c1,c2,c3,c4,c5] , [.....] , [....], []. []]
        for(int i = 0 ; i < dimension ; i++){
            this.board.add(new ArrayList<>());
            for(int j = 0 ; j < dimension ; j++){
                this.board.get(i).add(new Cell(i,j));
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
