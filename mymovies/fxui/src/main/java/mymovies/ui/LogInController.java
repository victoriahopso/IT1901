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
    Button signIn;
  @FXML
    Button signUp;
  @FXML
    TextField siUsername;
  @FXML
    TextField suUsername;
  @FXML
    PasswordField siPassword;
  @FXML
    PasswordField suPassword;
  @FXML
    PasswordField confPassword;

  UserAccess access = new RemoteUserAccess();
  User user;

  /**
   * Sørger for at knappene signIn og signUp er disabled så lenge ikke ikke
   * alle feltene er fyllt inn.
   */
  @FXML
    public void initialize() {
    signIn.disableProperty().bind(siUsername.textProperty().isEmpty()
          .or(siPassword.textProperty().isEmpty()));
    signUp.disableProperty().bind(suUsername.textProperty().isEmpty()
          .or(suPassword.textProperty().isEmpty().or(confPassword.textProperty().isEmpty())));
  }

  /**
    * Hvis passordet er korrekt bekreftet, og brukernavnet ikke eksisterer fra før
    * opprettes en ny bruker. Metoden gir beskjed til API om å legge til en ny
    * bruker på serveren.

    * @param event
    *     Tar i mot event (fra #onAction), og sender dette videre til
    *     login() slik at ny fxml fil kan opperere på samme stage.
    * @throws IOException
    *     Kaster unntak hvis event er null (via logIn)
    */
  @FXML
    public void handleSignUp(ActionEvent event) throws IOException {
    if (suUsername.getText().length() >= 2) {  
      if (suPassword.getText().equals(confPassword.getText())) {
        if (suPassword.getText().length() >= 8) {
          if (!access.usernameTaken(suUsername.getText())) {
            User user2 = new User(suUsername.getText(), suPassword.getText());
            this.user = user2;
            access.addUser(user);
            logIn(event);
          } else {
            logInFailour("Username is taken. Choose another username.");
            suUsername.setText(null);
          }
        } else {
          logInFailour("Password must contain 8 or more characters");
          suPassword.setText(null);
          confPassword.setText(null);
        }
      } else {
        logInFailour("Make sure you type the correct password twice.");
        suPassword.setText(null);
        confPassword.setText(null);
      }
    } else {
      logInFailour("Username must be of 2 characters or more");
      suUsername.setText(null);
    }
  }

  public void setAccess(UserAccess access) {
    this.access = access;
  }

  /**
    * Hvis inputtet brukernavn og passord matcher en eksisterende bruker logges
    * brukeren inn. Hvis ikke: feedback om ugyldig login

    * @param event
    *     Tar i mot event (fra #onAction), og sender dette videre til
    *     login() slik at ny fxml fil kan opperere på samme stage.
    * @throws IOException
    *     Kaster unntak hvis event er null (via logIn).
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
    * Sender en bruker til "hoved-GUI", myMovies.

    * @param event
    *     brukes for å få tak i Stage implementert av applikasjonens
    *     start-metode.
    * @throws IOException
    *     Kaster unntak hvis sxmlLoader ikke klarer å loade.
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

    * @param message
    *     parameter: teksten i pop-up vinduet settes til message, som varierer ut i
    *     fra type feil.
    */
  @FXML
    public void logInFailour(String message) {
    Button ok = new Button("Ok");
    ok.setId("ok");
    ok.setScaleX(1);
    ok.setScaleY(1);
    ok.setLayoutX(131);
    ok.setLayoutY(125);
    Label text = new Label();
    text.setFont(Font.font(13));
    text.setLayoutX(10);
    text.setLayoutY(35);
    text.setScaleX(1);
    text.setScaleY(1);
    text.setText(message);
    Pane root = new Pane();
    root.getChildren().addAll(ok, text);
    Stage stage = new Stage();
    stage.setScene(new Scene(root, 340, 166));
    stage.setTitle("Invalid user information");
    stage.show();

    // om man trykker på knappen "ok", lukkes vinduet
    ok.setOnMouseClicked((MouseEvent event1) -> {
      stage.close();
    });
  }

}