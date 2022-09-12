package server;

import project.moviedatabase.Movie;
import util.NetworkIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    private ServerSocket serverSocket;

    private final String INPUT_FILE = "movies.txt";
    private final String OUTPUT_FILE = "movies.txt";
    //hashmap stuffs
    public List<Movie> movieList;
    Server() throws IOException {
        movieList = new ArrayList<>();
        FileIO.fileInput(movieList, INPUT_FILE);
        try
        {
            serverSocket = new ServerSocket(42069);
            while(true)
            {
                Socket prodCompSocket = serverSocket.accept();
                serve(prodCompSocket);
            }
        }
        catch (Exception e)
        {
            System.out.println("Server exception: " + e);
        }

    }

    public void serve(Socket prodCompSocket) throws IOException
    {
        NetworkIO networkIO = new NetworkIO(prodCompSocket);
        new ServerRead(movieList, networkIO);
    }

    public static void main(String[] args) throws IOException
    {
        new Server();
    }
}
