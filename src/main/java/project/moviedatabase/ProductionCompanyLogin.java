package project.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.moviedatabase.Main;

import java.io.IOException;
import java.io.Serializable;

public class ProductionCompanyLogin implements Serializable
{
    private static final long serialVersionUID = 0L;
    private Main main;
    @FXML
    public TextField titleTextField;
    @FXML
    public Button buttonLogIn;
    @FXML
    public Label labelLogIn;

    @FXML
    public void onLogInClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String title = titleTextField.getText();
/*
        try
        {
            main.getNetworkIO().write(productionCompany);
        } catch (IOException e) {
            e.printStackTrace();
        }
 */
        main.connectToServer(title);
    }

    public void setMain(Main main)
    {
        this.main = main;
    }
}
