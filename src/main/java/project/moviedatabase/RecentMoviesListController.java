package project.moviedatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.Serializable;

public class RecentMoviesListController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Label latestYear;
    private Main main;
    private ProductionCompany productionCompany;
    public Label headerLabel;
    public TableView<Movie> tableViewMovies;
    public TableColumn<Movie, String> nameColumn;
    public Button buttonMovieListGoBack;
    ObservableList<Movie> observableList;
    String clickedMovie;

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void init(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        this.productionCompany = productionCompany;
        //productionCompany.updateMovies();
        assert this.headerLabel != null;
        this.headerLabel.setText("Most Recent Movies for " + productionCompany.title);
        observableList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        observableList.addAll(productionCompany.mostRecentMovies());
        tableViewMovies.setItems(observableList);
        //tableViewMovies.getColumns().addAll(nameColumn);
        this.latestYear.setText("Year of Release : " + String.valueOf(productionCompany.latestYear));

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
}
