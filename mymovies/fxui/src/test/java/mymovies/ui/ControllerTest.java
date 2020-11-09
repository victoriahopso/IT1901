package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.User;

public class ControllerTest extends ApplicationTest {
    
    MyMoveisController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("MyMovies.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
        User user = new User("brukernavn","passord");
        UserAccess access = new MockUserAccess();
        controller.setUp(user, access);
    }

    /*
     * Fjerner "gamle tilstander" etter hver test, slik at ingenting henger
     * ignen til neste test.
     * @throws Exception
     */
    @AfterEach
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testContructor() {
        assertNotNull(this.controller);
        assertNotNull(this.controller.user);
    }

    /*
     * Sjekker at kanppen submit er disabled med mindre alle input-felt er fyllt inn.
     * Sjekker at Comboboxen fylles med verdiene fra ratings og genres. 
     */
    @Test 
    public void testInitialize(){
        assertTrue(controller.submit.isDisabled());
        assertEquals(controller.ratings, controller.rating.getItems());
        assertEquals(controller.genres, controller.genre.getItems());
        assertFalse(controller.showMovies.isDisabled());
    }

    /**
     * Sjekker at alle input-felt settes til null etter en submit.
     */
    @Test
    public void testSubmitted(){
        controller.submitted();
        assertNull(controller.genre.getValue());
        assertNull(controller.rating.getValue());
        assertNull(controller.title.getText());
    }

    /**
     * FxRobot fyller inn input. Sjekker at disse stemmer med det controlleren mottar, 
     * og at filmen legges til i container-klassen etter submit. 
     * Lager en ny container og fyller den med innhold fra fil. Sjekker at dette innholdet
     * er likt innholdet i controlleren sin myMovies. 
     */
    @Test
    public void handleSubmitTest() {
        inputExampleMovie(false);
        assertEquals(controller.title.getText(), ("The boy"));
        assertEquals(controller.genre.getValue(), "Horror");
        assertEquals(controller.rating.getValue(), "2");
        clickOn(controller.submit);

        assertEquals(1, controller.user.getMyMovies().size());
        Iterator<Film> it = controller.user.getMyMovies().iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next().getName(), "The boy");
        assertEquals(1, controller.access.getUser(controller.user.getUserName()).getMyMovies().size());
        
        assertEquals(controller.genre.getValue(), null);
        assertEquals(controller.rating.getValue(), null);
        assertEquals(controller.title.getText(), null);
    
/*
        Film film = controller.getMyMovies().iterator().next();
        assertEquals("The boy", film.getName());
        assertEquals("Horror", film.getGenre());
        assertEquals(2, film.getRating());
        assertFalse(controller.getMyMovies().iterator().hasNext());
        */
    }

    /**
     * Sjekker at teksten som vises for brukeren settes til 
     * film.toString+\n for hver film i mymovies,
     * og at den settes til tom streng etter vinduet lukkes
     */
    
    @Test
    public void testGenerateList(){
        inputExampleMovie(true);
        clickOn(controller.showMovies);
        Film film = new Film("The boy", "Horror", 2);
        //TEST HER: AT DET GÅR AN Å FJERNE FILM 
        //VURDERE Å GJØRE MOVIELIST I METODEN TIL ET ATTRIBUTT ISTEDE
        //SLIK AT DU KAN SAMMENLIGNE INNHOLDET MED USER.GETMOVIES. 

        //assertEquals(film.toString()+"\n", controller.heltekst);
        //clickOn("#ok");
        //assertEquals("", controller.heltekst);
    }

    @Test
    public void testGetMyMovies(){
        inputExampleMovie(true);
        Film film = new Film("The boy", "Horror", 2);
        Collection<Film> films = new ArrayList<>();
        films.add(film);
        assertEquals(films.iterator().next().toString(), film.toString());
    }

    /** 
    * Hjelpemetode. 
    * FxRobot fyller inn alle innput-felt.
    * @param andSubmit avgjør om FxRobot skal trykke submit eller ikke. 
    */
    private void inputExampleMovie(boolean andSubmit) {
        clickOn("#title").write("The boy");
        clickOn("#genre").clickOn("Horror");
        clickOn("#rating").clickOn("2"); 
        if (andSubmit) {
            clickOn("#submit");
        }
    }
    

}