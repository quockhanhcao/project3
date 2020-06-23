package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used to create and show a white background window where
 * the shapes input by user are draw onto
 */
public class Canvas {
    private int width;
    private int height;
    private Color[][] grid;

    /**
     * Construct an empty Canvas.
     */
    public Canvas() {
            grid = null;
            width = 0;
            height = 0;
    }

    /**
     * Construct a canvas from an existed canvas.
     * @param c The existed canvas
     */
    public Canvas(Canvas c) {
        width = c.width;
        height = c.height;
        grid = new Color[width][height];
        for (int x = 0; x != width; ++x) {
            for (int y = 0; y != height; ++y) {
                grid[x][y] = c.grid[x][y];
            }
        }
    }

    /**
     * Retrieve the width of the canvas.
     * @return A integer data type
     */
    public int width() {
        return width;
    }

    /**
     * Retrieve the height of the canvas
     * @return A integer data type
     */
    public int height() {
        return height;
    }

    /**
     * Return the color at the given (x, y) coordinate. Check if that coordinate is in the canvas or not
     * @param x Coordinate X of the pixel
     * @param y Coordinate Y of the pixel
     * @return Color at grid (x, y)
     */
    public Color colorAt(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException("x position cannot be negative!");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y position cannot be negative!");
        }
        if (x >= width) {
            throw new IllegalArgumentException("x position exceeds canvas width!");
        }
        if (y >= height) {
            throw new IllegalArgumentException("y position exceeds canvas height!");
        }
        return grid[x][y];
    }

    /**
     * Draw a grid at the given coordinate (x, y) with specific color in the canvas.
     * @param x Coordinate Y of the grid
     * @param y Coordinate X of the grid
     * @param color Color will be drawn on the grid
     */
    public void draw(int x, int y, Color color) {
        if (x < 0) {
            throw new IllegalArgumentException("x position cannot be negative!");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y position cannot be negative!");
        }

        int nwidth = Math.max(width, x + 1);
        int nheight = Math.max(height, y + 1);

        if (nwidth != width || nheight != height) {
            // In this case, the canvas is not big enough.
            // Therefore, we must automatically resize it.
            Color[][] ngrid = new Color[nwidth][nheight];
            for (int i = 0; i != nwidth; ++i) {
                for (int j = 0; j != nheight; ++j) {
                    if (i < width && j < height) {
                        // copy old color
                        ngrid[i][j] = grid[i][j];
                    } else {
                        // put in default color in.
                        ngrid[i][j] = Color.WHITE;
                    }
                }
            }

            grid = ngrid;
            width = nwidth;
            height = nheight;
        }
        grid[x][y] = color;
    }

    /**
     *Show the contents of the canvas using a GUI Window.
     */
    @SuppressWarnings("serial")
    public void show() {
        String env = System.getenv("AUTOMARK");

        if (env != null) {
            return;
        }

        final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x != width; ++x) {
            for (int y = 0; y != height; ++y) {
                img.setRGB(x, y, grid[x][y].toRGB());
            }
        }

        JFrame window = new JFrame("Project 3, Canvas Viewer");

        java.awt.Canvas canvas = new java.awt.Canvas() {

            public void paint(Graphics g) {
                g.drawImage(img, 10, 10, null);
            }
        };

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(canvas);
        window.pack();
        window.setVisible(true);
        window.setBounds(0, 0, width + 40, height + 60);
    }

    /**
     * Convert the contents of the canvas into a string format, where each point is given using as a 6 digit hexadecimal string.
     * @return A string format of the canvas
     */
    public String toString() {
        StringBuilder r = new StringBuilder();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                r.append(colorAt(x, y));
            }
            r.append('\n');
        }
        return r.toString();
    }
}

