package server;

import project.moviedatabase.Movie;
import project.moviedatabase.ProductionCompany;
import util.NetworkIO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ServerRead
{
    public List<Movie> movieList;
    public HashMap<String, ProductionCompany> serverList;
    private final NetworkIO networkIO;
    //private final Thread thread;

    public ServerRead(List<Movie> movieList, HashMap<String, ProductionCompany> serverList, NetworkIO networkIO)
    {
        this.movieList = movieList;
        this.serverList = serverList;
        this.networkIO = networkIO;
        //this.thread = new Thread(this);
        //thread.start();
        try
        {
            process();
        } catch(IOException | ClassNotFoundException e)
        {
            System.out.println("Connection interrupted");
        }
    }

    public void process() throws IOException, ClassNotFoundException
    {
        Object obj = networkIO.read();
        if(obj instanceof String)
        {
            String clientName = (String) obj;
            if(serverList.containsKey(clientName.toLowerCase()))
            {
                networkIO.write(serverList.get(clientName.toLowerCase()));
            }
            else
            {
                networkIO.write("Error! Credentials do not match!");
            }
        }

    }

/*
    @Override
    public void run() {
        try
        {
            while (true)
            {
                Object obj = networkIO.read();
                if(obj != null) {
                    if (obj instanceof ProductionCompany) {
                        ProductionCompany productionCompany = (ProductionCompany) obj;
                        productionCompany = serverList.get(productionCompany.title);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try
            {
                networkIO.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
 */
}
