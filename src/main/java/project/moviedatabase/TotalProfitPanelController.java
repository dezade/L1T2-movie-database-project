package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.Serializable;

public class TotalProfitPanelController implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    private ProductionCompany productionCompany;
    public Label headerLabel;
    public Button buttonGoBack;
    public Label totalProfit;

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void init() throws IOException, ClassNotFoundException {
        headerLabel.setText(headerLabel.getText() + " " + productionCompany.title + " :");
        totalProfit.setText(String.valueOf(productionCompany.getTotalProfit()));
    }
}
