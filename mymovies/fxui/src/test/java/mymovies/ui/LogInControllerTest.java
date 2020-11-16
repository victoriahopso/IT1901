package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        clickOn(controller.siUsername).write("username1");
        clickOn(controller.siPassword).write("password");
        assertFalse(controller.signIn.isDisabled());
    }

    @Test
    public void testSignUpEnabled(){
        clickOn(controller.suUsername).write("username2");
        clickOn(controller.suPassword).write("password");
        clickOn(controller.confPassword).write("password");
        assertFalse(controller.signUp.isDisabled());
    }

    @Test
    public void testSignUpInvalidInput(){
        clickOn(controller.suUsername).write("username3");
        clickOn(controller.suPassword).write("password");
        clickOn(controller.confPassword).write("differentPassord");
        clickOn(controller.signUp);
        assertNull(controller.access.getUser("username3"));
        clickOn("#ok");

        controller.suPassword.setText(null);
        controller.suUsername.setText(null);
        controller.confPassword.setText(null);
        clickOn(controller.suUsername).write("a");
        clickOn(controller.suPassword).write("password");
        clickOn(controller.confPassword).write("password");
        clickOn(controller.signUp);
        assertNull(controller.access.getUser("a"));
        clickOn("#ok");
    }

    @Test
    public void testSignUpValidInput(){
        clickOn(controller.suUsername).write("username4");
        clickOn(controller.suPassword).write("passord123");
        clickOn(controller.confPassword).write("passord123");
        clickOn(controller.signUp);
        assertNotNull(controller.access.getUser("username4"));
    }

    @Test
    public void testSignInValidInput(){
        User user2 = new User("Ola","password");
        controller.access.addUser(user2);
        clickOn(controller.siUsername).write("Ola");
        clickOn(controller.siPassword).write("password");
        clickOn(controller.signIn);
        assertTrue(controller.user.equals(user2));
        assertNotNull(controller.access.getUser("Ola"));
        assertTrue(controller.access.usernameTaken("Ola"));
    }
    
    @Test
    public void testSignInInvalidInput(){
        User user3 = new User("Kari","password");
        controller.access.addUser(user3);
        clickOn(controller.siUsername).write("kari");
        clickOn(controller.siPassword).write("password");
        clickOn(controller.signIn);
        assertNull(controller.access.getUser(controller.siUsername.getText()));
        clickOn("#ok");
    }
    
}