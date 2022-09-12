package partOneFiles;

import java.util.List;
import java.util.Scanner;

public class MainMenu
{
    static int r;

    public static int callMainMenu()
    {
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("1) Search Movies");
        System.out.println("2) Search Production Companies");
        System.out.println("3) Add Movie");
        System.out.println("4) Exit System");
        System.out.println();
        System.out.print("Please choose an option: ");
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            r = sc.nextInt();
            if(r < 1 || r > 4)
            {
                System.out.println();
                System.out.print("ERROR! Please input an integer between 1 and 4 (inclusive): ");
            }
            else
                break;
        }
        return r;
    }

    public static void addMovie(List<Movie> movieList, List<ProductionCompany> prodCompList)
    {
        Scanner sc = new Scanner(System.in);
        Movie m = new Movie();

        System.out.println();
        System.out.print("Please input the title of the movie: ");
        m.setTitle(sc.nextLine());

        for(Movie x : movieList)
        {
            if(m.getTitle().equalsIgnoreCase(x.getTitle()))
            {
                System.out.println();
                System.out.print("ERROR! A movie with the provided title already exists in the database.");
                System.out.println();
                return;
            }
        }

        System.out.println();
        System.out.print("Please input the year of release of the movie: ");
        m.setYearOfRelease(Integer.parseInt(sc.nextLine()));

        System.out.println();
        String[] gen = new String[3];
        System.out.print("Please input the first genre of the movie (Required): ");
        gen[0] = sc.nextLine();

        System.out.println();
        System.out.print("Please input the second genre of the movie (If there is no second genre, please input -1): ");
        gen[1] = sc.nextLine();
        if(gen[1].equals("-1"))
            gen[1] = null;
        else
        {
            System.out.println();
            System.out.print("Please input the third genre of the movie (If there is no third genre, please input -1): ");
            gen[2] = sc.nextLine();
            if(gen[2].equals("-1"))
                gen[2] = null;
        }
        m.setGenres(gen);

        System.out.println();
        System.out.print("Please input the running time (in minutes) of the movie: ");
        m.setRunningTime(Integer.parseInt(sc.nextLine()));

        System.out.println();
        System.out.print("Please input the production company of the movie: ");
        m.setProductionCompany(sc.nextLine());


        System.out.println();
        System.out.print("Please input the budget of the movie: ");
        m.setBudget(Long.parseLong(sc.nextLine()));

        System.out.println();
        System.out.print("Please input the revenue of the movie: ");
        m.setRevenue(Long.parseLong(sc.nextLine()));

        m.setProfit();

        movieList.add(m);
        boolean check = false;
        for(ProductionCompany prodComp : prodCompList)
        {
            if(m.getProductionCompany().equals(prodComp.getTitle()))
            {
                prodComp.addMovie(m);
                check = true;
                break;
            }
        }
        if(!check)
        {
            ProductionCompany pc = new ProductionCompany(m.getProductionCompany());
            pc.addMovie(m);
            prodCompList.add(pc);
        }

        System.out.println();
        System.out.println("Thank you! The movie has been added to the database.");
    }
}
