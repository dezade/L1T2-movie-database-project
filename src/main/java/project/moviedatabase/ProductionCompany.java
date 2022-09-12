package project.moviedatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    @FXML
    public Label prodCompLabel;

    public ProductionCompany(String title)
    {
        this.title = title;
        maxRevenue = -1;
        totalProfit = 0;
        latestYear = -1;
        movies = new ArrayList<>();
    }

/*
    public void addMovie(Movie m)
    {
        this.movies.add(m);
        if(m.getYearOfRelease() > this.latestYear)
            this.latestYear = m.getYearOfRelease();
        if(m.getRevenue() > this.maxRevenue)
            this.maxRevenue = m.getRevenue();
        this.totalProfit += m.getProfit();
    }
*/

}
