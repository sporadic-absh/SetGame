package setgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * This class handles the cards of SET.
 * Its purpose is to create the symbol on the cards in the UI.
 */

public record Card(SymbolNumber symbolNumber, SymbolShape symbolShape, SymbolColor symbolColor, SymbolShading symbolShading) {

    public Shape createShape(SymbolShape symbolShape, SymbolColor symbolColor, SymbolShading symbolShading, double scaling) {
        Shape shapeDisplayed = switch (symbolShape) {
            case SymbolShape.CIRCLE -> new Circle(scaling * 25);
            case SymbolShape.TRIANGLE -> new Polygon(0, 0, scaling * 25, scaling * -43.3, scaling * 50, 0);
            case SymbolShape.SQUARE -> new Polygon(0, 0, scaling * 50, 0, scaling * 50, scaling * 50, 0, scaling * 50);
        };
        switch (symbolColor) {
            case SymbolColor.GREEN:
                shapeDisplayed.setStroke(Color.web("#64b258"));
                break;
            case SymbolColor.RED:
                shapeDisplayed.setStroke(Color.web("#eb2028"));
                break;
            case SymbolColor.BLUE:
                shapeDisplayed.setStroke(Color.web("#585cb2"));
        }
        switch (symbolShading) {
            case SymbolShading.OUTLINED:
                shapeDisplayed.setFill(Color.WHITE);
                shapeDisplayed.setStrokeWidth(scaling * 3);
                break;
            case SymbolShading.GREY:
                shapeDisplayed.setFill(Color.GREY);
                shapeDisplayed.setStrokeWidth(scaling * 7);
                shapeDisplayed.setStrokeType(StrokeType.INSIDE);
                break;
            case SymbolShading.SOLID:
                shapeDisplayed.setFill(shapeDisplayed.getStroke());
        }
        return shapeDisplayed;
    }
}