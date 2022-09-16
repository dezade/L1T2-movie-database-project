package server;

import project.moviedatabase.Movie;

import java.io.*;
import java.util.List;

public class FileIO implements Serializable
{
    private static final long serialVersionUID = 0L;
    public static void fileInput(List<Movie> movieList, String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while(true)
        {
            String input = br.readLine();
            if(input == null)
                break;
            String[] info = input.split(",");
            Movie m = new Movie(info);
            if(m.getProductionCompany().isEmpty())
                m.setProductionCompany("N/A");
            movieList.add(m);
        }
        br.close();
    }

    public static void fileOutput(List<Movie> movieList, String fileName) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(Movie m : movieList)
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
            if(m.getProductionCompany().equalsIgnoreCase("N/A"))
            {
                bw.write(",");
            }
            else
            {
                bw.write(m.getProductionCompany()); bw.write(",");
            }
            bw.write(String.valueOf(m.getBudget())); bw.write(",");
            bw.write(String.valueOf(m.getRevenue()));
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
