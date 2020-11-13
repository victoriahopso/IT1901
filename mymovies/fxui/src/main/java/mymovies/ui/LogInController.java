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
import mymovies.core.User;

public class LogInController {

    @FXML
    Button signIn, signUp;
    @FXML
    TextField siUsername, suUsername;
    @FXML
    PasswordField siPassword, suPassword, confPassword;

    UserAccess access = new RemoteUserAccess();
    User user;

    @FXML
    public void initialize() {
        signIn.disableProperty().bind(siUsername.textProperty().isEmpty().or(siPassword.textProperty().isEmpty()));

        signUp.disableProperty().bind(suUsername.textProperty().isEmpty()
                .or(suPassword.textProperty().isEmpty().or(confPassword.textProperty().isEmpty())));
    }

    /**
     * Hvis passordet er korrekt bekreftet, og brukernavnet ikke eksisterer fra før
     * opprettes en ny bruker. Metoden gir beskjed til API om å legge til en ny
     * bruker på serveren.
     * 
     * @param event Tar i mot event (fra #onAction), og sender dette videre til
     *              login() slik at ny fxml fil kan opperere på samme stage.
     * @throws IOException
     */
    @FXML
    public void handleSignUp(ActionEvent event) throws IOException {
        if (suPassword.getText().equals(confPassword.getText())) {
            if (!access.usernameTaken(suUsername.getText())) {
                User user2 = new User(suUsername.getText(), suPassword.getText());
                this.user = user2;
                System.out.println(user.getUserName());
                access.addUser(user);
                logIn(event);
            } else {
                logInFailour("Username is taken. Choose another username.");
                suUsername.setText(null);
            }
        } else {
            logInFailour("Make sure you type the correct password twice.");
            suPassword.setText(null);
            confPassword.setText(null);
        }
    }

    public void setAccess(UserAccess access) {
        this.access = access;
    }

    /**
     * Hvis inputtet brukernavn og passord matcher en eksisterende bruker logges
     * brukeren inn. Hvis ikke: feedback om ugyldig login
     * 
     * @param event Tar i mot event (fra #onAction), og sender dette videre til
     *              login() slik at ny fxml fil kan opperere på samme stage.
     * @throws IOException
     */
    @FXML
    public void handleSignIn(ActionEvent event) throws IOException {
        if (access.isUser(siUsername.getText(), siPassword.getText())) {
            this.user = access.getUser(siUsername.getText());
            logIn(event);
        } else {
            logInFailour("Username or password is incorrect");
            siPassword.setText(null);
        }
    }

    /**
     * Sender en bruker til "hoved-GUI", myMovies
     * 
     * @param event brukes for å få tak i Stage implementert av applikasjonens
     *              start-metode.
     * @throws IOException
     */
    @FXML
    public void logIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyMovies.fxml"));
        Stage myMoviesWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = (Parent) fxmlLoader.load();
        MyMoveisController controller = fxmlLoader.<MyMoveisController>getController();
        controller.setUp(user, access);
        Scene scene = new Scene(root);
        myMoviesWindow.setScene(scene);
        myMoviesWindow.show();
    }

    /**
     * Lager et pop-up vindu som viser frem en feilmelding for ugyldig
     * innloggingsinformasjon.
     * 
     * @param message teksten i pop-up vinduet settes til message, som varierer ut i
     *                fra type feil.
     */
    @FXML
    public void logInFailour(String message) {
        Stage stage = new Stage();
        Pane root = new Pane();
        Button ok = new Button("Ok");
        ok.setId("ok");
        Label text = new Label();
        ok.setScaleX(1);
        ok.setScaleY(1);
        ok.setLayoutX(131);
        ok.setLayoutY(125);
        text.setFont(Font.font(13));
        text.setLayoutX(10);
        text.setLayoutY(35);
        text.setScaleX(1);
        text.setScaleY(1);
        text.setText(message);
        root.getChildren().addAll(ok, text);
        stage.setScene(new Scene(root, 340, 166));
        stage.setTitle("Invalid user information");
        stage.show();

        // om man trykker på knappen "ok", lukkes vinduet
        ok.setOnMouseClicked((MouseEvent event1) -> {
            stage.close();
        });
    }

}