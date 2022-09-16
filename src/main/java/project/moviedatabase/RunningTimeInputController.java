package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RunningTimeInputController implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Label headerLabel1;
    public TextField inputTextFieldLow;
    public Label headerLabel2;
    public TextField inputTextFieldHigh;
    private Main main;
    private ProductionCompany productionCompany;
    public Button buttonGoBack;
    public Button buttonConfirm;

    public int inputLow, inputHigh;

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
        inputLow = Integer.parseInt(inputTextFieldLow.getText());
        inputHigh = Integer.parseInt(inputTextFieldHigh.getText());
        List<Movie> result = new ArrayList<>();
        for(Movie m : productionCompany.movies)
        {
            if(m.getRunningTime() >= inputLow && m.getRunningTime() <= inputHigh)
            {
                result.add(m);
            }
        }
        if(result.isEmpty())
        {
            main.wrongSearchInputAlert("range of running time");
        }
        else
        {
            main.showSearchResultList(result, productionCompany);
        }
    }
}
