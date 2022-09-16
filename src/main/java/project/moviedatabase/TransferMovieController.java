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

public class TransferMovieController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Button buttonTransferListGoBack;
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
        this.headerLabel.setText("Please select the movie to be transferred:");
        observableList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        observableList.addAll(productionCompany.movies);
        tableViewMovies.setItems(observableList);
        //tableViewMovies.getColumns().addAll(nameColumn);

        //Code for clicking on a movie to display in individually
        tableViewMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clickedMovie = tableViewMovies.getSelectionModel().getSelectedItem().getTitle();
            try {
                main.showTransferPanel(clickedMovie, this.productionCompany);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }
}
