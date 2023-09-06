/*
 * Player.java
 * 
 * Computer Science 112, Boston University
 * 
 * The blueprint class for objects that represent a single Blackjack player 
 * 
 * modified by: Youjung Jung, jenjung@bu.edu
 */

import java.util.*;

public class Player {
    /* fields for the player */
    private String name;
    private Card[] hand;
    private int numCards;

    /* constructor */
    public Player( String playerName) {
        this.name = playerName;
        this.hand = new Card[Blackjack.MAX_CARDS_PER_PLAYER];
        this.numCards = 0;
    }

    /*
     * getName - method that returns the player's name
     */
    public String getName() {
        return this.name;
    }

    /*
     * getNumCards - method that returns the current number of cards 
     * in the player's hand.
     */
    public int getNumCards() {
        return this.numCards;
    }

    /*
     * addCard - takes a Card object as parameter and adds the card 
     * to the player's hand 
     */
    public void addCard(Card newCard) {
        if(newCard == null || this.numCards >= 11) {
            throw new IllegalArgumentException();
        } else {
            this.hand[this.numCards]  = newCard;
            this.numCards += 1;
        }
    }

    /* 
     * getCard - takes an integer index as parameter 
     * and returns the card at that specified position.
     */
    public Card getCard(int i) {
        if (i < 0 || i > this.numCards) {
            throw new IllegalArgumentException();
        }
        return this.hand[i];
    }

    /*
     * getHandValue - returns the total value of the player's current hand
     */
    public int getHandValue() {
        int sum = 0;
        for (int i = 0; i < this.numCards; i++) {
            if  (this.hand[i].getValue() == 1 && sum <= 10) {
                sum += 11;
            } else {
                sum += this.hand[i].getValue();
            }    
        }
        return sum;
    }

    /*
     * printHand - prints the current contents of the player's hand 
     * followed by the value of the player's hand
     */
    public void printHand() {
        for ( int i = 0; i < this.numCards; i ++) {
                System.out.print(this.hand[i] + "  ");
        }
        System.out.print("(value = " + this.getHandValue() + ")");
    }

    /*
     * hasBlackJack - returns true if player has Blackjack
     */
    public boolean hasBlackjack() {
        if (this.getHandValue() == 21 && this.numCards == 2) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * wantsHit - returns true if the player wants another hit
     * takes two parameters: a scanner object and Player object
     */
    public boolean wantsHit(Scanner object, Player opponent) {
        System.out.println("Want another hit, " + this.getName() + "  (y/n)?");
        String answer = object.nextLine();
        if ( answer.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * discardCards - gets rid of all the cards in the player's hand
     */
    public void discardCards() {
        this.numCards = 0;
        this.hand = new Card[Blackjack.MAX_CARDS_PER_PLAYER];
    }




    
}
