package project.moviedatabase;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import util.StringPair;

import java.io.Serializable;

public class ShowMovieController implements Serializable
{
    private Movie movie;
    private ProductionCompany productionCompany;
    private Main main;
    @FXML
    public Label movieTitle;
    @FXML
    public TableView<StringPair> movieInfo;
    @FXML
    public TableColumn<StringPair, String> categoryColumn;
    @FXML
    public TableColumn<StringPair, String> informationColumn;

    public void setMain(Main main)
    {
        this.main = main;
    }

    ObservableList<StringPair> info;

    public void setInfo()
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
    }
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
        setInfo();
        movieTitle.setText(this.movie.getTitle());
        categoryColumn = new TableColumn<>("Category");
        informationColumn = new TableColumn<>("Information");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<StringPair, String>("first"));
        informationColumn.setCellValueFactory(new PropertyValueFactory<StringPair, String>("second"));
        movieInfo.setItems(info);
    }
}
