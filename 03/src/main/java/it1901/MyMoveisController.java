package it1901;

import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MyMoveisController {
	
	private ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5","6");
	private ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic", "Action", "Thriller", "Sci-fi");
	private MyMovies movs = new MyMovies();
	
	@FXML
	private Button submit;
	@FXML
	private ComboBox<String> rating, genre;
	@FXML
	private Label message;
	@FXML
	private TextField title; 
	@FXML
	private TextArea comment; 
	
	 @FXML
		public void initialize() {
			genre.setItems(genres);
			rating.setItems(ratings);	
		}
     

	@FXML
	private void handleSubmit() {
		if (validTitle() && isRated() && genreChosen()) {
			Film film = new Film (title.getText(), genre.getValue(), Integer.valueOf(rating.getValue()), comment.getText());
            submitted();
            //creates new film that automaticly adds it self to a container, MyMovies. 
            //container content will be saved to file when program is exited. 
		}
		else {
			message.setText("Please enter title, rating and genre before submitting");
		}
    }
    
    //This method handles saving to file. 
    //At this iteration, loading from file is not yet implemented
    //so method might seem pointless.
    //will later expand with loading.
    @FXML
    private void exitApp(ActionEvent event) throws FileNotFoundException {
    	RWFile fil = new RWFile();
    	fil.fileWriter("boardObject.txt", movs);
        System.exit(0);
    }
	
	private boolean validTitle() {
		return (!title.getText().equals(""));
		//this method should later include more sufficient 
        //validation for a valid movie title. 
	}
	private boolean isRated() {
		return rating.getValue()!=null;
	}
	private boolean genreChosen() {
		return rating.getValue()!=null;
	}
	private void submitted() {
		genre.setValue(null);
		rating.setValue(null);
		message.setText("Movie added");
		title.setText(null);
		comment.setText(null);
	}
	
}
