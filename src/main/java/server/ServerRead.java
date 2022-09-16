package server;

import project.moviedatabase.Movie;
import project.moviedatabase.ProductionCompany;
import util.MovieWrapper;
import util.NetworkIO;
import util.StringPair;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ServerRead implements Serializable
{
    private static final long serialVersionUID = 0L;
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
        } finally
        {
            try {
                networkIO.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void process() throws IOException, ClassNotFoundException
    {
        boolean credCheck = true;
        while (credCheck)
        {
            Object obj = networkIO.read();
            if(obj instanceof String)
            {
                String clientName = (String) obj;
                if(serverList.containsKey(clientName.toLowerCase()))
                {
                    networkIO.write(serverList.get(clientName.toLowerCase()));
                    credCheck = false;
                }
                else
                {
                    networkIO.write("Error! Credentials do not match!");
                }
            }
        }

        while(true)
        {
            Object obj = networkIO.read();
            if(obj != null)
            {
                if(obj instanceof StringPair)
                {
                    StringPair query = (StringPair) obj;

                    //Checking if new movie exists
                    if(query.first.equalsIgnoreCase("new"))
                    {
                        Boolean exists = false;
                        for(Movie m : movieList)
                        {
                            if(m.getTitle().equalsIgnoreCase(query.second))
                            {
                                exists = true;
                                break;
                            }
                        }
                        networkIO.write(exists);
                    }

                    //Production Company check
                    else if(query.first.equalsIgnoreCase("ProdComp"))
                    {
                        if(serverList.containsKey(query.second))
                        {
                            networkIO.write(serverList.get(query.second));
                        }
                        else
                        {
                            networkIO.write("Does not exist");
                        }
                    }
                }
                //Adding or transferring a new movie
                else if(obj instanceof MovieWrapper)
                {
                    MovieWrapper mw = (MovieWrapper) obj;

                    //Adding
                    if(mw.command.equalsIgnoreCase("ADD"))
                    {
                        movieList.add(mw.getMovie());
                        serverList.put(mw.getOwnerProductionCompany().title.toLowerCase(), mw.getOwnerProductionCompany());
                    }

                    //Transferring
                    else if(mw.command.equalsIgnoreCase("TRANSFER"))
                    {
                        mw.getMovie().setProductionCompany(mw.getReceiverProductionCompany().title);
                        serverList.get(mw.getOwnerProductionCompany().title.toLowerCase()).movies.remove(mw.getMovie());
                        serverList.get(mw.getReceiverProductionCompany().title.toLowerCase()).movies.add(mw.getMovie());
                    }
                    networkIO.write("OK");
                }
                //UpdatePC
                else if (obj instanceof ProductionCompany)
                {
                    ProductionCompany pc = (ProductionCompany) obj;
                    networkIO.write(serverList.get(pc.title.toLowerCase()));
                }

                else if(obj instanceof String)
                {
                    String title = (String) obj;
                    networkIO.write(serverList.get(title.toLowerCase()));
                }
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
