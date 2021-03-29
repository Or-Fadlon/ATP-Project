package algorithms.maze3D;

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

}
