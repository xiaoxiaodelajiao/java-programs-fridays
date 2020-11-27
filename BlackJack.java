import java.util.Random;
import java.util.Scanner;

class Card{
    private int suit = 0;
    private int value = 1;
    boolean isFaceUp = true;
    
    Card(int value, int suit){
        this.value = value;
        this.suit = suit;
    }
    
    int getValue() {
        return value;
    }
    
    int getSuit() {
        return suit;
    }
    
    void flip() {
        isFaceUp = !isFaceUp;
    }
    
    String toStr(){
        if (!isFaceUp) {
            return "XX";
        }
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
    
    Card drawCard(boolean faceUp){
        Card card = drawCard();
        card.isFaceUp = faceUp;
        return card;
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

public class BlackJack {
    private Deck deck = new Deck();
    private Card[] playerHand = new Card[5];
    private Card[] dealerHand = new Card[5];
    private int numCardsDealer = 0;
    private int numCardsPlayer = 0;
    
    BlackJack(){}
    
    int getScore(int who, boolean hard) {
        int sum = 0;
        boolean foundAce = false;
        Card[] hand;
        int numCards;
        
        if (who == -1) { // dealer
            hand = dealerHand;
            numCards = numCardsDealer;
        } else { // player
            hand = playerHand;
            numCards = numCardsPlayer;
        }
        
        for (int i = 0; i < numCards; i++) {
            if (hand[i].getValue() > 9) {
                sum += 10;
            } else if (hand[i].getValue() == 1 && !foundAce) {
                if (hard) {
                    sum += 1;
                } else {
                    sum += 11;
                }
                foundAce = true;
            }
            else {
                sum += hand[i].getValue();
            }
        }
        return sum;
    }
    
    void newGame(){
        deck.reset();
        deck.shuffle();
        playerHand[0] = deck.drawCard();
        playerHand[1] = deck.drawCard();
        dealerHand[0] = deck.drawCard(false);
        dealerHand[1] = deck.drawCard();
        numCardsDealer = 2;
        numCardsPlayer = 2;
        printState();
        Scanner sc = new Scanner(System.in);
        boolean playerStood = false;
        while (!playerStood) {
            System.out.println("Press H to hit, or S to stand: ");
            String input = sc.nextLine();
            if (input.equals("h") || input.equals("H")) {
                hit(0);
            } else {
                stand(0);
                playerStood = true;
            }
            printState();
            if (getScore(0, true) > 21) {
                System.out.println("YOU BUSTED");
                playerStood = true;
                return;
            } else if (getScore(0, true) == 21) {
                System.out.println("BLACKJACK");
                playerStood = true;
                return;
            } else if (playerHand[4] != null) {
                System.out.println("BLACKJACK");
                playerStood = true;
                return;
            }
        }
        boolean dealerStood = false;
        dealerHand[0].flip();
        while(!dealerStood) {
            printState();
            System.out.println("Press ENTER to continue: ");
            String input = sc.nextLine();
            int dealerScore = getScore(-1, true);
            if (dealerScore >= 17 && dealerScore < 22) {
                stand(-1);
                System.out.println("DEALER STOOD");
                dealerStood = true;
            } else if (dealerScore > 21) {
                System.out.println("DEALER BUST. PLAYER WINS");
                dealerStood = true;
                return;
            } else if (dealerHand[4] != null || dealerScore == 21) {
                System.out.println("BLACKJACK DEALER WINS");
                dealerStood = true;
                return;
            } else {
                hit(-1);
            }
        }
        int playerScore = getScore(0, false);
        if (playerScore > 21) {
            playerScore = getScore(0, true);
        }
        int dealerScore = getScore(-1, false);
        if (dealerScore > 21) {
            dealerScore = getScore(-1, true);
        }
        if (dealerScore == playerScore) {
            System.out.println("DRAW");
        } else if (dealerScore > playerScore) {
            System.out.println("YOU LOSE");
        } else {
            System.out.println("YOU WIN");
        }
    }
    
    void hit(int who) {
        Card[] hand;
        int numCards;
        if (who == -1) { // dealer
            hand = dealerHand;
            numCards = numCardsDealer;
        } else { // player
            hand = playerHand;
            numCards = numCardsPlayer;
        }
        
        hand[numCards] = deck.drawCard();
        
        if (who == -1) { // dealer
            numCardsDealer++;
        } else { // player
            numCardsPlayer++;
        }
    }
    
    void stand(int who) {
        
    }
    
    void printState(){
        System.out.print("Dealer: ");
        for (int i = 0; i < numCardsDealer; ++i) {
            System.out.print(dealerHand[i].toStr() + " ");
        }
        System.out.println();
        System.out.print("Player: ");
        for (int i = 0; i < numCardsPlayer; ++i) {
            System.out.print(playerHand[i].toStr() + " ");
        }
        System.out.println("Soft: " + getScore(0, false) + " Hard: " + getScore(0, true));
        
    }

    public static void main(String []args){
        BlackJack game = new BlackJack();
        game.newGame();
    } 
}