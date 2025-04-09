package factory;

import enums.BOTDifficultyLevel;
import strategy.BOTPlayingStrategy;
import strategy.EasyBOTPlayingStrategy;
import strategy.HardBOTPlayingStrategy;
import strategy.MediumBOTPlayingStrategy;

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
