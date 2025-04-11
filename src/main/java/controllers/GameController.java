package controllers;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Game;
import models.Player;
import strategy.WinningStrategies.WinningStrategy;

import java.util.List;


public class GameController {

    //startGame?r?c
    //access to his own game instance
    public Game startGame(int dimension,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) throws BOTCountInvalidException, PlayerCountNotValidException {
        return Game.getBuilder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategies(winningStrategies).
                build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }


    public void unDo(Game game){
        game.unDo();
    }

    public void printBoard(Game game){
        game.printBoard();
    }


}

/*
Rest Controller is used to build web APIs
As of now, we are not building web app, we are building a command line app
 */
