package project3;

public class ShapeDifference implements Shape {
        private final Shape shapeA;
        private final Shape shapeB;
        private final char Operator;
        private static final char DIFFERENCE = '-';

public ShapeDifference(Shape shapeA, Shape shapeB, char Operator) {
        this.shapeA = shapeA;
        this.shapeB = shapeB;
        this.Operator = Operator;
        }
    @Override
    public boolean contains(int x, int y) {
      if (Operator == DIFFERENCE) {
        return shapeA.contains(x, y) && !shapeB.contains(x, y);
        }
        return false;
    }

    @Override
    public Shape boundingBox() {
        Rectangle boundingBoxA = (Rectangle) shapeA.boundingBox();
        Rectangle boundingBoxB = (Rectangle) shapeB.boundingBox();
        return boundingBoxA;
    }
}
