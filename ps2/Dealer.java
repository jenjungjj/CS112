/*
 * Dealer.java
 * 
 * Computer Science 112, Boston University
 * 
 * The blueprint class for objects that represent the Dealer. 
 * 
 * modified by: Youjung Jung, jenjung@bu.edu
 */

import java.util.*;
public class Dealer extends Player{
    private boolean revealCard;

    /* constructor */
    public Dealer() {
        super("dealer");
        this.revealCard = false;
    }

    /*
     * revealFirstCard - mutator method that changes the value of the called object's boolean
     * field to true so that the dealer's card can be revealed
     */
    public void revealFirstCard() {
        this.revealCard = true;
    }

    /*
     * printHand - overrides the inherited version of this method 
     * prints the current contents of the player's hand 
     * followed by the value of the player's hand
     */
    public void printHand() {
        if (this.revealCard == false) {
            System.out.print("XX" + "  ");
            for (int i = 1; i < getNumCards() ; i++) {
                System.out.print(getCard(i) + "  ");
            }
        } else {
            for ( int i = 0; i < getNumCards(); i ++) {
                System.out.print(getCard(i) + "  ");
            }
            System.out.print("(value = " + this.getHandValue() + ")");
        }
    }


    /*
     * wantsHit - overrides the inherited version of this method
     * returns true if the player wants another hit
     * takes two parameters: a scanner object and Player object
     */
    public boolean wantsHit(Scanner object, Player opponent) {
        if (opponent.getHandValue() < 17 ) {
            if( opponent.getHandValue() < this.getHandValue()){
                return false;
            } else {
                return true;
            }
        } else {
            if (opponent.getHandValue() <= this.getHandValue()) {
                return false;
            } else {
                return true;
            }
        }
    }

    /*
     * discardCards - overrides the inherited version of this method
     * gets rid of all the cards in the player's hand
     */
    public void discardCards() {
        super.discardCards();
        this.revealCard = false;
    }




    
}
