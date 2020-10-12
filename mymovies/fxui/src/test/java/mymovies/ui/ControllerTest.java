package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

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
    @Test
    public void testContructor() {
        assertNotNull(this.controller);
        assertNotNull(this.controller.myMovies);
        assertNotNull(this.controller.persistence);
    }

    /**
     * Fjerner "gamle tilstander" etter hver test, slik at ingenting henger
     * ignen til neste test.
     * @throws Exception
     */
    @AfterEach
    public void tearDown () throws Exception {
        MyMovies movies = new MyMovies();
        FileOutputStream fileStream = new FileOutputStream("./mymovies.json");
        OutputStreamWriter writer = new OutputStreamWriter(fileStream,"UTF-8");
        controller.persistence.write(movies, writer);
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
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
        clickOn("#submit");
        assertEquals("The boy", controller.getMyMovies().iterator().next().getName());
        assertEquals("Horror", controller.getMyMovies().iterator().next().getGenre());
        assertEquals(2, controller.getMyMovies().iterator().next().getRating());
        assertEquals(controller.genre.getValue(), null);
        assertEquals(controller.rating.getValue(), null);
        assertEquals(controller.title.getText(), null);

        MyMovies myMovies1 = new MyMovies();
        try {
            InputStream inputStream = new FileInputStream("./mymovies.json"); 				
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            myMovies1 = controller.persistence.read(reader);
            }
        catch (IOException e){
                fail(e.getMessage());

        }
        assertEquals(controller.getMyMovies().iterator().next().getName(), myMovies1.getFilmer().iterator().next().getName());
        assertEquals(controller.getMyMovies().iterator().next().getGenre(), myMovies1.getFilmer().iterator().next().getGenre());
        assertEquals(controller.getMyMovies().iterator().next().getRating(), myMovies1.getFilmer().iterator().next().getRating());
    }   

    /**
     * Sjekker at det leses korrekt fra fil til myMovies, og at innholdet som vises for 
     * bruker er skrevet på riktig måte. 
     */
    @Test
    public void testResumeSession(){
        inputExampleMovie(true);
        controller.resumeSession();
        assertEquals("The boy", controller.getMyMovies().iterator().next().getName());
        assertEquals("Horror", controller.getMyMovies().iterator().next().getGenre());
        assertEquals(2, controller.getMyMovies().iterator().next().getRating()); 
        assertEquals(("Film: The boy, Genre: Horror, Rating: 2"),controller.getMyMovies().iterator().next().toString());
        assertEquals("Film: The boy, Genre: Horror, Rating: 2\n", controller.heltekst);
    }

    /**
     * Sjekker at kanppen submit er disabled med mindre alle input-felt er fyllt inn.
     * Sjekker at Comboboxen fylles med verdiene fra ratings og genres. 
     */
    @Test 
    public void testInitialize(){
        assertTrue(controller.submit.isDisabled());
        assertEquals(controller.ratings, controller.rating.getItems());
        assertEquals(controller.genres, controller.genre.getItems());
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
     * Sjekker at teksten som vises for brukeren settes til 
     * film.toString+\n for hver film i mymovies,
     * og at den settes til tom streng etter vinduet lukkes
     */
    @Test
    public void testGenerateList(){
        inputExampleMovie(true);
        clickOn("#showMovies");
        Film film = new Film("The boy", "Horror", 2);
        assertEquals(film.toString()+"\n", controller.heltekst);
        clickOn("#ok");
        assertEquals("", controller.heltekst);
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