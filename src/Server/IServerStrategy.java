package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    void ServerStrategy(InputStream inFromClient, OutputStream outToClient);
}
