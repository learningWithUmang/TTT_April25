package models;
import enums.GameState;
import enums.PlayerType;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import strategy.WinningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerTurnIndex;
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

    public Player checkWinner(){
        //algo to check the winner along the rows, cols, diag
        //
        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.checkWinner();
        }
        return null;
    }

    public Move makeMove(){
        //who is the next plater to play?
        //ask that player to make a move?
        return null;
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
}
