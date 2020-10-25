package mymovies.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyMoviesRemoteApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent parent = FXMLLoader.load(getClass().getResource("MyMoviesRemoteApp.fxml"));
    stage.setScene(new Scene(parent));
    stage.show();
  }

  public static void main(String[] args) {
    launch(MyMoviesRemoteApp.class, args);
  }
}
