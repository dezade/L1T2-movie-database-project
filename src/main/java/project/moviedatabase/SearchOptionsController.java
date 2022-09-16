package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.Serializable;

public class SearchOptionsController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Button buttonYearOfRelease;
    public Button buttonGenre;
    public Button buttonRunningTIme;
    public Button buttonProfit;
    public Button buttonBudget;
    public Button buttonRevenue;
    public Button buttonSearchByTitle;
    public Button buttonGoBack;
    private Main main;
    private ProductionCompany productionCompany;

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany)
    {
        this.productionCompany = productionCompany;
    }

    public void onSearchByTitleClick(ActionEvent actionEvent) throws IOException {
        main.showTitleInput(productionCompany);
    }
    public void onYearOfReleaseClick(ActionEvent actionEvent) throws IOException {
        main.showYearOfReleaseInput(productionCompany);
    }

    public void onGenreClick(ActionEvent actionEvent) throws IOException {
        main.showGenreInput(productionCompany);
    }

    public void onRunningTimeClick(ActionEvent actionEvent) throws IOException {
        main.showRunningTimeInput(productionCompany);
    }

    public void onProfitClick(ActionEvent actionEvent) throws IOException {
        main.showProfitInput(productionCompany);
    }

    public void onBudgetClick(ActionEvent actionEvent) throws IOException {
        main.showBudgetInput(productionCompany);
    }

    public void onRevenueClick(ActionEvent actionEvent) throws IOException {
        main.showRevenueInput(productionCompany);
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }
}
