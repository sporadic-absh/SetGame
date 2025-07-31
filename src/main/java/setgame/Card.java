package setgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * @param number  0 = one, 1 = two, 2 = three
 * @param color   0 = green, 1 = red, 2 = blue
 * @param shape   0 = circle, 1 = triangle, 2 = square
 * @param shading 0 = outlined, 1 = grey, 2 = solid
 */

public record Card(int number, int color, int shape, int shading) {

    public Shape createShape(int shape, int color, int shading, double scaling) {
        Shape shapeDisplayed = switch (shape) {
            case 0 -> new Circle(scaling * 25);
            case 1 -> new Polygon(0, 0, scaling * 25, scaling * -43.3, scaling * 50, 0);
            case 2 -> new Polygon(0, 0, scaling * 50, 0, scaling * 50, scaling * 50, 0, scaling * 50);
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
        switch (color) {
            case 0:
                shapeDisplayed.setStroke(Color.web("#64b258"));
                break;
            case 1:
                shapeDisplayed.setStroke(Color.web("#eb2028"));
                break;
            case 2:
                shapeDisplayed.setStroke(Color.web("#585cb2"));
        }
        switch (shading) {
            case 0:
                shapeDisplayed.setFill(Color.WHITE);
                shapeDisplayed.setStrokeWidth(scaling * 3);
                break;
            case 1:
                shapeDisplayed.setFill(Color.GREY);
                shapeDisplayed.setStrokeWidth(scaling * 7);
                shapeDisplayed.setStrokeType(StrokeType.INSIDE);
                break;
            case 2:
                shapeDisplayed.setFill(shapeDisplayed.getStroke());
        }
        return shapeDisplayed;
    }
}