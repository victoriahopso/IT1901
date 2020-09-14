package it1901;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MyMoveisController {
	
	private ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	private ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic", "Action", "Thriller", "Sci-fi");
	
	
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
			//save details
			submitted();
		}
		else {
			message.setText("Please enter title, rating and genre before submitting");
		}
	}
	
	private boolean validTitle() {
		return (!title.getText().equals(""));
		//this method should later include more sufficient 
		//validation of a valid movie title. 
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
