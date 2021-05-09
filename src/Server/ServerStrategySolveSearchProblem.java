package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * solving maze server strategy
 */
//TODO: re-document! maybe i should use the hase code
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static int number = 0;

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir"); //TODO: Remove ATP-Project
        Solution solution = null;

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze mazeFromClient = (Maze) fromClient.readObject();

            solution = searchForOlderSolution(tempDirectoryPath, mazeFromClient);

            if (solution == null)
                solution = solveAndSave(tempDirectoryPath, mazeFromClient);

            toClient.writeObject(solution);
            toClient.flush();
            toClient.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        //TODO: DO WE NEED TO REMOVE ALL THE FILES WE CREATED????
    }

    /**
     * search for older solutions of the given maze.
     *
     * @param DirectoryPath  the directory holds the old solutions
     * @param mazeFromClient maze to solve
     * @return Solution if found. otherwise - null
     */
    private Solution searchForOlderSolution(String DirectoryPath, Maze mazeFromClient) {
        Solution solution = null;
        byte[] mazeFromClientAsArray = mazeFromClient.toByteArray();
        try {
            Maze loadedMaze;
            ObjectInputStream inMazeFile;
            for (int i = 0; i < number; i++) {
                inMazeFile = new ObjectInputStream(new FileInputStream(DirectoryPath + "maze" + i + ".maze"));
                loadedMaze = (Maze) inMazeFile.readObject();
                //TODO: maybe i should compare maze size w/h first and then the all maze!
                //TODO: maybe compare using hash code??
                if (Arrays.equals(loadedMaze.toByteArray(), mazeFromClientAsArray)) {
                    ObjectInputStream inSolutionFile = new ObjectInputStream(new FileInputStream(DirectoryPath + "solvedMaze" + i + ".solution"));
                    System.out.println("SAME?!?!!?!?"); //TODO: remove
                    return (Solution) inSolutionFile.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * solve given maze and save the solution to the directory
     *
     * @param DirectoryPath  the directory holds the old solutions
     * @param mazeFromClient maze to solve
     * @return the given maze solution
     */
    private Solution solveAndSave(String DirectoryPath, Maze mazeFromClient) {
        synchronized (this) {
            Solution solution = null;
            try {
                //create ISearchingAlgorithm class using the name that in configuration file to solve the maze with.
                Class<?> mazeSearchClass = Class.forName("algorithms.search." + Configurations.getInstance().getMazeSearchingAlgorithm());
                ISearchingAlgorithm mazeSearchAlgorithm = (ISearchingAlgorithm) mazeSearchClass.getDeclaredConstructor().newInstance();
                solution = mazeSearchAlgorithm.solve(new SearchableMaze(mazeFromClient));
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
            try {
                // save maze to a file
                ObjectOutputStream outToMazeFile = new ObjectOutputStream(new FileOutputStream(DirectoryPath + "maze" + number + ".maze"));
                outToMazeFile.writeObject(mazeFromClient);
                outToMazeFile.flush();
                outToMazeFile.close();
                // save Solution to a file
                ObjectOutputStream outToSolutionFile = new ObjectOutputStream(new FileOutputStream(DirectoryPath + "solvedMaze" + number++ + ".solution"));
                outToSolutionFile.writeObject(solution);
                outToSolutionFile.flush();
                outToSolutionFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return solution;
        }
    }
}
