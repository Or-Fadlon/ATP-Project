package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static int number = 0;

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        Solution solution = null;

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze mazeFromClient = (Maze) fromClient.readObject();

            solution = searchForOlderSolution(tempDirectoryPath, mazeFromClient);

            if (solution == null) {
                solution = solveAndSave(tempDirectoryPath, mazeFromClient);
            }
            toClient.writeObject(solution);
            toClient.flush();
            toClient.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private Solution searchForOlderSolution(String tempDirectoryPath, Maze mazeFromClient) {
        Solution solution;
        try {
            Maze loadedMaze;
            ObjectInputStream inMazeFile;
            for (int i = 0; i < number; i++) {
                inMazeFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "\\maze" + i + ".maze"));
                loadedMaze = (Maze) inMazeFile.readObject();
                //TODO: maybe i should compare maze size w/h first and then the all maze!
                //TODO: maybe compare using hash code??
                if (Arrays.equals(loadedMaze.toByteArray(), mazeFromClient.toByteArray())) {
                    ObjectInputStream inSolutionFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "\\solvedMaze" + i + ".solution"));
                    return (Solution) inSolutionFile.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Solution solveAndSave(String tempDirectoryPath, Maze mazeFromClient){
        synchronized (this) {
            Solution solution;
            try {
                Class<?> mazeSearchClass = Class.forName("algorithms.search." + Configurations.getInstance().getMazeSearchingAlgorithm());
                ISearchingAlgorithm mazeSearchAlgorithm = (ISearchingAlgorithm) mazeSearchClass.getDeclaredConstructor().newInstance();
                solution = mazeSearchAlgorithm.solve(new SearchableMaze(mazeFromClient));
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
            try {
                // save maze to a file
                ObjectOutputStream outToMazeFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "\\maze" + number + ".maze"));
                outToMazeFile.writeObject(mazeFromClient);
                outToMazeFile.flush();
                outToMazeFile.close();
                // save Solution to a file
                ObjectOutputStream outToSolutionFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "\\solvedMaze" + number++ + ".solution"));
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
