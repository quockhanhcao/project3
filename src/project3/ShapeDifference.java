package project3;

/**
 * Determine the shape created by rectangle A difference shape B
 */
public class ShapeDifference implements Shape {
    private final Shape shapeA;
    private final Shape shapeB;
    private final char Operator;
    private static final char DIFFERENCE = '-';

    /**
     * Construct a shape difference from two rectangle A and B.
     * This shape will be A - B
     * @param shapeA Rectangle A
     * @param shapeB Rectangle B
     * @param Operator Operator between two shape
     */
    public ShapeDifference(Shape shapeA, Shape shapeB, char Operator) {
        this.shapeA = shapeA;
        this.shapeB = shapeB;
        this.Operator = Operator;
    }

    @Override
    /**
     * Determine whether or not the given point is contained within this shape.
     * @param x Coordinate X of the given point
     * @param y Coordinate Y of the given point
     * @return True if the given point is contained within this shape, false if not
     */
    public boolean contains(int x, int y) {
        if (Operator == DIFFERENCE) {
            return shapeA.contains(x, y) && !shapeB.contains(x, y);
        }
        return false;
    }

    @Override
    /**
     * Determine the surrounding of the shape
     */
    public Shape boundingBox() {
        Rectangle boundingBoxA = (Rectangle) shapeA.boundingBox();
        Rectangle boundingBoxB = (Rectangle) shapeB.boundingBox();
        return boundingBoxA;
    }
}