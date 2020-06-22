package project3;

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
    public boolean contains(int x, int y) {
        if (Operator == INTERSECTION) {
            return shapeA.contains(x, y) && shapeB.contains(x, y);
        }
        return false;
    }

    @Override
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

        // left-most, right-most, up-most, and down-most boundary of both
        int leftMost = Math.min(leftMostA, leftMostB);
        int rightMost = Math.max(rightMostA, rightMostB);
        int upMost = Math.min(upMostA, upMostB);
        int downMost = Math.max(downMostA, downMostB);
        if (rightMostA < leftMostB || leftMostA > rightMostB || downMostA < upMostB || upMostA > downMostB) {
                return null;
            }
        int leftIntersection = Math.max(leftMostA, leftMostB);
            int rightIntersection = Math.min(rightMostA, rightMostB);
            int upIntersection = Math.max(upMostA, upMostB);
            int downIntersection = Math.min(downMostA, downMostB);
            return new Rectangle(leftIntersection, upIntersection,
                    rightIntersection - leftIntersection, downIntersection - upIntersection);
    }
}
