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
import java.io.PipedReader;
import java.io.Serializable;
import java.util.List;

import static javafx.application.Application.launch;

public class Main extends Application implements Serializable
{
    private static final long serialVersionUID = 0L;
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
            if(ss.equalsIgnoreCase("Error! Credentials do not match!"))
            {
                showIncorrectCredentialAlert("Log In Error!");
            }
        }
        else if(obj instanceof ProductionCompany)
        {
            ProductionCompany productionCompany = (ProductionCompany) obj;
            productionCompany.networkIO = pcNetworkIO;
            //productionCompany.readThread = new ProductionCompanyRead(this, productionCompany);
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

        /*
        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });

         */
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

    public void showMovieList(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
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
        stage.setScene(new Scene(root, 800, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showAddNewMovie(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddNewMovie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddNewMovieController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Adding a new movie");
        stage.setScene(new Scene(root, 640, 365));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showNewMoviePanel(String input, ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewMoviePanel.fxml"));
        Parent root = loader.load();

        // Loading the controller
        NewMoviePanelController controller = loader.getController();
        controller.setMain(this);
        controller.movieTitle.setText(input);
        controller.prodCompTitle.setText(productionCompany.title);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Information for New Movie");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showTransferMovie(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TransferMovie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TransferMovieController controller = loader.getController();
        controller.setMain(this);
        controller.init(productionCompany);

        // Set the primary stage
        stage.setTitle("Transfer a movie");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showTransferPanel(String movieTitle, ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TransferMoviePanel.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TransferMoviePanelController controller = loader.getController();
        controller.setMain(this);
        controller.setTarget(movieTitle);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Transferring a movie");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showTotalProfitPanel(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TotalProfitPanel.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TotalProfitPanelController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);
        controller.init();

        // Set the primary stage
        stage.setTitle(productionCompany.title + " - Total  Profit");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showSearchMovieOptions(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchOptions.fxml"));
        //loader.setRoot(new AnchorPane());
        Parent root = loader.load();

        // Loading the controller
        SearchOptionsController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle(productionCompany.title + " - Searching options");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showYearOfReleaseInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("YearOfReleaseInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        YearOfReleaseInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Year of Release");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showGenreInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GenreInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        GenreInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Genre");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showRunningTimeInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RunningTimeInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        RunningTimeInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Running Time");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showProfitInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfitInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ProfitInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Profit");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showBudgetInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BudgetInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        BudgetInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Budget");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }
    public void showRevenueInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RevenueInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        RevenueInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Revenue");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }
    public void showTitleInput(ProductionCompany productionCompany) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TitleInput.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TitleInputController controller = loader.getController();
        controller.setMain(this);
        controller.setProductionCompany(productionCompany);

        // Set the primary stage
        stage.setTitle("Search by Title");
        stage.setScene(new Scene(root, 772, 400));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showSearchResultList(List<Movie> movieList, ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchResultList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ///////loader.setController(new MovieListController(productionCompany));
        SearchResultListController controller = loader.getController();
        controller.setMain(this);
        controller.init(movieList, productionCompany);

        // Set the primary stage
        stage.setTitle("Search Results");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }


    public void showRecentMoviesList(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RecentMoviesList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ///////loader.setController(new MovieListController(productionCompany));
        RecentMoviesListController controller = loader.getController();
        controller.setMain(this);
        controller.init(productionCompany);

        // Set the primary stage
        stage.setTitle(productionCompany.title + " - Most Recent Movies");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showMaxRevMoviesList(ProductionCompany productionCompany) throws IOException, ClassNotFoundException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaxRevMoviesList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MaxRevMoviesListController controller = loader.getController();
        controller.setMain(this);
        controller.init(productionCompany);

        // Set the primary stage
        stage.setTitle(productionCompany.title + " - Movies with Maximum Revenue");
        stage.setScene(new Scene(root, 640, 720));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            showLogOutAlert(stage);
        });
    }

    public void showIncorrectCredentialAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message);
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The provided production company is not in the server.");
        alert.showAndWait();
    }

    public void wrongSearchInputAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Search failed!");
        alert.setHeaderText("No such movie found.");
        alert.setContentText("The provided " + message + "does not match any movie for this production company.");
        alert.showAndWait();
    }

    public void showNewMovieExistsAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Action failed!");
        alert.setHeaderText("Could not add new movie!");
        alert.setContentText("A movie with this name already exists in the server. Please input a valid name.");
        alert.showAndWait();
    }

    public void showAddMovieConfirmationAlert(String title)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("The movie " + '"' + title + '"' + " has been successfully added to the server.");
        alert.setContentText("Please click on \"OK\" to return to homepage.");
        alert.showAndWait();
    }

    public void showTransferMovieConfirmationAlert(String title)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("The movie " + '"' + title + '"' + " has been successfully transferred.");
        alert.setContentText("Please click on \"OK\" to return to homepage.");
        alert.showAndWait();
    }

    public void showFieldEmptyAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Action failed!");
        alert.setHeaderText("Could not add new movie!");
        alert.setContentText("At least one required field is empty. Please fill up all required field.");
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
