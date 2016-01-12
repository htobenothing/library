package Model;

import Model.Card;

import java.util.HashMap;

public class Player {

    int playerID;
    String playerName;
    HashMap<Card, Boolean> handCards;
    int totalScore;

    public Player(int playerID, String playerName, 
            HashMap<Card, Boolean> handCards, int totalScore) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.handCards = handCards;
        this.totalScore = totalScore;
    }

    
    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public HashMap<Card, Boolean> getHandCards() {
        return handCards;
    }

    public int getTotalScore() {
        return totalScore;
    }
    
    public void drawCard(int number){
        
        switch (number){
            case 1:
                // draw one card;
                break;
            case 2:
                // draw two card;
                break;
            case 4:
                break;
            case 7:
                break;
            
                
        }
                
    }
    public Card putOne(Card c){
        
        this.handCards.remove(c);
        return c;
    }
    
    public void Uno(Player p){
         
    }
    public String Uno(){
        return "";
    }
}
