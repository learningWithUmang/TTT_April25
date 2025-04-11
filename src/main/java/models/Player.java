package models;

import enums.PlayerType;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Cell chooseCellToPlay(Board board){
        System.out.println("Enter the row number where you want to play?");
        //Take the row no in the input
        int row = scanner.nextInt();

        System.out.println("Enter the column number where you want to play?");
        //Take the col no in the input
        int col = scanner.nextInt();

        //inform back the caller the row , col
        //he will provide where to make the move? row, col?
        return new Cell(row, col);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
