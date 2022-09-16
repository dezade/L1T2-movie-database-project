package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.MovieWrapper;
import util.StringPair;

import java.io.IOException;
import java.io.Serializable;

public class TransferMoviePanelController implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    private ProductionCompany productionCompany;
    private String target;
    public Button buttonCancel;
    public Button buttonConfirm;
    public TextField textFieldRecipientProdComp;

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void onCancelClick(ActionEvent actionEvent) throws IOException {
        main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
    }

    public void onConfirmClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String prodCompName = textFieldRecipientProdComp.getText();
        productionCompany.networkIO.write(new StringPair("ProdComp", prodCompName));
        Object obj = productionCompany.networkIO.read();
        if(obj instanceof ProductionCompany)
        {
            ProductionCompany recipient = (ProductionCompany) obj;
            Movie movie = new Movie();
            for(Movie m : this.productionCompany.movies)
            {
                if(m.getTitle().equalsIgnoreCase(target))
                {
                    movie = m;
                    break;
                }
            }
            this.productionCompany.networkIO.write(new MovieWrapper(movie, this.productionCompany, recipient));
            main.showTransferMovieConfirmationAlert(target);
            main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
        }
        else if(obj instanceof String)
        {
            main.showIncorrectCredentialAlert("Action failed!");
        }
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setTarget(String input)
    {
        this.target = input;
    }
}
