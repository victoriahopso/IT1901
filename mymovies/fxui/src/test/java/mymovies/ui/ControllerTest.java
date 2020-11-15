package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.ListIterator;

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
        User user = new User("brukernavn", "password");
        UserAccess access = new MockUserAccess();
        controller.setUp(user, access);
    }

    /*
     * Fjerner "gamle tilstander" etter hver test, slik at ingenting henger ignen
     * til neste test.
     * 
     * @throws Exception
     */
    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }

    @Test
    public void testContructor() {
        assertNotNull(this.controller);
        assertNotNull(this.controller.user);
    }

    /*
     * Sjekker at kanppen submit er disabled med mindre alle input-felt er fyllt
     * inn. Sjekker at Comboboxen fylles med verdiene fra ratings og genres.
     */
    @Test
    public void testInitialize() {
        assertTrue(controller.submit.isDisabled());
        assertEquals(controller.ratings, controller.rating.getItems());
        assertEquals(controller.genres, controller.genre.getItems());
        assertFalse(controller.showMovies.isDisabled());
    }

    /**
     * Sjekker at alle input-felt settes til null etter en submit.
     */
    @Test
    public void testSubmitted() {
        controller.submitted();
        assertNull(controller.genre.getValue());
        assertNull(controller.rating.getValue());
        assertNull(controller.title.getText());
    }

    /**
     * FxRobot fyller inn input. Sjekker at disse stemmer med det controlleren
     * mottar, og at filmen legges til i brukerens myMovies etter submit. Sjekker
     * deretter at den samme infoen ble lagt til i access.
     */
    @Test
    public void testHandleSubmit() {
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
        Iterator<Film> it2 = controller.access.getUser(controller.user.getUserName()).getMyMovies().iterator();
        assertTrue(it2.hasNext());
        assertEquals("The boy", it2.next().getName());

        assertEquals(controller.genre.getValue(), null);
        assertEquals(controller.rating.getValue(), null);
        assertEquals(controller.title.getText(), null);
    }

    @Test
    public void testGenerateList() {
        Film film1 = new Film("Cfilm", "Bgenre", 1);
        Film film2 = new Film("Bfilm", "Agenre", 2);
        Film film3 = new Film("Afilm", "Cgenre", 3);
        controller.user.addMovie(film1);
        controller.user.addMovie(film2);
        controller.user.addMovie(film3);
        clickOn(controller.showMovies);

        // tester at alle brukerens filmer legges til i listen
        // som vises i tableView
        ListIterator<Film> it = controller.moviesList.listIterator();
        assertTrue(it.hasNext());
        assertEquals(film1, it.next());
        assertTrue(it.hasNext());
        assertEquals(film2, it.next());
        assertTrue(it.hasNext());
        assertEquals(film3, it.next());
        assertFalse(it.hasNext());

        // tester sortering på tittel
        ListIterator<Film> it2 = controller.moviesList.listIterator();
        clickOn("#Title");
        assertTrue(it2.hasNext());
        assertEquals(film3, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(film2, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(film1, it2.next());
        assertFalse(it2.hasNext());

        // tester sortering på sjanger
        ListIterator<Film> it3 = controller.moviesList.listIterator();
        clickOn("#Genre");
        assertTrue(it3.hasNext());
        assertEquals(film2, it3.next());
        assertTrue(it3.hasNext());
        assertEquals(film1, it3.next());
        assertTrue(it3.hasNext());
        assertEquals(film3, it3.next());
        assertFalse(it3.hasNext());

        // tester sortering på rating
        ListIterator<Film> it4 = controller.moviesList.listIterator();
        clickOn("#Rating");
        assertTrue(it4.hasNext());
        assertEquals(film1, it4.next());
        assertTrue(it4.hasNext());
        assertEquals(film2, it4.next());
        assertTrue(it4.hasNext());
        assertEquals(film3, it4.next());
        assertFalse(it4.hasNext());

        // tester å fjerne en film 
        controller.table.getSelectionModel().select(film1);
        clickOn("#removeMovie");
        assertEquals(2, controller.moviesList.size());
        ListIterator<Film> it5 = controller.moviesList.listIterator();
        assertTrue(it5.hasNext());
        assertEquals(film2, it5.next());
        assertTrue(it5.hasNext());
        assertEquals(film3, it5.next());
        assertFalse(it5.hasNext());
    }

    /**
     * Hjelpemetode. FxRobot fyller inn alle innput-felt.
     * 
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