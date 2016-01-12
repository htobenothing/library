
package Model;

import java.util.ArrayList;

public class Game {
    
    String GameID;
    ArrayList<Player> PLayerList;
    
    int roundLimit;
    int scoreLimit;

    public Game(String GameID, ArrayList<Player> PLayerList, int roundLimit, int scoreLimit) {
        this.GameID = GameID;
        this.PLayerList = PLayerList;
        this.roundLimit = roundLimit;
        this.scoreLimit = scoreLimit;
    }

    public String getGameID() {
        return GameID;
    }

    public ArrayList<Player> getPLayerList() {
        return PLayerList;
    }

    public int getRoundLimit() {
        return roundLimit;
    }

    public int getScoreLimit() {
        return scoreLimit;
    }
    public void gameEndProcess(){
        
    }
    public Round createnewround(){
        return null;
    } 
}
