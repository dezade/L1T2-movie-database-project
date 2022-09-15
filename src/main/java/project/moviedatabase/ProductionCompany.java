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
    public String title;
    public long maxRevenue;
    public long totalProfit;
    public int latestYear;
    public List<Movie> movies;

    public ProductionCompany(String title) throws IOException
    {
        this.title = title;
//        prodCompLabel.setText(title);
        maxRevenue = -1;
        totalProfit = 0;
        latestYear = -1;
        movies = new ArrayList<>();
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

    public List<Movie> mostRecentMovies()
    {
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

    public List<Movie> maximumRevenueMovies()
    {
        List<Movie> result = new ArrayList<>();
        for(Movie m : this.movies)
        {
            if(m.getProfit() == this.maxRevenue)
            {
                result.add(m);
            }
        }
        return result;
    }

    public long getTotalProfit() {
        return totalProfit;
    }
    
}
