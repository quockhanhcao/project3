package project3;

/**
 * Create arbitrary shape
 */
public interface Shape {

        /**
         * Determine whether or not the given point is contained within this shape.
         * @param x Coordinate X of the given point
         * @param y Coordinate Y of the given point
         * @return True if the given point is contained within this rectangle, false if not
         */
        public boolean contains(int x, int y);

        /**
         * Determine a <i>bounding box</i> of the current shape. A bounding box is a
         *      * box that will fit around the entire shape and, hence, can be used to
         *      * determine the maximum width and height of the shape. This is useful when
         *      * it comes to drawing the shape!
         *      *
         * @return A shape data type
         */
        public Shape boundingBox();

}

