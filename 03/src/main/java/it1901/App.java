package it1901;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	@Override
    public void start(final Stage primaryStage) throws Exception {
	    final Parent parent = FXMLLoader.load(getClass().getResource("MyMovies.fxml"));
	    primaryStage.setScene(new Scene(parent));
	    primaryStage.show();
//        primaryStage.setTitle("MyMovies");
//        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MyMovies.fxml"))));
//        primaryStage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}

