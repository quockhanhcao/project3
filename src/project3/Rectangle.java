package project3;

public class Rectangle implements Shape {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

//    Setter
//    Also check coordinate x, y

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

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    @Override
    public boolean contains(int x, int y) {
        return (x >= this.x) && (x < this.x + width) && (y >= this.y) && (y < this.y + height);
    }

    @Override
    public Shape boundingBox() {
        return this;
    }

}