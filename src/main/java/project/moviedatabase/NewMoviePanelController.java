package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MovieWrapper;

import java.io.IOException;
import java.io.Serializable;

public class NewMoviePanelController implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    private ProductionCompany productionCompany;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public Label movieTitle;
    public Label prodCompTitle;
    public TextField textYearOfRelease;
    public TextField textGenre1;
    public TextField textGenre2;
    public TextField textGenre3;
    public TextField textRunningTime;
    public TextField textBudget;
    public TextField textRevenue;
    public Button buttonCancel;
    public Button buttonConfirm;

    public void onCancelClick(ActionEvent actionEvent) throws IOException
    {
        main.showAddNewMovie(productionCompany);
    }

    public void onConfirmClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if(textYearOfRelease.getText() == null | textGenre1.getText() == null | textRunningTime.getText() == null | textBudget.getText() == null | textRevenue.getText() == null)
        {
            main.showFieldEmptyAlert();
        }
        else
        {
            Movie m = new Movie();
            m.setTitle(movieTitle.getText());
            m.setProductionCompany(productionCompany.title);
            m.setYearOfRelease(Integer.parseInt(textYearOfRelease.getText()));
            String[] gen = new String[3];
            gen[0] = textGenre1.getText();
            gen[1] = textGenre2.getText();
            gen[2] = textGenre3.getText();
            m.setGenres(gen);
            m.setRunningTime(Integer.parseInt(textRunningTime.getText()));
            m.setBudget(Long.parseLong(textBudget.getText()));
            m.setRevenue(Long.parseLong(textRevenue.getText()));
            m.setProfit();
            productionCompany.addMovie(m);
            productionCompany.networkIO.write(new MovieWrapper(m, productionCompany));
            main.showAddMovieConfirmationAlert(m.getTitle());
            main.showProductionCompanyHomepage(productionCompany, productionCompany.networkIO);
        }
    }
}
