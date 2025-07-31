package setgame;

public class Game {

    private final Board board;
    private final Deck deck;
    private int points;
    private boolean help; // describes whether the player found a set by using the find set button

    public Game() {
        board = new Board();
        deck = new Deck();
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 3; column++) {
                board.getCards()[row][column] = deck.draw();
            }
        }
        points = 0;
        help = false;
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getPoints() {
        return points;
    }

    public void increasePoints() {
        points++;
    }

    public void decreasePoints() {
        points--;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public void addThreeCards(int r1, int c1, int r2, int c2, int r3, int c3) { // r = row, c = column
        board.getCards()[r1][c1] = deck.draw();
        board.getCards()[r2][c2] = deck.draw();
        board.getCards()[r3][c3] = deck.draw();
    }
}