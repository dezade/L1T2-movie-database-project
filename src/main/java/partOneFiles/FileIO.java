package partOneFiles;

import java.io.*;
import java.util.List;

public class FileIO
{
    public static void fileInput(List<Movie> moviesList, List<ProductionCompany> prodCompList, String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while(true)
        {
            String input = br.readLine();
            if(input == null)
                break;
            String[] info = input.split(",");
            Movie m = new Movie(info);
            moviesList.add(m);
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
        }
        br.close();
    }

    public static void fileOutput(List<Movie> moviesList, String fileName) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(Movie m : moviesList)
        {
            bw.write(m.getTitle()); bw.write(",");
            bw.write(String.valueOf(m.getYearOfRelease())); bw.write(",");
            for(String s : m.getGenres())
            {
                if(s != null)
                    bw.write(s);
                bw.write(",");
            }
            bw.write(String.valueOf(m.getRunningTime())); bw.write(",");
            bw.write(m.getProductionCompany()); bw.write(",");
            bw.write(String.valueOf(m.getBudget())); bw.write(",");
            bw.write(String.valueOf(m.getRevenue()));
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
