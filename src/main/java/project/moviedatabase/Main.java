package project.moviedatabase;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.NetworkIO;

import java.io.IOException;
import java.io.Serializable;

import static javafx.application.Application.launch;

public class Main extends Application implements Serializable
{
    private Stage stage;
    public String serverAddress = "127.0.0.1";
    public int serverPort = 42069;
    Stage getStage() { return stage; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showLoginPage();
    }

    void connectToServer(String title) throws IOException, ClassNotFoundException
    {
        NetworkIO pcNetworkIO = new NetworkIO(serverAddress, serverPort);
        pcNetworkIO.write(title);
        Object obj = pcNetworkIO.read();
        if(obj instanceof String)
        {
            String ss = (String) obj;
            System.out.println(ss);
        }
        else if(obj instanceof ProductionCompany)
        {
            ProductionCompany productionCompany = (ProductionCompany) obj;
            showProductionCompanyHomepage(productionCompany, pcNetworkIO);
        }
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductionCompanyLogin.fxml"));
        //loader.setRoot(new AnchorPane());
        Parent root = loader.load();

        // Loading the controller
        ProductionCompanyLogin controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 720, 640));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showProductionCompanyHomepage(ProductionCompany productionCompany, NetworkIO networkIO) throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Homepage.fxml"));
        //loader.setRoot(new AnchorPane());
        Parent root = loader.load();

        // Loading the controller
        HomepageController controller = loader.getController();
        controller.setMain(this);
        String title = productionCompany.title;
        controller.setProductionCompany(productionCompany);
        controller.prodCompLabel.setText(title);
        controller.setNetworkIO(networkIO);

        // Set the primary stage
        stage.setTitle(title + " - Homepage");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showMovieList(ProductionCompany productionCompany) throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MovieList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ///////loader.setController(new MovieListController(productionCompany));
        MovieListController controller = loader.getController();
        controller.setMain(this);
        controller.init(productionCompany);

        // Set the primary stage
        stage.setTitle(productionCompany.title + " - List of Movies");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showMovie(String title, ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Movie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ShowMovieController controller = loader.getController();
        controller.setMain(this);
        controller.init(title, productionCompany);

        // Set the primary stage
        stage.setTitle(title);
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }
/*
    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
*/
    public void showIncorrectCredentialAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showLogOutAlert(Stage stage)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("You are about to log out!");
        alert.setContentText("Do you want to log out? Your changes will be permanently saved in the server.");

        if(alert.showAndWait().get() == ButtonType.OK)
        {
            System.out.println("Logged out successfully!");
            //Save file?
            stage.close();
        }
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
