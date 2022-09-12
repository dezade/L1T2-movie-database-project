package partOneFiles;

import java.util.ArrayList;
import java.util.List;

public class ProductionCompany
{
    private final String title;
    private long maxRevenue;
    private long totalProfit;
    private int latestYear;
    List<Movie> movies;

    public ProductionCompany(String title)
    {
        this.title = title;
        maxRevenue = -1;
        totalProfit = 0;
        latestYear = -1;
        movies = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }

    public long getMaxRevenue() {
        return maxRevenue;
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public int getLatestYear() {
        return latestYear;
    }

    public void setLatestYear(int latestYear) {
        this.latestYear = latestYear;
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
}
