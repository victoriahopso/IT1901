package mymovies.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mymovies.core.Film;
import mymovies.core.User;

public class MyMoveisController {
    protected ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    protected ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic",
            "Action", "Thriller", "Sci-fi");
    User user;
    RemoteUserAccess access;

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

    /**
     * Fyller comboBoxene med verdiene fra ratings og genres. Sørger for at
     * {@link #submit} er disabled så lenge ikke alle 3 inputfeltene er fyllt ut.
     */
    @FXML
    public void initialize() {
        genre.setItems(genres);
        rating.setItems(ratings);
        submit.disableProperty().bind(
                title.textProperty().isEmpty().or(genre.valueProperty().isNull().or(rating.valueProperty().isNull())));
    }

    public void setUp(User user, RemoteUserAccess access) {
        this.user = user;
        this.access = access;
    }

    /**
     * Henter verdiene fra inputfeltene,lager et Film-objekt, og legger det til i
     * container-objektet myMovies. Skriver myMovies til jsonfil.
     */
    @FXML
    private void handleSubmit() {
        System.out.println(this.user);
        System.out.println(this.user.getUserName());
        Film film = new Film(title.getText(), genre.getValue(), Integer.parseInt(rating.getValue()));
        user.addMovie(film);
        access.updateUser(user);
        submitted();
    }

    // hva er greia med denne?
    // KAST?
    @FXML
    private void exitApp(ActionEvent event) throws FileNotFoundException {
        System.exit(0);
    }

    private void submitted() {
        genre.setValue(null);
        rating.setValue(null);
        title.setText(null);
    }

    /**
     * Lager et nytt vindu som viser informasjonen om filmene
     */
    // BEHOLD
    @FXML
    public void generateList(ActionEvent event) {

        Stage stage = new Stage();
        Button ok = new Button("Close view");
        Label lbl = new Label("Movie ratings:");
        Button removeMovie = new Button("Delete selected");
        ok.setId("ok");
        ok.setMaxWidth(122);
        ok.setFont(Font.font(17));
        removeMovie.setId("removeMovie");

        Collection<String> displayTexts = new ArrayList<>();
        user.getMyMovies().forEach(p -> displayTexts.add(p.displayText()));
        javafx.collections.ObservableList<String> moviesList = FXCollections.observableArrayList(displayTexts);

        ListView<String> moviesLW = new ListView<String>();
        moviesLW.setItems(moviesList);
        moviesLW.setOrientation(Orientation.VERTICAL);
        moviesLW.setPrefSize(600, 500);

        VBox moviesSelection = new VBox();
        moviesSelection.setSpacing(10);
        moviesSelection.getChildren().addAll(lbl, moviesLW);
        VBox lower = new VBox();
        lower.getChildren().addAll(ok);

        GridPane pane = new GridPane();
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(colConstraints);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0, moviesSelection, removeMovie);
        pane.addRow(2, lower);

        Scene scene = new Scene(pane, 800, 700);
        stage.setScene(scene);
        stage.setTitle("Watched movies");
        stage.show();

        ok.setOnMouseClicked((MouseEvent event1) -> {
            stage.close();
        });

        removeMovie.setOnMouseClicked((MouseEvent event1) -> {
            String removable = moviesLW.getSelectionModel().getSelectedItem();
            removeFilmAssist(removable);
            moviesList.remove(removable);
            access.updateUser(user);
        });

    }

    private void removeFilmAssist(String removable) {
        for (Film film : user.getMyMovies()) {
            if (removable.equals(film.displayText())) {
                user.removeMovie(film);
            }
        }
    }
    

    // For test purposes
    protected Collection<Film> getMyMovies() {
        Collection<Film> myMoviesCopy = new ArrayList<>();
        for (Film film : user.getMyMovies()) {
            myMoviesCopy.add(film);
        }
        return myMoviesCopy;
    }
}