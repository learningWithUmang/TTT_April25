package factory;

import enums.BOTDifficultyLevel;
import strategy.BOTStrategies.BOTPlayingStrategy;
import strategy.BOTStrategies.EasyBOTPlayingStrategy;
import strategy.BOTStrategies.HardBOTPlayingStrategy;
import strategy.BOTStrategies.MediumBOTPlayingStrategy;

public class BOTPlayingStrategyFactory {
    public static BOTPlayingStrategy getBOTPlayingStrategy(BOTDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BOTDifficultyLevel.EASY)){
            return new EasyBOTPlayingStrategy();
        }else if(botDifficultyLevel.equals(BOTDifficultyLevel.MEDIUM)){
            return new MediumBOTPlayingStrategy();
        }else{
            return new HardBOTPlayingStrategy();
        }
    }
}
