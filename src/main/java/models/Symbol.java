package models;

public class Symbol {
    private char symbolChar;

    //url of the avatar
    private String avatarUrl;

    public Symbol(char symbolChar, String avatarUrl){
        this.symbolChar = symbolChar;
        this.avatarUrl = avatarUrl;
    }

    public char getSymbolChar() {
        return symbolChar;
    }

    public void setSymbolChar(char symbolChar) {
        this.symbolChar = symbolChar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
