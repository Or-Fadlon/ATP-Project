package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /***
     * generate a maze
     * @param rows number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return generated maze
     */
    Maze generate(int rows, int columns);

    /***
     * measure the time to generate maze algorithm
     * @param rows number of rows of the maze to generate
     * @param columns number of columns of the maze to generate
     * @return time takes to generate in Milli seconds
     */
    long measureAlgorithmTimeMillis(int rows, int columns);
}
