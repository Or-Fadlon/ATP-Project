package algorithms.mazeGenerators;

public class Position {
    private int x = 0, y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getColumnIndex() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getRowIndex() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + this.y + "," + this.x + "}";
    }
}
