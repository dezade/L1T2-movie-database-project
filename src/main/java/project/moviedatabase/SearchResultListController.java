package project.moviedatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class SearchResultListController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Button buttonMovieListGoBack;
    private Main main;
    @FXML
    public Label headerLabel;
    @FXML
    private TableView<Movie> tableViewMovies;
    @FXML
    private TableColumn<Movie, String> nameColumn;
    private ProductionCompany productionCompany;
    private List<Movie> movieList;
    private ObservableList<Movie> observableList;
    String clickedMovie;

    public void setMain(Main main)
    {
        this.main = main;
    }
    public void init(List<Movie> movieList, ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        this.movieList = movieList;
        this.productionCompany = productionCompany;
        //productionCompany.updateMovies();
        assert this.headerLabel != null;
        this.headerLabel.setText("Search Results :");
        observableList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        observableList.addAll(movieList);
        tableViewMovies.setItems(observableList);
        //tableViewMovies.getColumns().addAll(nameColumn);

        //Code for clicking on a movie to display in individually
        tableViewMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clickedMovie = tableViewMovies.getSelectionModel().getSelectedItem().getTitle();
            try {
                main.showMovie(clickedMovie, productionCompany);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showSearchMovieOptions(productionCompany);
    }
}
