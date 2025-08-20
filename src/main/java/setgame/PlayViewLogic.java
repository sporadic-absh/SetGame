package setgame;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

/**
 * This class handles the logic of the UI in the play view.
 * It updates the UI according to the internal state of the game (class Game).
 * It also defines button logic of the play view.
 */

public class PlayViewLogic {

    private Game game;
    private final ToggleButton[][] cardButtons;
    private final VBox[][] cardButtonVBoxes;
    private final Label cardsInDeckLabel;
    private final Label pointsLabel;
    private final Label lastActionLabel;
    private final Label resultLabel;

    public PlayViewLogic(Game game, ToggleButton[][] cardButtons, VBox[][] cardButtonVBoxes, Label cardsInDeckLabel, Label pointsLabel, Label lastActionLabel, Label resultLabel) {
        this.game = game;
        this.cardButtons = cardButtons;
        this.cardButtonVBoxes = cardButtonVBoxes;
        this.cardsInDeckLabel = cardsInDeckLabel;
        this.pointsLabel = pointsLabel;
        this.lastActionLabel = lastActionLabel;
        this.resultLabel = resultLabel;
    }

    public void updateButton(ToggleButton cardButton, VBox cardButtonVBox, Card card) {
        cardButton.getStyleClass().clear();
        cardButton.getStyleClass().add("card-button");
        cardButtonVBox.getChildren().clear();
        if (card == null) {
            cardButton.setVisible(false);
            return;
        }
        for (int i = 0; i <= card.symbolNumber().ordinal(); i++) {
            cardButtonVBox.getChildren().add(card.createShape(card.symbolShape(), card.symbolColor(), card.symbolShading(), 1));
        }
        cardButton.setVisible(true);
    }

    public void updateBoard() {
        Board board = game.getBoard();
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 6; column++) {
                updateButton(cardButtons[row][column], cardButtonVBoxes[row][column], board.getCard(row, column));
            }
        }
    }

    public void boardClickEvent() {
        int[] pressedButtons = new int[6];
        int counter = 0;
        for (int column = 0; column <= 6; column++) {
            for (int row = 0; row <= 2; row++) {
                if (cardButtons[row][column].isSelected()) {
                    pressedButtons[counter] = row;
                    pressedButtons[counter + 1] = column;
                    counter += 2;
                    if (counter == 6) {
                        checkSelectedCards(pressedButtons);
                        return;
                    }
                }
            }
        }
    }

    public void checkSelectedCards(int[] pos) { // pos = position
        Board board = game.getBoard();
        if (board.checkIfSet(board.getCard(pos[0], pos[1]), board.getCard(pos[2], pos[3]), board.getCard(pos[4], pos[5]))) {
            if (game.getDeck().size() == 0) {
                game.increasePoints();
                pointsLabel.setText("Points: " + game.getPoints());
            }
            if (board.getNumberOfCards() <= 12) {
                game.addThreeCards(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5]);
            } else {
                for (int i = 0; i <= 5; i += 2) {
                    board.getCards()[pos[i]][pos[i+1]] = null;
                }
            }
            cardsInDeckLabel.setText("Cards in deck: " + game.getDeck().size());
            lastActionLabel.setText("SET was removed!");
            if (game.getDeck().size() == 0 && board.findSet() == null) {
                if (game.getPoints() < 0) {
                    resultLabel.setText("You lose! :-(");
                } else {
                    if (game.isHelp()) {
                        resultLabel.setText("You win, assisted. ;-)");
                    }
                    if (!game.isHelp()) {
                        resultLabel.setText("You win! :-)");
                    }
                }
            }
        } else {
            lastActionLabel.setText("This is not a SET!");
        }
        for (int i = 0; i <= 5; i += 2) {
            cardButtons[pos[i]][pos[i+1]].setSelected(false);
        }
        updateBoard();
    }

    public Game newGameButtonClickEvent() {
        game = new Game();
        updateBoard();
        cardsInDeckLabel.setText("Cards in deck: " + game.getDeck().size());
        pointsLabel.setText("Points: " + game.getPoints());
        lastActionLabel.setText("New game started!");
        resultLabel.setText("");
        return game;
    }

    public void addThreeCardsButtonClickEvent() {
        if (game.getDeck().size() == 0) {
            lastActionLabel.setText("Deck is empty!");
            return;
        }
        Board board = game.getBoard();
        int[] emptySlots = board.findEmptySlots();
        if (emptySlots != null) {
            game.addThreeCards(emptySlots[0], emptySlots[1], emptySlots[2], emptySlots[3], emptySlots[4], emptySlots[5]);
            game.decreasePoints();
            cardsInDeckLabel.setText("Cards in deck: " + game.getDeck().size());
            pointsLabel.setText("Points: " + game.getPoints());
            lastActionLabel.setText("Three cards added!");
        } else {
            lastActionLabel.setText("Board is full!");
        }
        updateBoard();
    }

    public void findSetButtonClickEvent() {
        Board board = game.getBoard();
        int[] set = board.findSet();
        if (set == null) {
            lastActionLabel.setText("No SET on the board!");
            return;
        }
        for (int i = 0; i <= 2; i++) {
            cardButtons[set[2 * i]][set[2 * i + 1]].getStyleClass().clear();
            cardButtons[set[2 * i]][set[2 * i + 1]].getStyleClass().add("card-button-set");
        }
        lastActionLabel.setText("SET found!");
        game.setHelp(true);
    }
}