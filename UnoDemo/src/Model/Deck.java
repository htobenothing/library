
package Model;

import java.util.ArrayList;
import java.util.Random;


public class Deck {
    ArrayList<Card> CardList;

    public Deck(ArrayList<Card> CardList) {
        this.CardList = CardList;
    }

    public ArrayList<Card> getCardList() {
        return CardList;
    }
    
    public Card drawOneCard(){
        int size = this.CardList.size();
        int i = (int)(Math.random()*size); 
        Card drawCard = CardList.get(i);
        this.CardList.remove(i);
        return drawCard;
    }
    
}
