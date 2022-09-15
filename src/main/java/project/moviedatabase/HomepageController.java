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

    public void onMovieListClick(ActionEvent actionEvent) throws IOException {
        main.showMovieList(this.productionCompany);
    }

    public void onRecentMoviesClick(ActionEvent actionEvent) {
    }

    public void onMaxRevClick(ActionEvent actionEvent) {
    }

    public void onTotalProfitClick(ActionEvent actionEvent) {
    }

    public void onLogOutClick(ActionEvent actionEvent)
    {
        main.showLogOutAlert(main.getStage());
    }

    public void onAddMovieClick(ActionEvent actionEvent) {
    }

    public void onTransferMovieClick(ActionEvent actionEvent) {
    }

    public void onSearchMovieClick(ActionEvent actionEvent) {
    }


}
