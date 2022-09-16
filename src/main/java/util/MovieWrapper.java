package util;

import project.moviedatabase.Movie;
import project.moviedatabase.ProductionCompany;

import java.io.Serializable;

public class MovieWrapper implements Serializable
{
    private static final long serialVersionUID = 0L;
    public String command;
    private Movie movie;
    private ProductionCompany ownerProductionCompany;
    private ProductionCompany receiverProductionCompany;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ProductionCompany getOwnerProductionCompany() {
        return ownerProductionCompany;
    }

    public void setOwnerProductionCompany(ProductionCompany ownerProductionCompany) {
        this.ownerProductionCompany = ownerProductionCompany;
    }

    public ProductionCompany getReceiverProductionCompany() {
        return receiverProductionCompany;
    }

    public void setReceiverProductionCompany(ProductionCompany receiverProductionCompany) {
        this.receiverProductionCompany = receiverProductionCompany;
    }

    public MovieWrapper(Movie movie, ProductionCompany ownerProductionCompany)
    {
        command = "ADD";
        this.movie = movie;
        this.ownerProductionCompany = ownerProductionCompany;
    }

    public MovieWrapper(Movie movie, ProductionCompany ownerProductionCompany, ProductionCompany receiverProductionCompany)
    {
        command = "TRANSFER";
        this.movie = movie;
        this.ownerProductionCompany = ownerProductionCompany;
        this.receiverProductionCompany = receiverProductionCompany;
    }
}
