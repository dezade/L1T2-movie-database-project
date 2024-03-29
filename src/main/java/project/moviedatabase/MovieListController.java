package project.moviedatabase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.NetworkIO;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovieListController implements Serializable
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
    private ObservableList<Movie> observableList;
    String clickedMovie;

    public void setMain(Main main)
    {
        this.main = main;
    }
    public void init(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        this.productionCompany = productionCompany;
        //productionCompany.updateMovies();
        assert this.headerLabel != null;
        this.headerLabel.setText("List of movies for " + productionCompany.title);
        observableList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        observableList.addAll(productionCompany.movies);
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
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }
}
