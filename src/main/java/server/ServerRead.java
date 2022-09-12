package server;

import project.moviedatabase.Movie;
import project.moviedatabase.Movie;
import util.NetworkIO;

import java.util.List;

public class ServerRead implements Runnable
{
    public final List<Movie> movieList;
    private final NetworkIO networkIO;

    ServerRead(List<Movie> movieList, NetworkIO networkIO)
    {
        this.movieList = movieList;
        this.networkIO = networkIO;
        new Thread(()->
        {

        }).start();
    }

    @Override
    public void run() {

    }
}
