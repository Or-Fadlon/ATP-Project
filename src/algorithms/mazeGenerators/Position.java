package algorithms.mazeGenerators;

/**
 * this class represents a position in 2D area.
 * we think about our area as a table with x as column number and y as a row.
 * the values of the row and column are Natural number.
 */
public class Position {
    private int x, y;

    /**
     * constructor
     *
     * @param x column - Natural number.
     * @param y row - Natural number.
     */
    public Position(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("one or more of the arguments are not Natural number");
        this.x = x;
        this.y = y;
    }

    /**
     * copy constructor
     *
     * @param other Position to copy
     */
    public Position(Position other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * @return column index of the Position
     */
    public int getColumnIndex() {
        return x;
    }

    /**
     * set new value to the Position row.
     *
     * @param row a Natural number
     */
    public void setRow(int row) {
        if (row < 0)
            throw new IllegalArgumentException("the argument are not Natural number");
        this.x = row;
    }

    /**
     * @return row index of the Position
     */
    public int getRowIndex() {
        return y;
    }

    /**
     * set new value to the Position column.
     *
     * @param column a Natural number
     */
    public void setColumn(int column) {
        if (column < 0)
            throw new IllegalArgumentException("the argument are not Natural number");
        this.y = column;
    }

    @Override
    public String toString() {
        return "{" + this.y + "," + this.x + "}";
    }
}
