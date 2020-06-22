package project3;

/**
 * This class covert the hex string to color
 */
public class Color {
    private int red;
    private int green;
    private int blue;

    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);

    /**
     * Construct a color object from a 6 digit hexadecimal number string of the form "#rrggbb"
     * @param hex Input string in form of "#rrggbb"
     */
    public Color(String hex) {
        if (hex.length() != 7 || hex.charAt(0) != '#') {
            throw new IllegalArgumentException("hex string requires # followed by 6 hex digits");
        } else {
            // Now, check all digits are indeed hex digits.
            for (int i = 1; i != hex.length(); ++i) {
                char digit = hex.charAt(i);
                if (!Character.isDigit(digit) && digit != 'a' && digit != 'b' && digit != 'c' && digit != 'd'
                        && digit != 'e' && digit != 'f') {
                    throw new IllegalArgumentException("hex string requires # followed by 6 hex digits");
                }
            }
        }

        String red = hex.substring(1, 3);
        String green = hex.substring(3, 5);
        String blue = hex.substring(5, 7);

        this.red = Integer.parseInt(red, 16);
        this.blue = Integer.parseInt(blue, 16);
        this.green = Integer.parseInt(green, 16);
    }

    /**
     * Construct a Color object explicitly from the three color components.
     * Color values between 0 and 255.
     * @param red Value of red component
     * @param green Value of green component
     * @param blue Value of blue component
     */
    public Color(int red, int green, int blue) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException("Red component must take value between 0 and 255");
        }
        if (green < 0 || green > 255) {
            throw new IllegalArgumentException("Green component must take value between 0 and 255");
        }
        if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Blue component must take value between 0 and 255");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Return the red component of this color.
     * @return An integer data type
     */
    public int red() {
        return red;
    }

    /**
     * Return the green component of this color.
     * @return An integer data type
     */
    public int green() {
        return green;
    }

    /**
     * Return the blue component of this color.
     * @return An integer data type
     */
    public int blue() {
        return blue;
    }

    /**
     * Convert this color into a six digit hexadecimal string of the form "#rrggbb"
     * @return A Hex RGB color code
     */

    /**
     * Change to RGB color code
     * @return A RGB color code
     */
    public int toRGB() {
        return ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
    }

//    public String toString() {
//        return "#" + hexDigit(red) + hexDigit(green) + hexDigit(blue);
//    }
//
//    private static String hexDigit(int c) {
//        String r = Integer.toHexString(c);
//        if (r.length() < 2) {
//            r = "0" + r;
//        }
//        return r;
//    }
}
