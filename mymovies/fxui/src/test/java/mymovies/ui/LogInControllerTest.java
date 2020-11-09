package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import mymovies.core.User;


public class LogInControllerTest extends ApplicationTest {

    LogInController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        final Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
        controller.setAccess(new MockUserAccess());
    }

    @AfterEach
    public void tearDown() throws Exception{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
        controller.user = null;
        controller.siUsername.setText(null);
        controller.suUsername.setText(null);
        controller.siPassword.setText(null);
        controller.suPassword.setText(null);
        controller.confPassword.setText(null);
    }

    @Test 
    public void testInitialize(){
        assertTrue(controller.signIn.isDisabled());
        assertTrue(controller.signUp.isDisabled());
        assertTrue(controller.user==null);
        assertFalse(controller.access==null);
        assertTrue(controller.access instanceof MockUserAccess);
    }

    @Test
    public void testSignInEnabled(){
        clickOn(controller.siUsername).write("brukernavn1");
        clickOn(controller.siPassword).write("passord");
        assertFalse(controller.signIn.isDisabled());
    }

    @Test
    public void testSignUpEnabled(){
        clickOn(controller.suUsername).write("brukernavn2");
        clickOn(controller.suPassword).write("passord");
        clickOn(controller.confPassword).write("passord");
        assertFalse(controller.signUp.isDisabled());
    }

    @Test
    public void testSignUpInvalidInput(){
        clickOn(controller.suUsername).write("brukernavn3");
        clickOn(controller.suPassword).write("passord");
        clickOn(controller.confPassword).write("annetPassord");
        clickOn(controller.signUp);
        assertNull(controller.access.getUser("brukernavn3"));
    }

    @Test
    public void testSignUpValidInput(){
        clickOn(controller.suUsername).write("brukernavn4");
        clickOn(controller.suPassword).write("passord123");
        clickOn(controller.confPassword).write("passord123");
        clickOn(controller.signUp);
        assertNotNull(controller.access.getUser("brukernavn4"));
    }

    @Test
    public void testSignInValidInput(){
        User user2 = new User("Ola","passord");
        controller.access.addUser(user2);
        clickOn(controller.siUsername).write("Ola");
        clickOn(controller.siPassword).write("passord");
        clickOn(controller.signIn);
        assertTrue(controller.user.equals(user2));
        assertNotNull(controller.access.getUser("Ola"));
        assertTrue(controller.access.usernameTaken("Ola"));
    }
    @Test
    public void testSignInInvanlidInput(){
        User user3 = new User("Kari","passord");
        controller.access.addUser(user3);
        clickOn(controller.siUsername).write("kari");
        clickOn(controller.siPassword).write("passord");
        clickOn(controller.signIn);
        assertNull(controller.access.getUser(controller.siUsername.getText()));

    }
    
}