package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppTest extends ApplicationTest {

    private MyMoveisController controller; 
    private App app = new App();

    @Override
    public void start(final Stage stage) throws Exception {
        try{
            //å kjøre appen
        }
        catch(Exception e){
            //fail
        }
    }


    //@Test
    //public void shouldAnswerWithTrue() {
     //   assertNotNull(this.controller);
     //   assertNotNull(this.controller.myMovies);
     //   assertNotNull(this.controller.persistence);
     //}

   
}
