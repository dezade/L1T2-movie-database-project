package project.moviedatabase;

import java.util.List;
import java.util.Scanner;

public class ProdComSearchMenu
{
    static int r;

    public static int callProdComSearchMenu()
    {
        System.out.println();
        System.out.println("Production Company Searching Options:");
        System.out.println("1) Most Recent Movies");
        System.out.println("2) Movies with the Maximum Revenue");
        System.out.println("3) Total Profit");
        System.out.println("4) List of Production Companies and the Count of their Produced Movies");
        System.out.println("5) Back to Main Menu");
        System.out.println();
        System.out.print("Please choose an option: ");
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            r = sc.nextInt();
            if(r < 1 || r > 5)
            {
                System.out.println();
                System.out.print("ERROR! Please input an integer between 1 and 5 (inclusive): ");
            }
            else
                break;
        }
        return r;
    }

    /*** Function for option 1 ***/
    public static void mostRecentMovies(List<ProductionCompany> prodCompList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a production: ");
        String input = sc.nextLine();
        boolean check = false;
/*        for(ProductionCompany pc : prodCompList)
        {
            if(pc.getTitle().equalsIgnoreCase(input))
            {
                check = true;
                System.out.println();
                System.out.print("The latest movie(s) of the inquired production company are as follows: ");
                for(Movie m : pc.movies)
                {
                    if(m.getYearOfRelease() == pc.getLatestYear())
                        m.display();
                }
                break;
            }
        }*/
        if(!check)
        {
            System.out.println();
            System.out.println("No such production company with this name.");
        }
    }

    /*** Function for option 2 ***/
    public static void maxRevMovie(List<ProductionCompany> prodCompList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a production: ");
        String input = sc.nextLine();
        boolean check = false;
/*        for(ProductionCompany pc : prodCompList)
        {
            if(pc.getTitle().equalsIgnoreCase(input))
            {
                check = true;
                System.out.println();
                System.out.print("The movie(s) of the inquired production company with maximum revenue are as follows: ");
                for(Movie m : pc.movies)
                {
                    if(m.getRevenue() == pc.getMaxRevenue())
                        m.display();
                }
                break;
            }
        }*/
        if(!check)
        {
            System.out.println();
            System.out.println("No such production company with this name.");
        }
    }

    /*** Function for option 3 ***/
    public static void totalProfit(List<ProductionCompany> prodCompList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a production: ");
        String input = sc.nextLine();
        boolean check = false;
/*        for(ProductionCompany pc : prodCompList)
        {
            if(pc.getTitle().equalsIgnoreCase(input))
            {
                check = true;
                System.out.println();
                System.out.println("The total profit of the inquired production company is: " + pc.getTotalProfit());
                break;
            }
        }*/
        if(!check)
        {
            System.out.println();
            System.out.println("No such production company with this name.");
        }
    }

    /*** Function for option 4 ***/
/*    public static void listAndCount(List<ProductionCompany> prodCompList)
    {
        System.out.println();
        System.out.println("The list of all production companies with respective total number of movies are as follows: ");
        System.out.println();
        for(ProductionCompany pc : prodCompList)
        {
            System.out.println(pc.getTitle() + ": " + pc.movies.size());
        }
    }*/
}
