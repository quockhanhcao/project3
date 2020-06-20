package project3;

public class Rectangle implements Shape {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    /**
     * Construct a rectangle with the height and width, which starts at the coordinate (x, y)
     * @param x X coordinate of the starting point of the rectangle
     * @param y Y coordinate of the starting point of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public Rectangle(int x, int y, int width, int height) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate x & y cannot be less than 0.");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("The width cannot be 0 or less than 0.");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("The height cannot be 0 or less than 0.");
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    /**
     * Get the x coordinate of the starting point of the rectangle
     * @return A integer data type
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the starting point of the rectangle
     * @return A integer data type
     */
    public int getY() {
        return y;
    }

    /**
     * Get the width of the rectangle
     * @return A integer data type
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the rectangle
     * @return A integer data type
     */
    public int getHeight() {
        return height;
    }

    @Override
    /**
     * Determine whether or not the given point is contained within this rectangle.
     * @param x Coordinate X of the given point
     * @param y Coordinate Y of the given point
     * @return True if the given point is contained within this rectangle, false if not
     */
    public boolean contains(int x, int y) {
        return (x >= this.x) && (x < this.x + width) && (y >= this.y) && (y < this.y + height);
    }

    @Override
    /**
     * Determine the surrounding of the rectangle
     */
    public Shape boundingBox() {
        return this;
    }

}
