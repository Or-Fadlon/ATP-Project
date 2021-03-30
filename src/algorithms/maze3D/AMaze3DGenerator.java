package algorithms.maze3D;

/**
 * abstract 3D maze generator
 */
public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long startTime, finishTime;
        startTime = System.currentTimeMillis();
        generate(depth, row, column);
        finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
}
