package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static int number = 0;

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        boolean haveOlderSolution = false;
        Maze mazeFromClient;
        Solution solution = null;
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            mazeFromClient = (Maze) fromClient.readObject();
            Maze loadedMaze;
            ObjectInputStream inMazeFile;
            for (int i = 0; i < number; i++) {
                inMazeFile = new ObjectInputStream( new FileInputStream(tempDirectoryPath + "\\maze" + i + ".maze"));
                loadedMaze = (Maze) inMazeFile.readObject();
                if (Arrays.equals(loadedMaze.toByteArray(), mazeFromClient.toByteArray())) {
                    haveOlderSolution = true;
                    ObjectInputStream inSolutionFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "\\solvedMaze" + i + ".solution"));
                    solution = (Solution) inSolutionFile.readObject();
                    break;
                }

            }

            if (!haveOlderSolution) {
                solution = new BestFirstSearch().solve(new SearchableMaze(mazeFromClient)); //TODO use configuration search
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
            }
            toClient.writeObject(solution);
            toClient.flush();
            inFromClient.close();
            toClient.close();
            outToClient.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
