import java.util.Random;

class Card{
    int suit = 0;
    int value = 1;
    
    Card(int value, int suit){
        this.value = value;
        this.suit = suit;
    }
    
    String toStr(){
        String suit = "♥♦♣♠";
        if(value == 1) return ("A" + suit.charAt(this.suit));
        else if(value == 11) return ("J" + suit.charAt(this.suit));
        else if(value == 12) return ("Q" + suit.charAt(this.suit));
        else if(value == 13) return ("K" + suit.charAt(this.suit));
        return ("" + this.value + suit.charAt(this.suit));
    }
}

class Deck{
    private int cardsDrawn = -1;
    private Card[] cardsArray = new Card[52];
    Random random = new Random();
    Deck(){
        for(int value = 1; value < 14; value ++){
            for(int suit = 0; suit < 4; suit ++){
                cardsArray[(value-1)*4+suit] = new Card(value, suit);
            }
        }
    }
    
    String toStr(){
        String wholeDeck = "";
        for(int i = 0; i < 51; i ++){
            wholeDeck += this.cardsArray[i].toStr() + ", ";
        }
        wholeDeck += this.cardsArray[51].toStr();
        return wholeDeck;
    }
    
    Card getCard(int index){
        return cardsArray[index];
    }
    
    Card drawCard(){
        cardsDrawn ++;
        if(cardsDrawn < 52) return cardsArray[cardsDrawn];
        return null;
    }
    
    void reset(){
        cardsDrawn = -1;
    }
    
    void shuffle(){
        int s;
        Card temp;
        for(int i = 0; i < 52; i ++){
            s = random.nextInt(52);
            temp = this.cardsArray[s];
            this.cardsArray[s] = this.cardsArray[i];
            this.cardsArray[i] = temp;
        }
    }
}

public class CardDeck{
    public static void main(String []args){
        
    }
}