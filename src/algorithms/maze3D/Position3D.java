package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

/**
 * this class represents a position in 3D area.
 * we think about our area as a 3D table with x as column number, y as row and z as depth.
 * the values of the depth, row, column are Integer number.
 */
public class Position3D {
    private int depth, row, column;

    /**
     * constructor
     *
     * @param depth  depth.
     * @param column column.
     * @param row    row.
     */
    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.column = column;
        this.row = row;
    }

    /**
     * constructor
     *
     * @param str format: "{depth,row,column}"
     */
    public Position3D(String str) {
        String[] parts = str.split(",");
        this.depth = Integer.parseInt(parts[0].substring(1));
        this.row = Integer.parseInt(parts[1]);
        this.column = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
    }

    /**
     * copy constructor
     *
     * @param other Position to copy
     */
    public Position3D(Position3D other) {
        this.depth = other.depth;
        this.column = other.column;
        this.row = other.row;
    }

    public int getDepthIndex() {
        return this.depth;
    }

    public int getRowIndex() {
        return this.row;
    }

    public int getColumnIndex() {
        return this.column;
    }

    public Position3D getUpPosition() {
        return new Position3D(this.depth, this.row - 1, this.column);
    }

    public Position3D getRightPosition() {
        return new Position3D(this.depth, this.row, this.column + 1);
    }

    public Position3D getDownPosition() {
        return new Position3D(this.depth, this.row + 1, this.column);
    }

    public Position3D getLeftPosition() {
        return new Position3D(this.depth, this.row, this.column - 1);
    }

    public Position3D getHigherPosition() {
        return new Position3D(this.depth - 1, this.row, this.column);
    }

    public Position3D getLowerPosition() {
        return new Position3D(this.depth + 1, this.row, this.column);
    }

    @Override
    public String toString() {
        return "{" + depth + "," + row + "," + column + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position3D that = (Position3D) o;

        if (depth != that.depth) return false;
        if (row != that.row) return false;
        return column == that.column;
    }
}
