package test.ui.unit;

import org.junit.jupiter.api.*;
import page.NewUserFormPage;
import util.Constants;
import util.DriverUtil;
import util.PropertyReader;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewUserFormPageUiElementTests {

    private NewUserFormPage newUserFormPage;

    @BeforeAll
    public void openNewUserPage(){
        DriverUtil.openBrowser();
        newUserFormPage = NewUserFormPage.openNewUserFormPage();
    }

    @AfterAll
    public void closeBrowser(){
        close();
    }

    @DisplayName("Title should be: New User")
    @Test
    public void checkTitle(){
        assertThat(title()).isEqualTo("New User");
    }

    @DisplayName("Heading/Title shoudd be: New User")
    @Test
    public void checkHeader(){
        newUserFormPage.newUserHeading.shouldHave(exactText("New User"));
    }

    @DisplayName("Name label")
    @Test
    public void nameLabel(){
        newUserFormPage.nameLabel.shouldHave(exactText("Name:"));
    }

    @DisplayName("Email label")
    @Test
    public void emailLabel(){
        newUserFormPage.emailLabel.shouldHave(exactText("Email:"));
    }

    @DisplayName("Password label")
    @Test
    public void passwordLabel(){
        newUserFormPage.passwordLabel.shouldHave(exactText("Password:"));
    }

    @DisplayName("Confirmation password label")
    @Test
    public void confirmationPasswordLabel(){
        newUserFormPage.confirmationPasswordLabel.shouldHave(exactText("Confirmation password:"));
    }

    @DisplayName("Name input box placeholder should be: name")
    @Test
    public void nameInputPlaceHolder(){
        newUserFormPage.nameInput.shouldHave(attribute("placeholder", "name"));
    }

    @DisplayName("Email input box placeholder should be: your@email.com")
    @Test
    public void emailInputPlaceHolder(){
        newUserFormPage.emailInput.shouldHave(attribute("placeholder", "your@email.com"));
    }

    @DisplayName("Password input box placeholder should be: min 6 characters")
    @Test
    public void passwordInputPlaceHolder(){
        newUserFormPage.passwordInput.shouldHave(attribute("placeholder", "min 6 characters"));
    }

    @DisplayName("Confirmation Password input box placeholder should be: min 6 characters")
    @Test
    public void confirmationPasswordInputPlaceHolder(){
        newUserFormPage.confirmPasswordInput.shouldHave(attribute("placeholder", "min 6 characters"));
    }
}