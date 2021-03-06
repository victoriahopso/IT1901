package mymovies.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mymovies.core.Film;
import mymovies.core.User;

public class MyMoveisController {

  protected ObservableList<String> 
            ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
  protected ObservableList<String> 
            genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic",
            "Action", "Thriller", "Sci-fi");
  protected javafx.collections.ObservableList<Film> 
            moviesList = FXCollections.observableArrayList();
  protected TableView<Film> table;
  protected User user;
  protected UserAccess access;

  @FXML
  Button submit;
  @FXML
  ComboBox<String> rating;
  @FXML
  ComboBox<String> genre;
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
        title.textProperty().isEmpty()
        .or(genre.valueProperty().isNull()
        .or(rating.valueProperty().isNull())));
  }

  public void setUp(User user, UserAccess access) {
    this.user = user;
    this.access = access;
  }

  /**
     * Henter verdiene fra inputfeltene,lager et Film-objekt, og legger det til i
     * container-objektet myMovies. Skriver myMovies til jsonfil.
     */
  @FXML
  private void handleSubmit() {
    Film film = new Film(title.getText(), genre.getValue(), Integer.parseInt(rating.getValue()));
    user.addMovie(film);
    access.updateUser(user);
    submitted();
  }

  @FXML
  private void exitApp(ActionEvent event) {
    System.exit(0);
  }

  /**
   * Nullstiller input-felter etter en film er lagt til.
   */
  public void submitted() {
    genre.setValue(null);
    rating.setValue(null);
    title.setText(null);
  }

  /**
   * Lager et nytt vindu som viser fram brukerens filmer.
   * Filmene kan sorteres på tittel, sjanger og rating. 
   * Man kan også gjerne filmer.
   */
  @SuppressWarnings("unchecked")
  @FXML
  public void generateList() {
    moviesList.addAll(this.user.getMyMovies());
    table = new TableView<Film>();
    table.setId("table");

    Button ok = new Button("Close view");
    Button removeMovie = new Button("Delete selected");
    removeMovie.setId("removeMovie");
    ok.setId("ok");
    ok.setMaxWidth(122);
    ok.setFont(Font.font(17));

    Stage stage = new Stage();
    stage.setTitle("My movies");
    stage.setWidth(800);
    stage.setHeight(700);

    final Label label = new Label("Watched movies");
    label.setFont(new Font("Arial", 20));

    table.setEditable(true);

    TableColumn<Film, String> titleCol = new TableColumn<Film, String>("Title");
    titleCol.setId("Title");
    titleCol.setMinWidth(100);
    titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));

    TableColumn<Film, String> genreCol = new TableColumn<Film, String>("Genre");
    genreCol.setId("Genre");
    genreCol.setMinWidth(100);
    genreCol.setCellValueFactory(new PropertyValueFactory<Film, String>("genre"));

    TableColumn<Film, Integer> ratingCol = new TableColumn<Film, Integer>("Rating");
    ratingCol.setId("Rating");
    ratingCol.setMinWidth(200);
    ratingCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("rating"));

    table.setItems(moviesList);
    table.getColumns().addAll(titleCol, genreCol, ratingCol);
    table.setPrefSize(600, 500);

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(label, table, ok);
    final VBox vboxR = new VBox();
    vboxR.setSpacing(5);
    vboxR.setPadding(new Insets(10, 0, 0, 10));
    vboxR.getChildren().addAll(ok);

    GridPane pane = new GridPane();
    ColumnConstraints colConstraints = new ColumnConstraints();
    colConstraints.setHalignment(HPos.CENTER);
    pane.getColumnConstraints().add(colConstraints);
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(10);
    pane.setVgap(5);
    pane.addRow(0, vbox, removeMovie);
    pane.addRow(2, vboxR);

    Scene scene = new Scene(pane, 800, 700);
    stage.setScene(scene);
    stage.setTitle("Watched movies");
    stage.show();

    ok.setOnMouseClicked((MouseEvent event1) -> {
      stage.close();
      moviesList.clear();
    });

    removeMovie.setOnMouseClicked((MouseEvent event1) -> {
      user.removeMovie(table.getSelectionModel().getSelectedItem());
      access.updateUser(user);
      moviesList.remove(table.getSelectionModel().getSelectedItem());
    });
  }
}