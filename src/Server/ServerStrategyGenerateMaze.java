package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;

/**
 * generating maze server strategy
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream dimensionInputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeDimensions = (int[]) dimensionInputStream.readObject(); //get requested maze dimensions
            //create IMazeGenerator class using the name that in configuration file to generate the maze with.
            Class<?> mazeGeneratorClass = Class.forName("algorithms.mazeGenerators." + Configurations.getInstance().getMazeGeneratingAlgorithm());
            IMazeGenerator mazeGenerator = (IMazeGenerator) mazeGeneratorClass.getDeclaredConstructor().newInstance();
            Maze maze = mazeGenerator.generate(mazeDimensions[0], mazeDimensions[1]);

            //TODO:COMPRESS BEFORE SEND!!
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(ba);
            compressorOutputStream.write(maze.toByteArray());
            //TODO: up don't look good but work

            toClient.writeObject(ba.toByteArray());
            toClient.flush();
            toClient.close();
        } catch (SocketException e) {
            System.out.println("Lost connection with client");
        } catch (IOException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
