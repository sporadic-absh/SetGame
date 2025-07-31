package setgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InstructionView {

    public BorderPane getView() {
        // 1. Overall layout
        BorderPane instructionView = new BorderPane();

        VBox sidebar = new VBox(5);
        BorderPane.setMargin(sidebar, new Insets(0, 10, 0, 10));
        instructionView.setLeft(sidebar);

        // 2. Create subview "Deck"
        VBox deckText = new VBox();
        BorderPane.setAlignment(deckText, Pos.TOP_LEFT);

        Text deckText1 = new Text("""
                The deck consists of 81 unique cards.
                Each card has four features: number, color, shape, and shading.
                Each of these features comes in three variations:
                
                Number: one, two, or three.""");
        deckText1.getStyleClass().add("label");
        deckText.getChildren().add(deckText1);
        deckText.getChildren().add(createExample(new Card[]{new Card(0,0,0,0), new Card(1,0,0,0), new Card(2,0,0,0)}));

        Text deckText2 = new Text("\nColor: green, red, or blue.");
        deckText2.getStyleClass().add("label");
        deckText.getChildren().add(deckText2);
        deckText.getChildren().add(createExample(new Card[]{new Card(0,0,1,1), new Card(0,1,1,1), new Card(0,2,1,1)}));

        Text deckText3 = new Text("\nShape: circle, triangle, or square.");
        deckText3.getStyleClass().add("label");
        deckText.getChildren().add(deckText3);
        deckText.getChildren().add(createExample(new Card[]{new Card(1,1,0,2), new Card(1,1,1,2), new Card(1,1,2,2)}));

        Text deckText4 = new Text("\nShading: outlined, grey, or solid.");
        deckText4.getStyleClass().add("label");
        deckText.getChildren().add(deckText4);
        deckText.getChildren().add(createExample(new Card[]{new Card(2,2,2,0), new Card(2,2,2,1), new Card(2,2,2,2)}));

        Text deckText5 = new Text("\nEvery possible combination occurs exactly once!");
        deckText5.getStyleClass().add("label");
        deckText.getChildren().add(deckText5);

        Button deckButton = new Button("Deck");
        deckButton.setOnMouseClicked((_) -> instructionView.setCenter(deckText));
        deckButton.getStyleClass().add("instruction-button");
        sidebar.getChildren().add(deckButton);

        // 3. Create subview "Rules"
        Text rulesText = new Text("""
                The object of the game is to identify three cards that form a SET.
                Three cards are a SET if the following holds for each of the four features:
                The feature is the same on each of the three cards,
                or the feature is different on each pair of the three cards.
                
                When a SET is identified, it is removed from the board. Three new cards
                are then added to the board if there are less than twelve cards on the board.
                It is possible that there is no SET on the board!
                This is why three cards can also be added by clicking the respective button,
                up to a maximum of 21 cards on the board.
                
                The player starts at zero points and gets -1 point for using the "Add Three Cards" button.
                The player gets +1 point for each remaining SET that is identified when the deck is empty.
                The game ends when the deck is depleted and all remaining SETs on the board are identified.
                The player wins if they have zero or more points.""");
        rulesText.getStyleClass().add("label");
        BorderPane.setAlignment(rulesText, Pos.TOP_LEFT);

        Button rulesButton = new Button("Rules");
        rulesButton.setOnMouseClicked(_ -> instructionView.setCenter(rulesText));
        rulesButton.getStyleClass().add("instruction-button");
        sidebar.getChildren().add(rulesButton);

        // 4. Create subview "Examples"
        VBox examplesText = new VBox();
        BorderPane.setAlignment(examplesText, Pos.TOP_LEFT);

        Text examplesText1 = new Text("Examples for SETs:");
        examplesText1.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText1);
        examplesText.getChildren().add(createExample(new Card[]{new Card(0,1,2,0), new Card(0,1,2,1), new Card(0,1,2,2)}));
        Text examplesText2 = new Text("""
                All cards have the same number, the same color,
                the same shape, and pairwise different shading.
                """);
        examplesText2.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText2);

        examplesText.getChildren().add(createExample(new Card[]{new Card(0,2,0,2), new Card(1,2,1,2), new Card(2,2,2,2)}));
        Text examplesText3 = new Text("""
                All cards have the same color, the same shading,
                pairwise different numbers, and pairwise different shapes.
                """);
        examplesText3.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText3);

        examplesText.getChildren().add(createExample(new Card[]{new Card(1,2,0,1), new Card(2,0,1,2), new Card(0,1,2,0)}));
        Text examplesText4 = new Text("""
                All cards have pairwise different colors, pairwise different shading,
                pairwise different numbers, and pairwise different shapes.
                """);
        examplesText4.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText4);

        Text examplesText5 = new Text("Non-example for a SET:");
        examplesText5.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText5);
        examplesText.getChildren().add(createExample(new Card[]{new Card(1,0,2,0), new Card(1,1,2,0), new Card(1,2,2,2)}));
        Text examplesText6 = new Text("""
                All cards have the same number, the same shape, and pairwise different colors,
                but the first two cards have the same shading, while the third one is different.
                
                Whenever two of three cards share a feature in which the third one is different, then this is not a SET!""");
        examplesText6.getStyleClass().add("label");
        examplesText.getChildren().add(examplesText6);

        Button examplesButton = new Button("Examples");
        examplesButton.setOnMouseClicked(_ -> instructionView.setCenter(examplesText));
        examplesButton.getStyleClass().add("instruction-button");
        sidebar.getChildren().add(examplesButton);

        return instructionView;
    }

    public HBox createExample(Card[] cards) {
        HBox hBox = new HBox(10);
        for (int i = 0; i <= 2; i++) {
            VBox vBox = new VBox(5);
            vBox.setAlignment(Pos.CENTER);
            for (int j = 0; j <= cards[i].number(); j++) {
                vBox.getChildren().add(cards[i].createShape(cards[i].shape(), cards[i].color(), cards[i].shading(), 0.4));
            }
            ToggleButton toggleButton = new ToggleButton("", vBox);
            toggleButton.getStyleClass().add("card-button-small");
            hBox.getChildren().add(toggleButton);
        }
        return hBox;
    }
}