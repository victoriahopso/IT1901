package it1901;


import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyMoveisController {
	
	private ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5","6");
	private ObservableList<String> genres = FXCollections.observableArrayList("Horror", "Comedy", "Romantic", "Action", "Thriller", "Sci-fi");
	private String heltekst = "";
	
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
            RWFile fil = new RWFile();
            String streng =title.getText()+", "+genre.getValue()+", "+rating.getValue()+", "+comment.getText();
            fil.save(streng);
            submitted();
		}
		else {
			message.setText("Please enter title, rating and genre before submitting");
		}
    }

    @FXML
    private void resumeSession(){
        RWFile fil = new RWFile();
        heltekst = fil.load();
        
    }

   
    
    //This method handles saving to file. 
    //At this iteration, loading from file is not yet implemented
    //so method might seem pointless.
    //will later expand with loading.
    @FXML
    private void exitApp(ActionEvent event) throws FileNotFoundException {
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
	
	//This method generates a list with
	//every movie the user has seen and rated
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
		
		ok.setOnMouseClicked((MouseEvent event1) -> {
			stage.close();
		});
	}
}