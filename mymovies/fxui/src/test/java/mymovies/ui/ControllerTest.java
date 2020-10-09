package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mymovies.core.Film;
import mymovies.core.MyMovies;

public class ControllerTest extends ApplicationTest {


    MyMoveisController controller; 


    @Override
    public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("MyMovies.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }
    @BeforeEach
    public void setUp(){
        

    }
    @Test
    public void testController(){
        assertNotNull(controller);
        assertNotNull(controller.getMyMovies());
    }

    

    @Test
    public void testInitialize(){
        //testSubmitted();
        //assertEquals(controller.ratings, controller.rating.getItems());
        //assertEquals(controller.genres, controller.genre.getItems());
        }

    @Test
    public void testHandleSubmit(){
        
        clickOn("#title").write("Yolo");
        //controller.genre.setValue("Horror");
        //controller.rating.setValue("2");
        clickOn("#genre").clickOn("#Horror");
        clickOn("#rating").clickOn("#2");
        clickOn("#submit");
        assertTrue(!controller.getMyMovies().isEmpty());
        assertEquals("Yolo", controller.getMyMovies().iterator().next().getName());
        

    //    assertTrue(controller.submit.isDisabled());
    //    assertNull(controller.rating.getValue());
    //    assertNull(controller.rating.getValue());
    }
    @Test
    public void testResumeSession(){

        //ORDNE SLIK AT DENNE HENTER FRA testMymovies.json DER DISSE FILMENE
        //LIKKER STATISK

        //Film film1 = new Film("film1", "genre", 4);
        //Film film2 = new Film("film2", "genre", 3);
        //Collection<Film> comparison = new ArrayList<Film>();
        //comparison.add(film1);
        //comparison.add(film2);
        //controller.resumeSession();
        //assertEquals(comparison, controller.myMovies);
        //assertEquals(film1.toString()+"\n"+film2.toString()+"\n", controller.heltekst);

        //before: skriv en rekke filmer.
        //sjekk at mymovies inneholder alle disse. 
        //sjekk at heltekst er lik film.tostring+\n for alle filmer
    }
    @Test
    public void testsubmitted(){
        controller.submitted();
        assertNull(controller.genre.getValue());
        assertNull(controller.rating.getValue());
        assertNull(controller.title.getText());
        

        //sjekk at det blir laget en film
        //sjekk at filmen legges til i mymovies med get 
        //sjekk at noe blir delegert til persistens 
    }
    @Test
    public void testGenerateList(){
        //sjekk at tekst settes til heltekst
        //sjekk at heltekst settes til "" når du trykker ok-knappen
        //sjekk at ok knappen avsutter vinduet 
    }



}