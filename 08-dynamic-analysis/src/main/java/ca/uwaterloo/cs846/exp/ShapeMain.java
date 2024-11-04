package ca.uwaterloo.cs846.exp;

public class ShapeMain {

    public static void drawShape(Shape shape) {
        shape.draw();
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Line(0, 0, 10, 10),
                new Rectangle(0, 0, 10, 10),
                // Circle is not used here
        };
        for (Shape shape : shapes) {
            drawShape(shape);
        }
    }
}
