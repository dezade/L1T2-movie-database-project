package project.moviedatabase;

public class Movie
{
    private String title;
    private int yearOfRelease;
    private String[] genres = new String[3];
    private int runningTime;
    private String productionCompany;
    private long budget;
    private long revenue;
    private long profit;

    public Movie()
    {
        this.title = null;
        this.yearOfRelease = 0;
        for(String s : this.genres)
            s = null;
        this.runningTime = 0;
        this.productionCompany = null;
        this.budget = 0;
        this.revenue = 0;
        this.profit = 0;
    }

    public Movie(String[] all)
    {
        this.title = all[0];
        this.yearOfRelease = Integer.parseInt(all[1]);
        for(int i = 0; i < 3; i++)
            genres[i] = all[2+i];
        this.runningTime = Integer.parseInt(all[5]);
        this.productionCompany = all[6];
        this.budget = Long.parseLong(all[7]);
        this.revenue = Long.parseLong(all[8]);
        this.profit = (this.revenue - this.budget);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    /*
    public void display()
    {
        System.out.println();
        System.out.println("Title: " + this.title);
        System.out.println("Year of release: " + this.yearOfRelease);
        for(int i = 0; i < 3; i++)
        {
            if(genres[i] != null)
            {
                System.out.println("Genre " + (i+1) + ": " + genres[i]);
            }
        }
        System.out.println("Running Time: " + this.runningTime);
        System.out.println("Production Company: " + this.productionCompany);
        System.out.println("Budget: " + this.budget);
        System.out.println("Revenue: " + this.revenue);
    }
*/
}
