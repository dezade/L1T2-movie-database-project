package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProductionCompanyLogin
{
    @FXML
    public TextField titleTextField;
    @FXML
    public Button buttonLogIn;
    @FXML
    public Label labelLogIn;

    @FXML
    public void onLogInClick(ActionEvent actionEvent)
    {
        String title = titleTextField.getText();

    }

}
