package setgame;

public class Board {

    private final Card[][] cards;

    public Board() {
        cards = new Card[3][7];
    }

    public Card[][] getCards() {
        return cards;
    }

    public Card getCard(int row, int column) {
        return cards[row][column];
    }

    public int getNumberOfCards() {
        int number = 0;
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 6; column++) {
                if (cards[row][column] != null) {
                    number++;
                }
            }
        }
        return number;
    }

    // See https://www.youtube.com/watch?v=EkFX9jUJPKk to learn why this works
    public boolean checkIfSet(Card card1, Card card2, Card card3) {
        if ((card1.number() + card2.number() + card3.number()) % 3 != 0) {
            return false;
        }
        if ((card1.color() + card2.color() + card3.color()) % 3 != 0) {
            return false;
        }
        if ((card1.shape() + card2.shape() + card3.shape()) % 3 != 0) {
            return false;
        }
        return (card1.shading() + card2.shading() + card3.shading()) % 3 == 0;
    }

    public int[] findSet() {
        Card[] boardAsList = new Card[21];
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 6; column++) {
                boardAsList[row * 7 + column] = cards[row][column];
            }
        }
        for (int i = 0; i <= 20; i++) {
            Card card1 = boardAsList[i];
            if (card1 == null) {
                continue;
            }
            for (int j = i + 1; j <= 20; j++) {
                Card card2 = boardAsList[j];
                if (card2 == null) {
                    continue;
                }
                for (int k = j + 1; k <= 20; k++) {
                    Card card3 = boardAsList[k];
                    if (card3 == null) {
                        continue;
                    }
                    if (checkIfSet(card1, card2, card3)) {
                        return new int[]{i / 7, i % 7, j / 7, j % 7, k / 7, k % 7};
                    }
                }
            }
        }
        return null;
    }

    public int[] findEmptySlots() {
        int[] emptySlots = new int[6];
        int counter = 0;
        for (int column = 0; column <= 6; column++) {
            for (int row = 0; row <= 2; row++) {
                if (cards[row][column] == null) {
                    emptySlots[counter] = row;
                    emptySlots[counter + 1] = column;
                    counter += 2;
                    if (counter == 6) {
                        return emptySlots;
                    }
                }
            }
        }
        return null;
    }
}