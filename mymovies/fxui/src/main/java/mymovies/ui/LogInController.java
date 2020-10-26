package mymovies.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mymovies.core.AllUsers;
import mymovies.core.User;

public class LogInController {

    @FXML
    Button signIn, signUp;
    @FXML
    TextField siUsername, suUsername;
    @FXML
    PasswordField siPassword, suPassword, confPassword;

    protected Boolean check = true;

    private AllUsers all = new AllUsers(); // DENNE MÅ LASTES INN FRA SKYEN SLIK AT DEN ER STATISK!!

    @FXML
    public void initialize() {
        signIn.disableProperty().bind(siUsername.textProperty().isEmpty()
                .or(siPassword.textProperty().isEmpty()));

        signUp.disableProperty().bind(suUsername.textProperty().isEmpty()
                .or(suPassword.textProperty().isEmpty()
                .or(confPassword.textProperty().isEmpty())));
    }

    @FXML
    public void handleSignUp(ActionEvent event) throws IOException {
        if (suPassword.getText().equals(confPassword.getText())) {
            User user = new User(suUsername.getText(), suPassword.getText());
            //User user2 = all.getUser(suUsername.getText(), suPassword.getText());
            for (User users : all.getAllUsers()) {
                if (user.getUserName().equals(users.getUserName())) {
                    check = false;
                }
            }
            if (check) {
                all.addUser(user);
                logIn(event, user);
            }
            else {
                logInFailour("Username is taken. Choose another username.");
                suUsername.setText(null);
            }
        }
        else {
            logInFailour("Make sure you type the correct password twice.");
            suPassword.setText(null);
            confPassword.setText(null);
        }
    }
    @FXML
    public void handleSignIn(ActionEvent event) throws IOException {
        User user = all.getUser(siUsername.getText(), siPassword.getText());
        if (!((user.getUserName().equals("")) && (user.getPassword().equals("")))) {
            logIn(event, user);
        }
        else {
            logInFailour("Username or password is incorrect");
            siPassword.setText(null);
        }
    }

    /**
     * Lunches main-gui, myMovies
     * @param event used to get the stage implemented by application start-method
     * @throws IOException
     */
    @FXML
    public void logIn(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent myMoviesParent = FXMLLoader.load(getClass().getResource("MyMovies.fxml"));
        Scene myMoviesScene = new Scene(myMoviesParent);
        Stage myMoviesWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

        MyMoveisController mmc = loader.getController(); 
        mmc.setUser(user); 

        myMoviesWindow.setScene(myMoviesScene);
        myMoviesWindow.show();
    }

    /**
     * Creates a pop-up window to display error messages for invalid 
     * user input in "sign up" and "sign in"
     * @param message Text in pop-up set to the string contained in message
     */
    @FXML
    public void logInFailour(String message) {
        Stage stage = new Stage();
        Pane root = new Pane();
        Button ok = new Button("Ok");
        ok.setId("ok");
        Label tekst = new Label();
        ok.setScaleX(1);
        ok.setScaleY(1);
        ok.setLayoutX(131);
        ok.setLayoutY(125);
        tekst.setFont(Font.font(13));
        tekst.setLayoutX(10);
        tekst.setLayoutY(35);
        tekst.setScaleX(1);
        tekst.setScaleY(1);
        tekst.setText(message);
        root.getChildren().addAll(ok, tekst);
        stage.setScene(new Scene(root, 340, 166));
        stage.setTitle("Invalid user information");
        stage.show();

        //om man trykker på knappen "ok", lukkes vinduet
        ok.setOnMouseClicked((MouseEvent event1) -> {
            stage.close();
        });
    }


}