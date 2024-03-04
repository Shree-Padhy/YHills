import java.util.*;

// Enum for card suits
enum Suit {
    SPADE, CLUB, HEART, DIAMOND
}

// Enum for card ranks
enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

// Card class representing a playing card
class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit + "s";
    }
}

// Custom comparator for sorting cards based on color, suit, and value
class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        // First, compare by color (Red: Heart, Diamond; Black: Spade, Club)
        int colorComparison = getColorValue(card1.getSuit()) - getColorValue(card2.getSuit());
        if (colorComparison != 0) {
            return colorComparison;
        }

        // Then, compare by suit within each color
        int suitComparison = card1.getSuit().compareTo(card2.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }

        // Finally, compare by card value (ascending)
        return card1.getRank().compareTo(card2.getRank());
    }

    private int getColorValue(Suit suit) {
        return (suit == Suit.HEART || suit == Suit.DIAMOND) ? 0 : 1;
    }
}

// Deck class representing a deck of cards
class Deck {
    private List<Card> cards;

    public Deck() {
        initializeDeck();
    }

    private void initializeDeck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        int size = cards.size();
        Random rand = new Random();

        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            // Swap the cards at positions i and j
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }

        System.out.println("\nDeck shuffled successfully.\n");
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // Return null if the deck is empty
    }

    public int getDeckSize() {
        return cards.size();
    }

    public List<Card> drawRandomCards(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card drawnCard = drawCard();
            if (drawnCard != null) {
                drawnCards.add(drawnCard);
            }
        }
        return drawnCards;
    }

    @Override
    public String toString() {
        return "Deck size: " + getDeckSize();
    }
}

public class CardDeckSimulation {
    public static void main(String[] args) {
        // Create a deck
        Deck deck = new Deck();

        // Shuffle the deck (optional)
        deck.shuffle();

        // Draw 20 random cards
        List<Card> drawnCards = deck.drawRandomCards(20);

        // Display the drawn cards before sorting
        System.out.println("Drawn Cards before sorting:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }

        // Sort the drawn cards using custom comparator
        drawnCards.sort(new CardComparator());

        // Display the sorted cards
        System.out.println("\nDrawn Cards after sorting:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }

        // Display deck size after drawing cards
        System.out.println("\n" + deck);

        System.out.println("Total strength of the deck: " + deck.getDeckSize());

    }
}
