package controllers;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Game;
import models.Player;
import strategy.WinningStrategies.WinningStrategy;

import java.util.List;


public class GameController {

    public Game startGame(int dimension,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) throws BOTCountInvalidException, PlayerCountNotValidException {
        return Game.getBuilder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategies(winningStrategies).
                build();
    }

    public void makeMove(){

    }


    public void unDo(){

    }


}

/*
Rest Controller is used to build web APIs
As of now, we are not building web app, we are building a command line app
 */
