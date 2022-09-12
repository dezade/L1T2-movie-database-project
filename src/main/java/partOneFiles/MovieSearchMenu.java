package partOneFiles;

import java.util.List;
import java.util.Scanner;

public class MovieSearchMenu
{
    static int r;

    public static int callMovieSearchMenu()
    {
        System.out.println();
        System.out.println("Movie Searching Options:");
        System.out.println("1) By Movie Title");
        System.out.println("2) By Release Year");
        System.out.println("3) By Genre");
        System.out.println("4) By Production Company");
        System.out.println("5) By Running Time");
        System.out.println("6) Top 10 Movies");
        System.out.println("7) Back to Main Menu");
        System.out.println();
        System.out.print("Please choose an option: ");
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            r = sc.nextInt();
            if(r < 1 || r > 7)
            {
                System.out.println();
                System.out.print("ERROR! Please input an integer between 1 and 7 (inclusive): ");
            }
            else
                break;
        }
        return r;
    }

    /*** Function for option 1 ***/
    public static void searchByTitle(List<Movie> moviesList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input the title of the movie: ");
        String input = sc.nextLine();
        boolean check = false;
        for(Movie m : moviesList)
        {
            if(m.getTitle().equalsIgnoreCase(input))
            {
                check = true;
                m.display();
            }
        }
        if(!check)
        {
            System.out.println();
            System.out.println("No such movie with this name.");
        }
    }

    /*** Function for option 2 ***/
    public static void searchByYearOfRelease(List<Movie> moviesList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a year of release: ");
        int input = sc.nextInt();
        boolean check = false;
        for(Movie m : moviesList)
        {
            if(m.getYearOfRelease() == input)
            {
                check = true;
                m.display();
            }
        }
        if(!check)
        {
            System.out.println();
            System.out.println("No such movie with this release year.");
        }
    }

    /*** Function for option 3 ***/
    public static void searchByGenre(List<Movie> moviesList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a genre: ");
        String input = sc.nextLine();
        boolean check = false;
        for(Movie m : moviesList)
        {
            for(String s : m.getGenres())
            {
                if(s.equalsIgnoreCase(input))
                {
                    check = true;
                    m.display();
                }
            }
        }
        if(!check)
        {
            System.out.println();
            System.out.println("No such movie with this genre.");
        }
    }

    /*** Function for option 4 ***/
    public static void searchByProdCom(List<Movie> moviesList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input a production company: ");
        String input = sc.nextLine();
        boolean check = false;
        for(Movie m : moviesList)
        {
            if(m.getProductionCompany().equalsIgnoreCase(input))
            {
                check = true;
                m.display();
            }
        }
        if(!check)
        {
            System.out.println();
            System.out.println("No such movie with this production company.");
        }
    }

    /*** Function for option 5 ***/
    public static void searchByRunningTime(List<Movie> moviesList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please input the lower bound for running time: ");
        int low = sc.nextInt();
        System.out.println();
        System.out.print("Please input the upper bound for running time: ");
        int up = sc.nextInt();
        boolean check = false;
        for(Movie m : moviesList)
        {
            if(m.getRunningTime() >= low && m.getRunningTime() <= up)
            {
                check = true;
                m.display();
            }
        }
        if(!check)
        {
            System.out.println();
            System.out.println("No such movie with this running time range.");
        }
    }

    /*** Function for option 6 ***/
    public static void top10Movies(List<Movie> moviesList, int[] top)
    {
        System.out.println();
        System.out.println("The top ten movies based on profit are as follows: ");
        for(int i : top)
        {
            moviesList.get(i).display();
        }
    }
}
