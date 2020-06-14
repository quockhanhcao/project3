package project3;

public interface Shape {

        /**
         * Determine whether or not the given point is contained within this shape.
         *
         * @param x
         * @param y
         * @return
         */
        public boolean contains(int x, int y);

        /**
         * Determine a <i>bounding box</i> of the current shape. A bounding box is a
         *      * box that will fit around the entire shape and, hence, can be used to
         *      * determine the maximum width and height of the shape. This is useful when
         *      * it comes to drawing the shape!
         *      *
         * @return
         */
        public Shape boundingBox();

}

