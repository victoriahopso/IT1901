package mymovies.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mymovies.core.Film;
import mymovies.core.MyMovies;
import mymovies.json.MoviesPersistence;

public class MyMoveisController {

    private ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    private ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic", "Action",
            "Thriller", "Sci-fi");
    private String heltekst = "";
    private MoviesPersistence persistence = new MoviesPersistence();
    private MyMovies myMovies = new MyMovies();

    @FXML
    private Button submit;
    @FXML
    private ComboBox<String> rating, genre;
    @FXML
    private Label message;
    @FXML
    private TextField title;
    @FXML 
    private Button showMovies;

    @FXML
    public void initialize() {
        genre.setItems(genres);
        rating.setItems(ratings);
    }

    /**
     * Funksjon som kjører når man legger til en film
     */
    @FXML
    private void handleSubmit() {
        if (validTitle() && isRated() && genreChosen()) {
            //lager et nytt film-objekt med input fra bruker
            Film film = new Film(title.getText(), genre.getValue(), Integer.parseInt(rating.getValue()));
            //legger filmen til i container-klassen
            myMovies.addMovie(film);
            //skriver container-klassen "myMovies" til json.fil
            try  {
                FileOutputStream fileStream = new FileOutputStream("mymovies.json");
                OutputStreamWriter writer = new OutputStreamWriter(fileStream,"UTF-8");
                persistence.write(myMovies, writer);
                submitted();
            }
            catch (IOException e){
                System.out.println(e);
            }
        } 
        else {
            message.setText("Please enter title, rating and genre before submitting");
        }
    }

    /**
     * Henter tidligere tilstand fra fil og endrer myMovies-objektet til dette
     */
    @FXML
    private void resumeSession() {
        try {
            InputStream inputStream = new FileInputStream("mymovies.json"); 				
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            //Endrer myMovies-objektet til det som ligger i json-fil
            myMovies = persistence.read(reader);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        //bruker toString til å lage en streng med alle filmene på fint
        //format, om lista "myMovies" ikke er tom
        if (!myMovies.getFilmer().isEmpty()){
            for (Film film: myMovies){
                heltekst += film.toString()+"\n";  
            }
        }
    }

    @FXML
    private void exitApp(ActionEvent event) throws FileNotFoundException {
        System.exit(0);
    }

    private boolean validTitle() {
        return (!title.getText().equals(""));
    }

    private boolean isRated() {
        return rating.getValue() != null;
    }

    private boolean genreChosen() {
        return rating.getValue() != null;
    }

    /**
     * Endrer tekst i begge combobokser og tekstfelt, etter
     * man har lagt til film
     */
    private void submitted() {
        genre.setPromptText("Genre");
        rating.setPromptText("Rating");
        message.setText("Movie added");
        title.setText(null);
    }

    /**
     * Lager et nytt vindu som viser informasjonen om filmene
     */
    @FXML
    private void generateList(ActionEvent event) {
        resumeSession();
        Stage stage = new Stage();
        Pane root = new Pane();
        Button ok = new Button("Ok");
        Label tekst = new Label();
        tekst.setText(null);
        ok.setScaleX(2);
        ok.setScaleY(2);
        ok.setLayoutX(400);
        ok.setLayoutY(500);
        tekst.setFont(Font.font(20));
        tekst.setLayoutX(0);
        tekst.setLayoutY(0);
        tekst.setText(heltekst);
        root.getChildren().addAll(ok, tekst);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        //om man trykker på knappen "ok", forsvinner
        ok.setOnMouseClicked((MouseEvent event1) -> {
            heltekst ="";
            stage.close();
        });
    }
}