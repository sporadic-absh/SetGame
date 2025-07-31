module com.example.setgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens setgame to javafx.fxml;
    exports setgame;
}