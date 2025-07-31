package setgame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                for (int k = 0; k <= 2; k++) {
                    for (int l = 0; l <= 2; l++) {
                        deck.add(new Card(i, j, k, l));
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