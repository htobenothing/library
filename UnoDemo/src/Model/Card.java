
package Model;

public class Card {
    int CardID;
    int Value;
    String Color;
    String specialAbility;
    String picture;
    

    public Card(int CardID, int Value, String Color, String specialAbility, String picture) {
        this.CardID = CardID;
        this.Value = Value;
        this.Color = Color;
        this.specialAbility = specialAbility;
        this.picture = picture;
    }

    public int getCardID() {
        return CardID;
    }

    public int getValue() {
        return Value;
    }

    public String getColor() {
        return Color;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public String getPicture() {
        return picture;
    }
    
}
