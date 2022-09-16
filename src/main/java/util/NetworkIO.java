package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class NetworkIO implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public NetworkIO(String ip, int port) throws IOException
    {
        this.socket = new Socket(ip, port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    public NetworkIO(Socket s) throws IOException
    {
        this.socket = s;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException
    {
        return ois.readUnshared();
    }

    public void write(Object obj) throws IOException
    {
        oos.writeUnshared(obj);
    }

    public void closeConnection() throws IOException
    {
        ois.close();
        oos.close();
    }
}
