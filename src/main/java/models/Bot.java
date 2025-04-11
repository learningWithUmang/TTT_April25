package models;

import enums.BOTDifficultyLevel;
import enums.PlayerType;
import factory.BOTPlayingStrategyFactory;
import strategy.BOTStrategies.BOTPlayingStrategy;

public class Bot extends Player{
    private BOTDifficultyLevel botDifficultyLevel;
    private BOTPlayingStrategy botPlayingStrategy;
    public Bot(String name, Symbol symbol, BOTDifficultyLevel botDifficultyLevel){
        //constructor chaining - When you create a child object, parent is created first
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BOTPlayingStrategyFactory.getBOTPlayingStrategy(botDifficultyLevel);
    }

    public Cell chooseCellToPlay(Board board){
        //automated

        //inform back the caller the row , col
        //he will provide where to make the move? row, col?
        return botPlayingStrategy.chooseCellToPlay(board);
    }

}
