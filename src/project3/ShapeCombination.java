//package project3;
//
//public class ShapeCombination implements Shape {
//
//    private final Shape shapeA;
//    private final Shape shapeB;
//    private final char Operator;
//
//    private static final char UNION = '+';
//    private static final char DIFFERENCE = '-';
//    private static final char INTERSECTION = '&';
//
//    /**
//     * Construct a shape that is the result of operation between two shapes. Which one comes first matters.
//     * @param shapeA The first shape
//     * @param shapeB The second shape
//     * @param Operator The operation between two shapes
//     */
//    public ShapeCombination(Shape shapeA, Shape shapeB, char Operator) {
//        this.shapeA = shapeA;
//        this.shapeB = shapeB;
//
//        if (Operator != UNION
//                && Operator != DIFFERENCE
//                && Operator != INTERSECTION) {
//            throw new IllegalArgumentException("Invalid operator");
//        }
//
//        this.Operator = Operator;
//    }
//
//    @Override
//    /**
//     * Determine whether or not the given point is contained within this shape.
//     * @param x Coordinate X of the given point
//     * @param y Coordinate Y of the given point
//     * @return True if the given point is contained within this rectangle, false if not
//     */
//    public boolean contains(int x, int y) {
//        if (Operator == UNION) {
//            return shapeA.contains(x, y) || shapeB.contains(x, y);
//        } else if (Operator == DIFFERENCE) {
//            return shapeA.contains(x, y) && !shapeB.contains(x, y);
//        } else {  // shapeOperator == SHAPE_INTERSECTION
//            return shapeA.contains(x, y) && shapeB.contains(x, y);
//        }
//    }
//
//    @Override
//    /**
//     * Determine the surrounding of the current shape
//     */
//    public Shape boundingBox() {
//        // the bounding boxes of two input shapes
//        Rectangle boundingBoxA = (Rectangle) shapeA.boundingBox();
//        Rectangle boundingBoxB = (Rectangle) shapeB.boundingBox();
//
//        // check whether any or both of these two bounding box is null.
//        if (boundingBoxA == null && boundingBoxB != null) {
//            if (Operator == UNION) {
//                return boundingBoxB;
//            } else {
//                return null;
//            }
//        } else if (boundingBoxA != null && boundingBoxB == null) {
//            if (Operator == INTERSECTION) {
//                return null;
//            } else {
//                return boundingBoxA;
//            }
//        } else if (boundingBoxA == null && boundingBoxB == null) {
//            return null;
//        }
//
//        // four boundaries of shapeA
//        int leftMostA = boundingBoxA.getX();
//        int rightMostA = leftMostA + boundingBoxA.getWidth();
//        int upMostA = boundingBoxA.getY();
//        int downMostA = upMostA + boundingBoxA.getHeight();
//
//        // four boundaries of shapeB
//        int leftMostB = boundingBoxB.getX();
//        int rightMostB = leftMostB + boundingBoxB.getWidth();
//        int upMostB = boundingBoxB.getY();
//        int downMostB = upMostB + boundingBoxB.getHeight();
//
//        // left-most, right-most, up-most, and down-most boundary of both
//        int leftMost = Math.min(leftMostA, leftMostB);
//        int rightMost = Math.max(rightMostA, rightMostB);
//        int upMost = Math.min(upMostA, upMostB);
//        int downMost = Math.max(downMostA, downMostB);
//
//        if (Operator == UNION) {
//            return new Rectangle(leftMost, upMost, rightMost - leftMost, downMost - upMost);
//        } else if (Operator == DIFFERENCE) {
//            return boundingBoxA;
//        } else {
//            // if these two shapes have no intersection at all
//            if (rightMostA < leftMostB || leftMostA > rightMostB || downMostA < upMostB || upMostA > downMostB) {
//                return null;
//            }
//
//             //the boundary of intersection part
//            int leftIntersection = Math.max(leftMostA, leftMostB);
//            int rightIntersection = Math.min(rightMostA, rightMostB);
//            int upIntersection = Math.max(upMostA, upMostB);
//            int downIntersection = Math.min(downMostA, downMostB);
//            return new Rectangle(leftIntersection, upIntersection,
//                    rightIntersection - leftIntersection, downIntersection - upIntersection);
//        }
//    }
//}
//
