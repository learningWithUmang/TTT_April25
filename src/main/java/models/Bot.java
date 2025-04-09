package models;

import enums.BOTDifficultyLevel;
import enums.PlayerType;
import factory.BOTPlayingStrategyFactory;
import strategy.BOTPlayingStrategy;

public class Bot extends Player{
    private BOTDifficultyLevel botDifficultyLevel;
    private BOTPlayingStrategy botPlayingStrategy;
    public Bot(String name, Symbol symbol, BOTDifficultyLevel botDifficultyLevel){
        //constructor chaining - When you create a child object, parent is created first
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BOTPlayingStrategyFactory.getBOTPlayingStrategy(botDifficultyLevel);
    }

    public Move makeMove(){
        //this.botPlayingStrategy.makeMove();
        //using it's own algorithm
        return null;
    }

}
