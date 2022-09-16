package server;

import project.moviedatabase.Movie;
import project.moviedatabase.ProductionCompany;
import util.NetworkIO;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server implements Serializable
{
    private static final long serialVersionUID = 0L;
    private static ServerSocket serverSocket;
    private static int port = 42069;
    private static final String INPUT_FILE = "movies.txt";
    private static final String OUTPUT_FILE = "movies.txt";
    //hashmap stuffs
    public static HashMap<String, ProductionCompany> serverList;
    public static List<Movie> movieList;

    public static void serve(Socket prodCompSocket) throws IOException, ClassNotFoundException {
        NetworkIO networkIO = new NetworkIO(prodCompSocket);
        //CHHHHHHEEEEEEEECCCCCCCCCCCKKKKKKKKK
        /*Object call = networkIO.read();
        System.out.println((String) call);
        networkIO.write("Hello to you to");*/
        //CHHHHHHEEEEEEEECCCCCCCCCCCKKKKKKKKK
        try
        {
            new Thread(()->{
                new ServerRead(movieList, serverList, networkIO);
            }).start();
        } catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

    }

    public static void main(String[] args) throws IOException
    {
        serverList = new HashMap<>();
        movieList = new ArrayList<>();
        FileIO.fileInput(movieList, INPUT_FILE);
        for(Movie m : movieList)
        {
            ProductionCompany pc = new ProductionCompany(m.getProductionCompany());
            if(serverList.containsKey(m.getProductionCompany().toLowerCase()))
            {
                pc = serverList.get(m.getProductionCompany().toLowerCase());
            }
            pc.addMovie(m);
            serverList.put(m.getProductionCompany().toLowerCase(), pc);
        }
        serverSocket = new ServerSocket(port);
        System.out.println();
        System.out.println("Server is online!");
        System.out.println();
        while(true)
        {
            try
            {
                Socket prodCompSocket = serverSocket.accept();
                System.out.println("Client connected!");
                serve(prodCompSocket);
            } catch (IOException | ClassNotFoundException e)
            {
                System.out.println("Connection interrupted");
                FileIO.fileOutput(movieList, OUTPUT_FILE);
            }
        }
    }
}
