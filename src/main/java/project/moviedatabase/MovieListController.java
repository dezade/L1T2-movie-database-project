package project.moviedatabase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovieListController implements Serializable
{
    private Main main;
    @FXML
    public Label headerLabel;
    @FXML
    private TableView<String> tableViewMovies;
    private ProductionCompany productionCompany;
    private List<String> nameOfMovies = new ArrayList<>();
    String clickedMovie;

    public void setMain(Main main)
    {
        this.main = main;
    }
    public void init(ProductionCompany productionCompany)
    {
        this.productionCompany = productionCompany;
        for(Movie m : productionCompany.movies)
        {
            this.nameOfMovies.add(m.getTitle());
        }
        assert this.headerLabel != null;
        this.headerLabel.setText("List of movies for " + productionCompany.title);
        tableViewMovies.getItems().addAll(nameOfMovies);

        //Code for clicking on a movie to display in individually
        tableViewMovies.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clickedMovie = tableViewMovies.getSelectionModel().getSelectedItem();
                try {
                    main.showMovie(clickedMovie, productionCompany);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
