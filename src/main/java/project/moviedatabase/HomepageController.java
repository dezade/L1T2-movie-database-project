package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.NetworkIO;

import java.io.IOException;
import java.io.Serializable;

public class HomepageController implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    @FXML
    public Label prodCompLabel;
    @FXML
    public Button buttonRecentMovies;
    @FXML
    public Button buttonMaxRev;
    @FXML
    public Button buttonTotalProfit;
    @FXML
    public Button buttonLogOut;
    public Button buttonAddMovie;
    public Button buttonTransferMovie;
    public Button buttonSearchMovie;
    public Button buttonMovieList;
    private ProductionCompany productionCompany;
    private NetworkIO networkIO;

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany)
    {
        this.productionCompany = productionCompany;
    }

    public ProductionCompany getProductionCompany()
    {
        return this.productionCompany;
    }

    public void setNetworkIO(NetworkIO networkIO) {
        this.networkIO = networkIO;
    }

    public NetworkIO getNetworkIO() {
        return networkIO;
    }

    public void onMovieListClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showMovieList(this.productionCompany);
    }

    public void onRecentMoviesClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showRecentMoviesList(productionCompany);
    }

    public void onMaxRevClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showMaxRevMoviesList(productionCompany);
    }

    public void onTotalProfitClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showTotalProfitPanel(productionCompany);
    }

    public void onLogOutClick(ActionEvent actionEvent)
    {
        main.showLogOutAlert(main.getStage());
    }

    public void onAddMovieClick(ActionEvent actionEvent) throws IOException {
        main.showAddNewMovie(productionCompany);
    }

    public void onTransferMovieClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        main.showTransferMovie(productionCompany);
    }

    public void onSearchMovieClick(ActionEvent actionEvent) throws IOException {
        main.showSearchMovieOptions(productionCompany);
    }


}
