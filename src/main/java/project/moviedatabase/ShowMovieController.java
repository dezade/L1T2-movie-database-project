package project.moviedatabase;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import util.StringPair;

import java.io.IOException;
import java.io.Serializable;

public class ShowMovieController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Button movieGoBack;
    public Button movieTransfer;
    public Button buttonWatchTrailer;
    public Label prodComp;
    public Label year;
    public Label genres;
    public Label runningTime;
    public Label budget;
    public Label revenue;
    public Label profit;
    private Movie movie;
    private ProductionCompany productionCompany;
    private Main main;
    @FXML
    public Label movieTitle;

    public void setMain(Main main)
    {
        this.main = main;
    }

    /*public void setInfo()
    {
        String genres = movie.getGenres()[0];
        if(movie.getGenres()[1] != null)
        {
            genres = genres + ", " + movie.getGenres()[1];
            if(movie.getGenres()[2] != null)
            {
                genres = genres + ", " + movie.getGenres()[2];
            }
        }
        info = FXCollections.observableArrayList(
                new StringPair("Production Company", movie.getProductionCompany()),
                new StringPair("Year of release", String.valueOf(movie.getYearOfRelease())),
                new StringPair("Genre(s)", genres),
                new StringPair("Running time (in minutes)", String.valueOf(movie.getRunningTime())),
                new StringPair("Budget", String.valueOf(movie.getBudget())),
                new StringPair("Revenue", String.valueOf(movie.getRevenue())),
                new StringPair("Profit", String.valueOf(movie.getProfit()))
        );
    }*/
    public void init(String title, ProductionCompany productionCompany)
    {
        this.productionCompany = productionCompany;
        for(Movie m : productionCompany.movies)
        {
            if(title.equalsIgnoreCase(m.getTitle()))
            {
                this.movie = new Movie();
                this.movie = m;
                break;
            }
        }
        movieTitle.setText(this.movie.getTitle());
        prodComp.setText(this.movie.getProductionCompany());
        year.setText(String.valueOf(this.movie.getYearOfRelease()));
        runningTime.setText(String.valueOf(this.movie.getRunningTime()));
        budget.setText(String.valueOf(this.movie.getBudget()));
        revenue.setText(String.valueOf(this.movie.getRevenue()));
        profit.setText(String.valueOf(this.movie.getProfit()));
        String gen = this.movie.getGenres()[0];
        if(!this.movie.getGenres()[1].isEmpty())
        {
            gen = gen + ", " + this.movie.getGenres()[1];
            if(!this.movie.getGenres()[2].isEmpty())
            {
                gen = gen + ", " + this.movie.getGenres()[2];
            }
        }
        genres.setText(gen);
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showMovieList(this.productionCompany);
    }

    public void onTransferClick(ActionEvent actionEvent) throws IOException {
        main.showTransferPanel(this.movie.getTitle(), this.productionCompany);
    }

    public void onTrailerClick(ActionEvent actionEvent) {
    }
}
