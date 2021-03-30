package algorithms.mazeGenerators;

/**
 * this class represents a position in 2D area.
 * we think about our area as a table with x as column number and y as a row.
 * the values of the row and column are Integer number.
 */
public class Position {
    private int column, row;

    /**
     * constructor
     *
     * @param column column.
     * @param row    row.
     */
    public Position(int row, int column) {
//        if (column < 0 || row < 0)
//            throw new IllegalArgumentException("one or more of the arguments are not Natural number");
        this.column = column;
        this.row = row;
    }

    /***
     * constructor
     * @throws IllegalArgumentException argument String don't match the format required: {row,column}
     * @param str format: {row,column}
     */
    public Position(String str) {
        if (str == null || str.length() < 5 || str.charAt(0) != '{' || str.charAt(str.length() - 1) != '}' || !str.contains(","))
            throw new IllegalArgumentException("argument String don't match the format required: {row,column}");
        String[] parts = str.split(",");
        this.row = Integer.parseInt(parts[0].substring(1));
        this.column = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
    }

    /**
     * copy constructor
     * if other is null create with default values
     *
     * @param other Position to copy
     */
    public Position(Position other) {
        if (other != null) {
            this.column = other.column;
            this.row = other.row;
        }
    }

    /**
     * @return column index of the Position
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * set new value to the Position row.
     *
     * @param row a Natural number
     */
    public void setRow(int row) {
        if (row < 0)
            throw new IllegalArgumentException("the argument are not Natural number");
        this.column = row;
    }

    /**
     * @return row index of the Position
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * set new value to the Position column.
     *
     * @param column a Natural number
     */
    public void setColumn(int column) {
        if (column < 0)
            throw new IllegalArgumentException("the argument are not Natural number");
        this.row = column;
    }

    /**
     * @return Position with row-1
     */
    public Position getUpPosition() {
        return new Position(this.row - 1, this.column);
    }

    /**
     * @return Position with row-1 and column+1
     */
    public Position getUpRightPosition() {
        return new Position(this.row - 1, this.column + 1);
    }

    /**
     * @return Position with column+1
     */
    public Position getRightPosition() {
        return new Position(this.row, this.column + 1);
    }

    /**
     * @return Position with row+1 and column+1
     */
    public Position getDownRightPosition() {
        return new Position(this.row + 1, this.column + 1);
    }

    /**
     * @return Position with row+1
     */
    public Position getDownPosition() {
        return new Position(this.row + 1, this.column);
    }

    /**
     * @return Position with row+1 and column-1
     */
    public Position getDownLeftPosition() {
        return new Position(this.row + 1, this.column - 1);
    }

    /**
     * @return Position with column-1
     */
    public Position getLeftPosition() {
        return new Position(this.row, this.column - 1);
    }

    /**
     * @return Position with row-1 and column-1
     */
    public Position getUpLeftPosition() {
        return new Position(this.row - 1, this.column - 1);
    }

    @Override
    public String toString() {
        return "{" + this.row + "," + this.column + "}";
    }

    /**
     * two Points equals when its row and column index equals
     * @param o
     * @return true - row and column index equals. otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;
        return row == position.row;
    }
}
