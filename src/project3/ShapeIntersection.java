package project3;

/**
 * Determine the shape created by rectangle A intersection with shape B
 */
public class ShapeIntersection implements Shape {
    private final Shape shapeA;
    private final Shape shapeB;
    private final char Operator;
    private static final char INTERSECTION = '&';

    public ShapeIntersection(Shape shapeA, Shape shapeB, char Operator) {
        this.shapeA = shapeA;
        this.shapeB = shapeB;
        this.Operator = Operator;
    }
    @Override
    /**
     * Construct a shape intersection from two rectangle A and B.
     * This shape will be A & B
     * @param shapeA Rectangle A
     * @param shapeB Rectangle B
     * @param Operator Operator between two shape
     */
    public boolean contains(int x, int y) {
        if (Operator == INTERSECTION) {
            return shapeA.contains(x, y) && shapeB.contains(x, y);
        }
        return false;
    }

    @Override
    /**
     * Determine whether or not the given point is contained within this shape.
     * @param x Coordinate X of the given point
     * @param y Coordinate Y of the given point
     * @return True if the given point is contained within this shape, false if not
     */
    public Shape boundingBox() {
        Rectangle boundingBoxA = (Rectangle) shapeA.boundingBox();
        Rectangle boundingBoxB = (Rectangle) shapeB.boundingBox();
        int leftMostA = boundingBoxA.getX();
        int rightMostA = leftMostA + boundingBoxA.getWidth();
        int upMostA = boundingBoxA.getY();
        int downMostA = upMostA + boundingBoxA.getHeight();

        // four boundaries of shapeB
        int leftMostB = boundingBoxB.getX();
        int rightMostB = leftMostB + boundingBoxB.getWidth();
        int upMostB = boundingBoxB.getY();
        int downMostB = upMostB + boundingBoxB.getHeight();

        if (rightMostA < leftMostB || leftMostA > rightMostB || downMostA < upMostB || upMostA > downMostB) {
            System.out.println("\n\n2 shape [" + leftMostA + "," + upMostA + "," + boundingBoxA.getWidth() + "," + boundingBoxA.getHeight() + "] and [" +
                    leftMostB + "," + upMostB + "," + boundingBoxB.getWidth() + "," + boundingBoxB.getHeight() + "] have no intersection\n\n");
            return null;
        }
        // left-most, right-most, up-most, and down-most boundary of intersection
        int leftIntersection = Math.max(leftMostA, leftMostB);
        int rightIntersection = Math.min(rightMostA, rightMostB);
        int upIntersection = Math.max(upMostA, upMostB);
        int downIntersection = Math.min(downMostA, downMostB);
        return new Rectangle(leftIntersection, upIntersection,
                rightIntersection - leftIntersection, downIntersection - upIntersection);
    }
}