package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class YearOfReleaseInputController implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    private ProductionCompany productionCompany;
    public Label headerLabel;
    public Button buttonGoBack;
    public TextField inputTextField;
    public Button buttonConfirm;

    public int input;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        main.showSearchMovieOptions(productionCompany);
    }

    public void onConfirmClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        input = Integer.parseInt(inputTextField.getText());
        List<Movie> result = new ArrayList<>();
        for(Movie m : productionCompany.movies)
        {
            if(m.getYearOfRelease() == input)
            {
                result.add(m);
            }
        }
        if(result.isEmpty())
        {
            main.wrongSearchInputAlert("year of release");
        }
        else
        {
            main.showSearchResultList(result, productionCompany);
        }
    }
}
