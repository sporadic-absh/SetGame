package setgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SetGameApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Create subviews
        PlayView playView = new PlayView();
        InstructionView instructionView = new InstructionView();

        // 2. Overall layout
        BorderPane layout = new BorderPane();

        HBox topBar = new HBox(5);
        BorderPane.setMargin(topBar, new Insets(10));
        layout.setTop(topBar);

        Button playButton = new Button("Play");
        playButton.setOnMouseClicked(_ -> layout.setCenter(playView.getView()));
        playButton.getStyleClass().add("play-button");
        topBar.getChildren().add(playButton);

        Button instructionButton = new Button("Instruction");
        instructionButton.setOnMouseClicked(_ -> layout.setCenter(instructionView.getView()));
        instructionButton.getStyleClass().add("instruction-button");
        topBar.getChildren().add(instructionButton);

        // 3. Prepare the window
        Scene scene = new Scene(layout, 1400, 800);
        scene.getStylesheets().add("style.css");

        stage.setTitle("SET");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}