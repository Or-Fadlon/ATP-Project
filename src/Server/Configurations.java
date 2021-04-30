package Server;

import java.io.*;
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
        //TODO: handle a case when the file in exist but is empty
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

    public void setMazeGeneratingAlgorithm(String mazeGeneratingAlgorithm) {
        //TODO: handle algorithm not exist
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
            properties.setProperty("mazeGeneratingAlgorithm", "" + threadPoolSize);
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

    public void setMazeSearchingAlgorithm(String mazeSearchingAlgorithm) {
        //TODO: handle algorithm not exist
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/config.properties");
            properties.setProperty("mazeSearchingAlgorithm", "" + threadPoolSize);
            properties.store(fileOut,null);
        }
        catch (IOException io){
            io.printStackTrace();
        }
        this.mazeSearchingAlgorithm = mazeSearchingAlgorithm;
    }
}