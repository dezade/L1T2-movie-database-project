package partOneFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseSystem
{
    static List<Movie> movieList = new ArrayList<>();
    static int[] top = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static List<ProductionCompany> prodCompList = new ArrayList<>();
    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";
/*
    public static void main(String[] args) throws IOException
    {
        FileIO.fileInput(movieList, prodCompList, INPUT_FILE_NAME);

        // Determining the top 10 movies
        for(int m = 0; m < movieList.size(); m++)
        {
            for(int i = 0; i < top.length; i++)
            {
                if(top[i] == -1)
                {
                    top[i] = m;
                    break;
                }
                else if(movieList.get(m).getProfit() > movieList.get(top[i]).getProfit())
                {
                    for(int k = (top.length-1); k > i; k--)
                    {
                        top[k] = top[k-1];
                    }
                    top[i] = m;
                    break;
                }
            }
        }

        boolean mainMenuOn = true;
        while(mainMenuOn)
        {
            int mainMenuOption = MainMenu.callMainMenu();
            switch (mainMenuOption) {
                case 1 ->
                {
                    boolean movieSearchMenuOn = true;
                    while (movieSearchMenuOn) {
                        int movieSearchMenuOption = MovieSearchMenu.callMovieSearchMenu();
                        switch (movieSearchMenuOption) {
                            case 1 -> MovieSearchMenu.searchByTitle(movieList);
                            case 2 -> MovieSearchMenu.searchByYearOfRelease(movieList);
                            case 3 -> MovieSearchMenu.searchByGenre(movieList);
                            case 4 -> MovieSearchMenu.searchByProdCom(movieList);
                            case 5 -> MovieSearchMenu.searchByRunningTime(movieList);
                            case 6 -> MovieSearchMenu.top10Movies(movieList, top);
                            case 7 -> movieSearchMenuOn = false;
                            default -> {
                                System.out.println();
                                System.out.print("ERROR! Please input an integer between 1 and 7 (inclusive): ");
                            }
                        }
                    }
                }
                case 2 ->
                {
                    boolean prodComSearchMenuOn = true;
                    while (prodComSearchMenuOn) {
                        int prodComSearchMenuOption = ProdComSearchMenu.callProdComSearchMenu();
                        switch (prodComSearchMenuOption) {
                            case 1 -> ProdComSearchMenu.mostRecentMovies(prodCompList);
                            case 2 -> ProdComSearchMenu.maxRevMovie(prodCompList);
                            case 3 -> ProdComSearchMenu.totalProfit(prodCompList);
                            case 4 -> ProdComSearchMenu.listAndCount(prodCompList);
                            case 5 -> prodComSearchMenuOn = false;
                            default -> {
                                System.out.println();
                                System.out.print("ERROR! Please input an integer between 1 and 5 (inclusive): ");
                            }
                        }
                    }
                }
                case 3 -> MainMenu.addMovie(movieList, prodCompList);
                case 4 -> mainMenuOn = false;
                default ->
                {
                    System.out.println();
                    System.out.print("ERROR! Please input an integer between 1 and 4 (inclusive): ");
                }
            }
        }

        FileIO.fileOutput(movieList, OUTPUT_FILE_NAME);
        System.out.println();
        System.out.println("Thank you for using the movie database!");
    }

 */
}
