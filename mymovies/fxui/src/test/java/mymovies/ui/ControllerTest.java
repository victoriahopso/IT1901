package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import mymovies.json.MoviesPersistence;

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
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void handleSubmitTest() {
        clickOn("#title").write("The boy");
        clickOn("#genre").clickOn("Horror");
        clickOn("#rating").clickOn("2");
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
            InputStream inputStream = new FileInputStream("mymovies.json"); 				
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            myMovies1 = controller.persistence.read(reader);
            }
        catch (IOException e){
                e.printStackTrace();
        }
        assertEquals(controller.getMyMovies().iterator().next().getName(), myMovies1.getFilmer().iterator().next().getName());
        assertEquals(controller.getMyMovies().iterator().next().getGenre(), myMovies1.getFilmer().iterator().next().getGenre());
        assertEquals(controller.getMyMovies().iterator().next().getRating(), myMovies1.getFilmer().iterator().next().getRating());
    }   

}