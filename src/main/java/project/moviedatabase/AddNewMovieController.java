package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.StringPair;

import java.io.IOException;
import java.io.Serializable;

public class AddNewMovieController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public TextField newMovieNameField;
    private Main main;
    public Button buttonCancel;
    public Button buttonContinue;
    private ProductionCompany productionCompany;
    private String input;

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void onCancelClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }

    public void onContinueClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        input = newMovieNameField.getText();
        productionCompany.networkIO.write(new StringPair("new", input));
        Object obj = productionCompany.networkIO.read();
        if(obj instanceof Boolean)
        {
            Boolean exists = (Boolean) obj;
            if(exists)
                main.showNewMovieExistsAlert();
            else
                main.showNewMoviePanel(input, productionCompany);
        }
    }
}
