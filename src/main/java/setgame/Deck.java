package setgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class handles the deck of a round of SET.
 * Its purpose is to create and store the deck.
 */

public class Deck {

    private final ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (SymbolNumber symbolNumber : SymbolNumber.values()) {
            for (SymbolShape symbolShape : SymbolShape.values()) {
                for (SymbolColor symbolColor : SymbolColor.values()) {
                    for (SymbolShading symbolShading : SymbolShading.values()) {
                        deck.add(new Card(symbolNumber, symbolShape, symbolColor, symbolShading));
                    }
                }
            }
        }
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.removeFirst();
    }

    public int size() {
        return deck.size();
    }
}