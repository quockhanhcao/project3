package project3;

import java.util.*;

/**
 * Responsible for interpreting a shape program. The program is represented as a
 * string, through which the interpreter moves. For example, consider this shape
 * program:
 *
 * <pre>
 * x =[0,0,10,10]
 * fill x #000000
 * </pre>
 *
 * This program will be represented in the input string as follows:
 *
 * <pre>
 * --------------------------------------------------------------
 * | x |   | = | [ | 0 | , | 0 | , | 1 | 0 | , | 1 | 0 | ] | \n |
 * --------------------------------------------------------------
 *   0   1   2   3   4   5   6   7   8   9   10  11  12  13  14
 *
 * (continued)
 * --------------------------------------------------------------
 * | f | i | l | l |   | x |   | # | 0 | 0 | 0 | 0 | 0 | 0 | \n |
 * --------------------------------------------------------------
 *   14  15  16  17  18  19  20  21  22  23  24  25  26  27  38
 * </pre>
 *
 * The interpreter starts at index 0 and attempts to decide what kind of command
 * we have. If the first four characters are "fill" then it's a fill command. If
 * the first four characters are "draw" then it's a draw command. Otherwise, it
 * must be an assignment command.
 *
 *
 *
 */
public class Interpreter {
    private String input;
    private int index;
    private HashMap<String, Shape> environment = new HashMap<>();

    public Interpreter(String input) {
        this.input = input;
        this.index = 0;
    }

    /*
    This method create a canvas for the program
    */

    public Canvas createCanvas() {
        Canvas newCanvas = new Canvas();
        while (index < input.length()) {
            checkCommand(newCanvas);
        }
        return newCanvas;
    }

    private void skipWhiteSpace() {
        while (index < input.length() && (input.charAt(index) == ' ' || input.charAt(index) == '\n')) {
            index = index + 1;
        }
    }
    /*
     Read word(separate by space character) from input
     The word can be variable or command draw and fill
     If the word is not command, it'll be variable name
     Variable name must start with character
    */
    private String readWord() {
        int start = index;
        if (!Character.isLetter(input.charAt(start))) {
            error("Variable name or command must start with letter");
        }

        while (index < input.length() && (Character.isLetter(input.charAt(index)) || Character.isDigit(input.charAt(index)))) {
            index++;
        }

        return input.substring(start, index);
    }
    /*

     */
    private void match(String text) {
        skipWhiteSpace();
        if (input.startsWith(text, index)) {
            index ++;
        } else {
            error("Expecting: " + text);
        }
    }
    /*
    This method check the word read by method readWord
    If the word is not command, it's a variable, method will look for "=" and check the bracket
        and add keys and value (command, shape) to environment
    */
    private void checkCommand(Canvas canvas) {
        skipWhiteSpace();
        String command = readWord();
        if (command.equals("fill")) {
            Shape shape = checkBracketExpression();
            Color color = readColor();
            fillShape(color, shape, canvas);
        } else if (command.equals("draw")) {
            Shape shape = checkBracketExpression();
            Color color = readColor();
            drawShape(color, shape, canvas);
        } else if (!command.equals("")) {
            match("=");
            Shape shape = checkBracketExpression();
            environment.put(command, shape);
        }
        skipWhiteSpace();
    }

    /**
     * Evaluate a shape expression which is expected at the current position
     * within the input string. This is done by first looking at the current
     * character in the input string. If this is a '(', for example, then it
     * signals the start of a bracketed expression.
     *
     * @return ---- a shape constructed from the evaluated result
     */
    private Shape checkBracketExpression() {
        skipWhiteSpace();
        char character = input.charAt(index);
        Shape value = null;

        if (character == '(') {
            value = checkParenthesis();
        } else if (character == '[') {
            value = readRectangle();
        } else if (Character.isLetter(character)) {
            // in this case, we have an identifier
            value = readVariable();
        } else {
            error("Unknown operator");
        }

        skipWhiteSpace();

        // to prevent index out of boundary
        if (index < input.length()) {
            character = input.charAt(index);

            if (character == '+' || character == '-' || character == '&') {
                // in this case we have a shape operation
                char so = readOperator(character);
                Shape valueCopy = value; // just a copy of value
                Shape another = checkBracketExpression(); // another shape after
                // the shape operator
                value = new ShapeCombination(valueCopy, another, so);
            }
        }

        return value;
    }

    /**
     * Evaluate a bracketed shape expression. That is a shape expression which
     * is surrounded by braces.
     *
     * @return ---- a shape constructed from the evaluated result
     */
    private Shape checkParenthesis() {
        /*
         * This method is essentially another evaluateShapeExpression() method.
         * It only matches the bracket before and after.
         */
        match("(");
        Shape value = checkBracketExpression();
        match(")");
        return value;
    }

    /**
     * Evaluate a rectangle expression. Four numbers separated by
     * comma's and '[', ']'.
     *
     * @return ---- a shape constructed from the evaluated result
     */
    private Shape readRectangle() {
        match("[");
        int x = readNumber();
        match(",");
        int y = readNumber();
        match(",");
        int width = readNumber();
        match(",");
        int height = readNumber();
        match("]");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return rectangle;
    }
    //    Read a number which is expected at the current input position. A number
//    is defined as a sequence of one or more digits.
//
//    @return ---- the int number parsed from current input position
    private int readNumber() {
        skipWhiteSpace();
        int start = index;
        while (index < input.length() && Character.isDigit(input.charAt(index))) {
            index = index + 1;
        }
        return Integer.parseInt(input.substring(start, index));
    }

    /**
     * Evaluate a variable expression which is expected at the current input
     * position. A variable is a sequence of one or more digits or letters, of
     * which the first character must be a letter. Having determined the
     * variable name, its current value is then looked up in the environment.
     *
     * @return ---- a shape constructed from the evaluated result
     */
    private Shape readVariable() {
        int start = index;
        String variable = readWord();
        Shape s = environment.get(variable);
        if (s == null) {
            index = start;
            error("Undefined variable");
        }
        return s;
    }








    /**
     * Read a shape operator which is expected at the current input position. A
     * shape operator can only be '+' for shape union, '-' for shape difference,
     * or '&' for shape intersection. This method returns the passed argument
     * char unchanged, so essentially it only advances the index by one.
     *
     * @param chr
     *            --- the character that need to be parsed
     * @return --- the passed argument
     */
    private char readOperator(char chr) {
        /*
         * In this assignment, this error would never happen. This "if" is only
         * for a robust functional method
         */
//        if (chr != '+' && chr != '-' && chr != '&') {
//            error("Invalid operator\nAvailable operator\nUnion: +\t Difference -\t Intersection & ");
//        }

        index++;
        return chr;
    }

    /**
     * Read a color which is expected at the current input position. A color is
     * a string of 7 characters, of which the first is a '#' and the remainder
     * are digits or letters.
     *
     * @return ---- a color object parsed from current input position
     */
    private Color readColor() {
        skipWhiteSpace();
        if ((index + 7) > input.length()) {
            error("Expecting color");
        }
        String str = input.substring(index, index + 7);
        index += 7;
        return new Color(str);
    }





    /**
     * This method fills a given shape in a given colour onto the canvas.
     *
     * @param color
     *            ---- the colour of the filled shape
     * @param shape
     *            ---- the shape to be filled
     * @param canvas
     *            ---- where to paint
     */
    private void fillShape(Color color, Shape shape, Canvas canvas) {
        Rectangle boundingBox = (Rectangle) shape.boundingBox();
        /*
         * To prevent any non-positive width or non-positive height bounding box
         * which could be generated by an intersection operation.
         */
        if (boundingBox == null) {
            return;
        }

        // get four boundaries
        int left = boundingBox.getX();
        int right = left + boundingBox.getWidth();
        int up = boundingBox.getY();
        int down = up + boundingBox.getHeight();

        // fill the shape
        for (int x = left; x < right; x++) {
            for (int y = up; y < down; y++) {
                if (shape.contains(x, y)) {
                    canvas.draw(x, y, color);
                }
            }
        }
    }

    /**
     * This method draws a given shape in a given colour onto the canvas.
     *
     * @param color
     *            ---- the colour of the drawn shape
     * @param shape
     *            ---- the shape to be drawn
     * @param canvas
     *            ---- where to paint
     */
    private void drawShape(Color color, Shape shape, Canvas canvas) {
        Rectangle boundingBox = (Rectangle) shape.boundingBox();
        /*
         * To prevent any non-positive width or non-positive height bounding box
         * which could be generated by an intersection operation.
         */
        if (boundingBox == null) {
            return;
        }

        // four boundaries
        int left = boundingBox.getX();
        int right = left + boundingBox.getWidth();
        int up = boundingBox.getY();
        int down = up + boundingBox.getHeight();

        /*
         * two flags indicates: was last pixel inside the shape, and is current
         * pixel inside the shape
         */
        boolean wasInside;
        boolean isInside;

        // scan horizontally
        for (int y = up; y < down; y++) {

            // need to deal with the left-most pixel
            int x = left;
            if (shape.contains(x, y) && shape.contains(x + 1, y)) {
                canvas.draw(x, y, color);
            }

            // deal with pixels in the middle
            for (x = left + 1; x < right; x++) {
                wasInside = shape.contains(x - 1, y);
                isInside = shape.contains(x, y);
                if (!wasInside && isInside) {
                    // enter the shape
                    canvas.draw(x, y, color);
                } else if (wasInside && !isInside) {
                    // exit the shape
                    canvas.draw(x - 1, y, color);
                }
            }

            // need to deal with the right-most pixel, here x = right
            x--;
            if (shape.contains(x - 1, y) && shape.contains(x, y)) {
                canvas.draw(x, y, color);
            }
        }

        // scan vertically
        for (int x = left; x < right; x++) {

            // need to deal with the up-most pixel
            int y = up;
            if (shape.contains(x, y) && shape.contains(x, y + 1)) {
                canvas.draw(x, y, color);
            }

            // deal with pixels in the middle
            for (y = up + 1; y < down; y++) {
                wasInside = shape.contains(x, y - 1);
                isInside = shape.contains(x, y);
                if (!wasInside && isInside) {
                    // enter the shape
                    canvas.draw(x, y, color);
                } else if (wasInside && !isInside) {
                    // exit the shape
                    canvas.draw(x, y - 1, color);
                }
            }

            // need to deal with the down-most pixel, here y = down
            y--;
            if (shape.contains(x, y - 1) && shape.contains(x, y)) {
                canvas.draw(x, y, color);
            }
        }
    }

    /**
     print out error message
     */
    private void error(String error) {
        String msg = error + "\n" + input + "\n";
        throw new IllegalArgumentException(msg);
    }
}
