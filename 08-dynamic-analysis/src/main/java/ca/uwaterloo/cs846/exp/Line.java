package ca.uwaterloo.cs846.exp;

public class Line implements Shape {
    private int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a line between (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")");
    }
}
