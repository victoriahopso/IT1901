package mymovies.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

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
import mymovies.core.RW;

public class MyMoveisController {

    protected ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    protected ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic", "Action",
            "Thriller", "Sci-fi");
    protected String heltekst = "";
    protected MoviesPersistence persistence = new MoviesPersistence();
    protected MyMovies myMovies = new MyMovies();
    protected RW rw = new RW();

    @FXML
    Button submit;
    @FXML
    ComboBox<String> rating, genre;
    @FXML
    Label message;
    @FXML
    TextField title;
    @FXML 
    Button showMovies;
    @FXML
    String endpointUri;

    /**
     * Fyller comboBoxene med verdiene fra ratings og genres.
     * Sørger for at {@link #submit} er disabled 
     * så lenge ikke alle 3 inputfeltene er fyllt ut.
     */
    @FXML
    public void initialize() {
        genre.setItems(genres);
        rating.setItems(ratings);
        submit.disableProperty().bind(
        title.textProperty().isEmpty()
        .or(
        genre.valueProperty().isNull()
        .or(
        rating.valueProperty().isNull())
        ));

        //sett endpointUri, og koble til interface
    }

    /**
     * Henter verdiene fra inputfeltene,lager et Film-objekt, 
     * og legger det til i container-objektet myMovies. 
     * Skriver myMovies til jsonfil.
     */
    @FXML
    private void handleSubmit() {
        Film film = new Film(title.getText(), genre.getValue(), Integer.parseInt(rating.getValue()));
        myMovies.addMovie(film);
        persistence.write(myMovies, rw.createWriter("./mymovies.json"));
        submitted();
    }


    @FXML
    protected void resumeSession() {
        //Endrer myMovies-objektet til det som ligger i json-fil
        myMovies = persistence.read(rw.createReader("./mymovies.json"));
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

    public void submitted() {
        genre.setValue(null);
        rating.setValue(null);
        title.setText(null);
    }

    /**
     * Lager et nytt vindu som viser informasjonen om filmene
     */
    @FXML
    public void generateList(ActionEvent event) {
        resumeSession();
        Stage stage = new Stage();
        Pane root = new Pane();
        Button ok = new Button("Ok");
        ok.setId("ok");
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
        stage.setTitle("Watched movies");
        stage.show();

        //om man trykker på knappen "ok", forsvinner
        ok.setOnMouseClicked((MouseEvent event1) -> {
            heltekst ="";
            stage.close();
        });
    }

    //For test purposes
    protected Collection<Film> getMyMovies(){
        Collection<Film> myMoviesCopy = new ArrayList<>();
        for (Film film : myMovies){
            myMoviesCopy.add(film);
        }
        return myMoviesCopy;
    }
}