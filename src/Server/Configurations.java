package Server;

import algorithms.search.*;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Server Configuration - implementing "SingleTone Pattern"
 */
class Configurations
{
    // static variable of Singleton pattern
    private static Configurations single_instance = null;

    private Properties properties = new Properties();

    private int threadPoolSize;
    private String mazeGeneratingAlgorithm;
    private String mazeSearchingAlgorithm;

    // private constructor restricted to this class itself
    private Configurations()
    {
        File file = new File("resources/config.properties");
        try {
            if (file.createNewFile()) {
                FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
                properties.setProperty("threadPoolSize", "" + 3);
                properties.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
                properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
                properties.store(fileOut,null);
            }
                FileInputStream fileIn = new FileInputStream("resources/config.properties");
                properties.load(fileIn);
                this.threadPoolSize = Integer.parseInt(properties.getProperty("threadPoolSize"));
                this.mazeGeneratingAlgorithm = properties.getProperty("mazeGeneratingAlgorithm");
                this.mazeSearchingAlgorithm = properties.getProperty("mazeSearchingAlgorithm");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // static method to create instance of Singleton class
    public static Configurations getInstance()
    {
        if (single_instance == null)
            single_instance = new Configurations();

        return single_instance;
    }

    public int getThreadPoolSize() {
        return this.threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) throws IllegalArgumentException {
        if (threadPoolSize < 1)
            throw new IllegalArgumentException("ThreadPool size cant be < 1");
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
            properties.setProperty("threadPoolSize", "" + threadPoolSize);
            properties.store(fileOut,null);
        }
        catch (IOException io){
            io.printStackTrace();
        }
        this.threadPoolSize = threadPoolSize;
    }

    public String getMazeGeneratingAlgorithm() {
        return mazeGeneratingAlgorithm;
    }

    /**
     *
     * @param mazeGeneratingAlgorithm
     * @throws ClassNotFoundException Given class isn't implementing IMazeGenerator
     */
    public void setMazeGeneratingAlgorithm(String mazeGeneratingAlgorithm) throws ClassNotFoundException {
        try {
            Class<?> mazeGenerateClass = Class.forName("algorithms.mazeGenerators." + mazeSearchingAlgorithm);
            IMazeGenerator mazeGenerateAlgorithm = (IMazeGenerator) mazeGenerateClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new ClassNotFoundException("Given class isn't implementing IMazeGenerator");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
            properties.setProperty("mazeGeneratingAlgorithm", mazeGeneratingAlgorithm);
            properties.store(fileOut,null);
        }
        catch (IOException io){
            io.printStackTrace();
        }
        this.mazeGeneratingAlgorithm = mazeGeneratingAlgorithm;
    }

    public String getMazeSearchingAlgorithm() {
        return mazeSearchingAlgorithm;
    }

    /**
     *
     * @param mazeSearchingAlgorithm
     * @throws ClassNotFoundException Given class isn't implementing ISearchingAlgorithm
     */
    public void setMazeSearchingAlgorithm(String mazeSearchingAlgorithm) throws ClassNotFoundException {
        try {
            Class<?> mazeSearchClass = Class.forName("algorithms.search." + mazeSearchingAlgorithm);
            ISearchingAlgorithm mazeSearchAlgorithm = (ISearchingAlgorithm) mazeSearchClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new ClassNotFoundException("Given class isn't implementing ISearchingAlgorithm");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
            properties.setProperty("mazeSearchingAlgorithm", mazeSearchingAlgorithm);
            properties.store(fileOut,null);
        }
        catch (IOException io){
            io.printStackTrace();
        }
        this.mazeSearchingAlgorithm = mazeSearchingAlgorithm;
    }
}