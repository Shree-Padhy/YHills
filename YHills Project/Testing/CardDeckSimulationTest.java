import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CardDeckSimulationTest {

    @Test
    public void testDeckInitialization() {
        Deck deck = new Deck();
        assertEquals(52, deck.getDeckSize(), "Deck should contain 52 cards upon initialization");
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();
        List<Card> initialDeckOrder = deck.drawRandomCards(10); // Draw some cards to ensure deck order changes
        deck.shuffle();
        List<Card> shuffledDeckOrder = deck.drawRandomCards(10); // Draw cards again after shuffle
        assertNotEquals(initialDeckOrder, shuffledDeckOrder, "Shuffling should change the order of cards");
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        int initialSize = deck.getDeckSize();
        Card drawnCard = deck.drawCard();
        assertEquals(initialSize - 1, deck.getDeckSize(), "Deck size should decrease after drawing a card");
        assertNotNull(drawnCard, "Drawn card should not be null");
    }

    @Test
    public void testDrawRandomCards() {
        Deck deck = new Deck();
        int count = 10;
        List<Card> drawnCards = deck.drawRandomCards(count);
        assertEquals(count, drawnCards.size(), "Drawn cards count should match specified count");
        assertEquals(52 - count, deck.getDeckSize(), "Deck size should decrease after drawing random cards");
    }

    @Test
    public void testCardSorting() {
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> drawnCards = deck.drawRandomCards(20);
        drawnCards.sort(new CardComparator());

        Card previousCard = null;
        for (Card card : drawnCards) {
            if (previousCard != null) {
                assertTrue(new CardComparator().compare(previousCard, card) <= 0, "Cards should be sorted correctly");
            }
            previousCard = card;
        }
    }
}
