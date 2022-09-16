package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.NetworkIO;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductionCompany implements Serializable
{
    private static final long serialVersionUID = 0L;
    public String title;
    public long maxRevenue;
    public long totalProfit;
    public int latestYear;
    public List<Movie> movies;
    public NetworkIO networkIO;
    //public ProductionCompanyRead readThread;

    public ProductionCompany(String title) throws IOException
    {
        this.title = title;
        maxRevenue = -1;
        totalProfit = 0;
        latestYear = -1;
        movies = new ArrayList<>();
    }

    public void updateMovies() throws IOException, ClassNotFoundException {
        //networkIO.write(this);
        networkIO.write(title);
        Object obj = networkIO.read();
        if(obj instanceof ProductionCompany)
        {
            this.movies = ((ProductionCompany) obj).movies;
        }
    }

    public void updateAll()
    {
        this.latestYear = -1;
        this.maxRevenue = -1;
        this.totalProfit = 0;
        for (Movie m : movies)
        {
            if(m.getYearOfRelease() > this.latestYear)
                this.latestYear = m.getYearOfRelease();
            if(m.getRevenue() > this.maxRevenue)
                this.maxRevenue = m.getRevenue();
            this.totalProfit += m.getProfit();
        }
    }

    public void addMovie(Movie m)
    {
        this.movies.add(m);
        if(m.getYearOfRelease() > this.latestYear)
            this.latestYear = m.getYearOfRelease();
        if(m.getRevenue() > this.maxRevenue)
            this.maxRevenue = m.getRevenue();
        this.totalProfit += m.getProfit();
    }

    public List<Movie> mostRecentMovies() throws IOException, ClassNotFoundException {
        updateMovies();
        updateAll();
        List<Movie> result = new ArrayList<>();
        for(Movie m : this.movies)
        {
            if(m.getYearOfRelease() == this.latestYear)
            {
                result.add(m);
            }
        }
        return result;
    }

    public List<Movie> maximumRevenueMovies() throws IOException, ClassNotFoundException {
        updateMovies();
        updateAll();
        List<Movie> result = new ArrayList<>();
        for(Movie m : this.movies)
        {
            if(m.getRevenue() == this.maxRevenue)
            {
                result.add(m);
            }
        }
        return result;
    }

    public long getTotalProfit() throws IOException, ClassNotFoundException {
        updateMovies();
        updateAll();
        return totalProfit;
    }
    
}
