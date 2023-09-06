/**
 * Card.java
 * 
 * A blueprint class to represent an individual playing card.
 * 
 * CS 112, Boston University
 * 
 * completed by: Youjung Jung, jenjung@bu.edu
 */

public class Card {
    // constants for the ranks of non-numeric cards
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    
    // other constants for the ranks
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 13;
    
    // Arrays of strings for the rank names and abbreviations.
    // The name of the rank r is given by RANK_NAMES[r].
    // The abbreviation of the rank r is given by RANK_ABBREVS[r].
    private static final String[] RANK_NAMES = {
      null, "Ace", "2", "3", "4", "5", "6", 
      "7", "8", "9", "10", "Jack", "Queen", "King"
    };
    private static final String[] RANK_ABBREVS = {
      null, "A", "2", "3", "4", "5", "6",
      "7", "8", "9", "10", "J", "Q", "K"
    };
    
    // constants for the suits
    public static final int FIRST_SUIT = 0;
    public static final int LAST_SUIT = 3;
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;
    
    // Arrays of strings for the suit names and abbreviations.
    // The name of the suit s is given by SUIT_NAMES[s].
    // The abbreviation of the suit s is given by SUIT_ABBREVS[s].
    private static final String[] SUIT_NAMES = {
      "Clubs", "Diamonds", "Hearts", "Spades"
    };
    private static final String[] SUIT_ABBREVS = {
      "C", "D", "H", "S"
    };
    
    /***** part 2: getSuitNum *****/
    private static int getSuitNum(String suit) {
        // The return statement below is included so the starter code 
        // will compile.
        // Replace it with your implementation of the method.
        for ( int i = 0; i < SUIT_NAMES.length; i ++) {
            if ( suit.equalsIgnoreCase(SUIT_NAMES[i])) {
                return i;
            }
        }
        return -1;
    }

    /***** Implement parts 3-7 below. *****/
    // Defining fields for the card's rank and card's suit number
    private int rank;
    private int suitNum;

    //constructor that specifies the card's rank and suit number 
    public Card(int r, int s) {
        if (r > 0 && r < 14 && s >= 0 && s < 4) {
            this.rank = r;
            this.suitNum = s;
        } else {
            throw new IllegalArgumentException();
        }
    }
    // constructor 2 that determines the suit number for the specified suit string
    public Card (int r, String cardSuit) {
        if ( r > 0 && r < 14 && getSuitNum(cardSuit) >= 0 && getSuitNum(cardSuit) < 4) {
            this.suitNum = getSuitNum(cardSuit);
            this.rank = r;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /*
     * getRank - accessor method that returns the number representind 
     * the card's rank
     */
    public int getRank() {
        return this.rank;
    }

    /*
     * getRankName - accessor method that returns String representation
     * of the card's rank
     */
    public String getRankName() {
        return RANK_NAMES[this.rank];
    }

    /*
     * getSuitNum - returns the card object's suit number 
     */
    public int getSuitNum() {
        return this.suitNum;
    }

    /*
     * getSuitName - returns the String representation of the card's suit
     */
    public String getSuitName() {
        return SUIT_NAMES[this.suitNum];
    }

    /*
     * getName - returns a string representation of the full name of the card object
     */
    public String getName() {
        return this.getRankName() + " of " + this.getSuitName();
    }

    /* 
     * isAce - returns true if the card is an ace and false if otherwise
     */
    public boolean isAce() {
        if (this.getRankName().equals("Ace")) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * isFaceCard - returns true if the card is a face card (Jack, Queen, or King)
     * false if otherwise
     */
    public boolean isFaceCard() {
        if ( this.getRank() > 10) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * getValue - returns card object's value 
     * returns 10 for facecards and rank number for other cards
     */
    public int getValue() {
        if (this.isFaceCard() == true) {
            return 10;
        } else {
            return this.rank;
        }
    }

    /*
     * toString - returns a string representation of the card object 
     * consisting of rank abbreviation followed by suit abbreviation
     */
    public String toString() {
        return RANK_ABBREVS[this.rank] + SUIT_ABBREVS[this.suitNum];
    }

    /*
     * sameSuitAs - returns true if the parameter card object has the same suit 
     * as the called objects
     */
    public boolean sameSuitAs(Card other) {
        if (other == null) {
            return false;
        }
        if (this.getSuitNum() == other.getSuitNum()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * equals - returns true if the parameter card objeect is equal to 
     * the called object
     */
    public boolean equals(Card other) {
        if (other == null) {
            return false;
        }
        if ( this.getSuitNum() == other.getSuitNum() && 
                this.getRank() == other.getRank()) {
                    return true;
                } else {
                    return false;
                }
    }

    /*public static void main(String[] args) {
        
        System.out.println(getSuitNum("foo"));
        System.out.println(getSuitNum("CLUBS"));
        Card c5 = new Card(7, 5);
        System.out.println(c5);

    }
    */


}
