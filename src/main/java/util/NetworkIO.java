package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkIO
{
    private final Socket socket;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;

    public NetworkIO(String ip, int port) throws IOException
    {
        this.socket = new Socket(ip, port);
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public NetworkIO(Socket s) throws IOException
    {
        this.socket = s;
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public Object read() throws IOException, ClassNotFoundException
    {
        return ois.readUnshared();
    }

    public void write(Object obj) throws IOException
    {
        oos.writeUnshared(obj);
    }

    public void close() throws IOException
    {
        ois.close();
        oos.close();
    }
}
