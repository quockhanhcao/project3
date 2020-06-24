package project3;

import java.util.*;

/**
 * This class create a canvas and handle the input string into each command to draw shapes.
 */
public class Interpreter {
    private String input;
    private int index;
    private HashMap<String, Shape> environment = new HashMap<>();

    /**
     * Construct a new interpreter from input string
     * @param input The string of command
     */
    public Interpreter(String input) {
        this.input = input;
        this.index = 0;
    }

    /**
     * Create a new canvas
     * @return A new canvas
     */
    public Canvas createCanvas() {
        Canvas newCanvas = new Canvas();
        while (index < input.length()) {
            checkCommand(newCanvas);
        }
        return newCanvas;
    }

    /**
     * This method uses method readword() to check the word is a command or variable.
     * If the word is a command, it will execute the command on the input canvas.
     * If the word is a variable, it will look for "=" sign and add the values in the bracket to the environment ass a new shape
     * @param canvas A canvas used to show the contents
     */
    private void checkCommand(Canvas canvas) {
        skipWhiteSpace();
        String command = readWord();
        if (command.equals("fill")) {
            Shape shape = checkBracket();
            Color color = readColor();
            fillShape(color, shape, canvas);
        }
        else if (command.equals("draw")) {
            Shape shape = checkBracket();
            Color color = readColor();
            drawShape(color, shape, canvas);
        }
        else if (!command.equals("")) {
            match("=");
            Shape shape = checkBracket();
            environment.put(command, shape);
        }
        skipWhiteSpace();
    }

    /**
     * Method to skip over any space or end line at the current position of the input
     */
    private void skipWhiteSpace() {
        while (index < input.length() && (input.charAt(index) == ' ' || input.charAt(index) == '\n')) {
            index = index + 1;
        }
    }

    /**
     * Read a word from input. It can be a variable name or command If the word is a variable name, it must starts with a letter
     * @return A substring which contains a command or a variable name
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

    /**
     * Match a string of text which is expected at the current input position.
     * If the match fails, then an error is produced.
     * @param text The string that need to be matched
     */
    private void match(String text) {
        skipWhiteSpace();
        if (input.startsWith(text, index)) {
            index ++;
        } else {
            error("Expecting: " + text);
        }
    }

    /**
     * Evaluate a shape expression at the current position of the input string.
     * This is done by first looking at the current character in the input string.
     * If the first character of the input string is a '(', then it signals the start of a bracketed expression.
     * If the first character of the input string is a '[', then we read the rectangle.
     * @return A shape constructed from the evaluated result
     */
    private Shape checkBracket() {
        skipWhiteSpace();
        char character = input.charAt(index);
        Shape value = null;

        if (character == '(') {
            value = checkParenthesis();
        }
        else if (character == '[') {
            value = readShape();
        }
        else if (Character.isLetter(character)) {
            value = readVariable();
        }
        else {
            error("Unknown operator");
        }

        skipWhiteSpace();

        if (index < input.length()) {
            character = input.charAt(index);
//

            if (character == '+') {
                char so = character;//Shape Operator
                index++;
                Shape valueCopy = value;
                Shape another = checkBracket();
                value = new ShapeUnion(valueCopy, another, so);
            }
            else if (character == '-') {
                char so = character;
                index++;
                Shape valueCopy = value;
                Shape another = checkBracket();
                value = new ShapeDifference(valueCopy, another, so);
            }
            else if (character == '&') {
                char so = character;
                index++;
                Shape valueCopy = value;
                Shape another = checkBracket();
                value = new ShapeIntersection(valueCopy, another, so);
            }
        }
        return value;
    }

    /**
     * Evaluate a bracketed shape expression.
     * @return A shape constructed from the evaluated result
     */
    private Shape checkParenthesis() {
        match("(");
        Shape value = checkBracket();
        match(")");
        return value;
    }

    /**
     * Evaluate a rectangle expression, which is four numbers separated by comma's and '[', ']'
     * @return A rectangle constructed from the evaluated result
     */
    private Shape readShape() {
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

    /**
     * Read a number at the current input position
     * @return The integer number parsed from current input position
     */
    private int readNumber() {
        skipWhiteSpace();
        int start = index;
        while (index < input.length() && Character.isDigit(input.charAt(index))) {
            index = index + 1;
        }
        return Integer.parseInt(input.substring(start, index));
    }

    /**
     * Evaluate a variable expression at the current input position.
     * When the variable name is determined, its current value is looked up in the environment.
     * @return A shape constructed from the evaluated result
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
     * Read a color at the current input position. A color is a string of Hex color code
     * @return A color parsed from current input position
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
     * This method paint a given shape in a given colour onto the canvas.
     * @param color The colour of the filled shape
     * @param shape The shape to be filled
     * @param canvas The canvas which is painted onto
     */
    private void fillShape(Color color, Shape shape, Canvas canvas) {
        Rectangle boundingBox = (Rectangle) shape.boundingBox();
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
     * This method draws a border of the given shape in a given colour onto the canvas.
     * @param color The colour of the drawn shape
     * @param shape The shape need to be drawn
     * @param canvas The canvas which is drawn onto
     */
    private void drawShape(Color color, Shape shape, Canvas canvas) {
        Rectangle boundingBox = (Rectangle) shape.boundingBox();

        if (boundingBox == null) {
            return;
        }

        //create four boundaries
        int left = boundingBox.getX();
        int right = left + boundingBox.getWidth();
        int up = boundingBox.getY();
        int down = up + boundingBox.getHeight();

        boolean firstPoint;
        boolean secondPoint;

        for (int y = up; y < down; y++) {
            int x = left;
            if (shape.contains(x, y) && shape.contains(x + 1, y)) {
                canvas.draw(x, y, color);
            }
            for (x = left + 1; x < right; x++) {
                firstPoint = shape.contains(x - 1, y);
                secondPoint = shape.contains(x, y);
                if (!firstPoint && secondPoint) {
                    canvas.draw(x, y, color);
                }
                else if (firstPoint && !secondPoint) {
                    canvas.draw(x - 1, y, color);
                }
            }
            x--;
            if (shape.contains(x - 1, y) && shape.contains(x, y)) {
                canvas.draw(x, y, color);
            }
        }

        for (int x = left; x < right; x++) {
            int y = up;
            if (shape.contains(x, y) && shape.contains(x, y + 1)) {
                canvas.draw(x, y, color);
            }
            for (y = up + 1; y < down; y++) {
                firstPoint = shape.contains(x, y - 1);
                isInside = shape.contains(x, y);
                if (!firstPoint && secondPoint) {
                    canvas.draw(x, y, color);
                } else if (firstPoint && !secondPoint) {
                    canvas.draw(x, y - 1, color);
                }
            }
            y--;
            if (shape.contains(x, y - 1) && shape.contains(x, y)) {
                canvas.draw(x, y, color);
            }
        }
    }

    /**
     Print out the error message
     @param error Error message
     */
    private void error(String error) {
        String msg = error + "\n" + input + "\n";
        System.out.println("\n\n" + msg + "\n\n");
    }
}
