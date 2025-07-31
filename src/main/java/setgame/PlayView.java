package setgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PlayView {

    private Game game;

    public PlayView() {
        this.game = new Game();
    }

    public BorderPane getView() {
        // 1. Overall layout
        BorderPane playView = new BorderPane();

        VBox sidebar = new VBox(5);
        BorderPane.setMargin(sidebar, new Insets(0, 10, 0, 10));
        playView.setLeft(sidebar);

        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(10);
        boardGrid.setVgap(10);
        playView.setCenter(boardGrid);

        // 2. Create info labels
        Label cardsInDeckLabel = new Label("Cards in deck: " + game.getDeck().size());
        cardsInDeckLabel.getStyleClass().add("label");

        Label pointsLabel = new Label("Points: " + game.getPoints());
        pointsLabel.getStyleClass().add("label");

        Label lastActionLabel = new Label("");
        lastActionLabel.getStyleClass().add("label");

        Label resultLabel = new Label("");
        resultLabel.getStyleClass().add("label");

        // 3. Create board buttons
        ToggleButton[][] cardButtons = new ToggleButton[3][7];
        VBox[][] cardButtonVBoxes = new VBox[3][7];

        PlayViewLogic playViewLogic = new PlayViewLogic(game, cardButtons, cardButtonVBoxes, cardsInDeckLabel, pointsLabel, lastActionLabel, resultLabel);

        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 6; column++) {
                cardButtonVBoxes[row][column] = new VBox(5);
                cardButtonVBoxes[row][column].setAlignment(Pos.CENTER);
                cardButtons[row][column] = new ToggleButton("", cardButtonVBoxes[row][column]);
                cardButtons[row][column].setOnMouseClicked(_ -> playViewLogic.boardClickEvent());
                boardGrid.add(cardButtons[row][column], column, row);
            }
        }
        playViewLogic.updateBoard();

        // 4. Fill sidebar
        Button newGameButton = new Button("New Game");
        newGameButton.setOnMouseClicked((_) -> game = playViewLogic.newGameButtonClickEvent());
        newGameButton.getStyleClass().add("play-button");
        sidebar.getChildren().add(newGameButton);

        Button addThreeCardsButton = new Button("Add Three Cards");
        addThreeCardsButton.setOnMouseClicked(_ -> playViewLogic.addThreeCardsButtonClickEvent());
        addThreeCardsButton.getStyleClass().add("play-button");
        sidebar.getChildren().add(addThreeCardsButton);

        Button findSetButton = new Button("Find a SET");
        findSetButton.setOnMouseClicked(_ -> playViewLogic.findSetButtonClickEvent());
        findSetButton.getStyleClass().add("play-button");
        sidebar.getChildren().add(findSetButton);

        sidebar.getChildren().add(cardsInDeckLabel);
        sidebar.getChildren().add(pointsLabel);
        sidebar.getChildren().add(lastActionLabel);
        sidebar.getChildren().add(resultLabel);

        return playView;
    }
}