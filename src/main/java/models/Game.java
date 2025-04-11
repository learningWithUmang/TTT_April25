package models;
import enums.CellState;
import enums.GameState;
import enums.PlayerType;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import strategy.WinningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves; //help in unDo feature
    private Player winner;
    private int nextPlayerTurnIndex;
    //[p1,p2,p3,p4,p5] = 0th, 1st, 2nd....4th, 0th, 1st...
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    //Game.getBuilder().setDimension().setPlayers().setStrategies().build();

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.nextPlayerTurnIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

    public void printBoard(){
        board.print();
    }

    public void unDo(){
        //
        if(moves.size() == 0){
            System.out.println("There're no moves on the board, undo not possible");
            return;
        }

        Move lastMove = moves.getLast();
        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        //(a-b) % m = (a%m - b%m + m) % m

        nextPlayerTurnIndex = (nextPlayerTurnIndex  -  1 + players.size()) % players.size();

        //update the hashmaps

        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(lastMove, board.getDimension());
        }
        //[0,1,2]

        //idx = 1
        /*
        1. remove the last move from the list
        2. remove the move from the board, make the cell empty
         */
    }

    private boolean checkWinner(Move move){
        //algo to check the winner along the rows, cols, diag
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(move, board.getDimension())){
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(int row, int col){
        //basic boundary checks
        if(row < 0 ||  col < 0 || row >= board.getDimension() || col >= board.getDimension()){
            return false;
        }

        //do you need to extract the cell obj from the board corresponding
        //to this row, col
        //board = [[c1,c2,c3],
        //          [c4,c5,c6] ,
        //          [c7,c8,c9]]

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }

        return  true;
    }

    public void makeMove(){
        //who is the next player to play?
        Player currentPlayer = players.get(nextPlayerTurnIndex);
        System.out.println("It's " + currentPlayer.getName() + "'s turn"); //It's Umang's turn

        //ask that player to make a move?
        Cell dummyCell = currentPlayer.chooseCellToPlay(board);
        int row = dummyCell.getRow();
        int col = dummyCell.getCol();

        if(!validateMove(row, col)){
            System.out.println("It's an invalid move, can you choose again?");
            return;
        }

        //Is the move executed? - No

        //marking the cell as filled and putting the player inside the cell
        //executing the move on the board
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);


        Move move = new Move(currentPlayer, cell);
        moves.add(move);

        //update nextPlayerTurnIdx

        nextPlayerTurnIndex = (nextPlayerTurnIndex + 1) % players.size();

        //check whether this move is a winning move??
        if(checkWinner(move)){
            gameState = GameState.ENDED;
            winner = currentPlayer;
        }else if(moves.size() == board.getDimension()* board.getDimension()) { // draw, all cells are filled
            gameState = GameState.DRAW;
        }
        //do we need to validate the move player wants to make before actually executing it?
        return;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validatePlayerCount() throws PlayerCountNotValidException {
            if(players.size() != dimension - 1){
                throw new PlayerCountNotValidException("Player's count is not equivalent to dimension - 1");
            }
        }

        private void validateUniqueSymbols(){
            //validate if all players have unique symbol
            //?? - Please try to implement this method - HW
            //put all player's symbol in a hashset?
            HashSet<Character> symbols = new HashSet<Character>();
            for(Player player : players){
                Character sym = player.getSymbol().getSymbolChar();
                if(symbols.contains(sym)){
                    throw new RuntimeException("Symbols for all players are not unique");
                }
                symbols.add(player.getSymbol().getSymbolChar());
            }
        }

        private void validateBOTCount() throws BOTCountInvalidException {
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            //# players = d - 1
            //# bots = d - 2
            if(botCount > dimension - 2){
                throw new BOTCountInvalidException("BOTs count is invalid");
            }
        }

        private void validate() throws PlayerCountNotValidException, BOTCountInvalidException {
            validatePlayerCount();
            validateBOTCount();
            validateUniqueSymbols();
        }

        public Game build() throws BOTCountInvalidException, PlayerCountNotValidException {
            //perform all the validations check and
            //if all are passing, then create the obj, otherwise throw an exception
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }

    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}
