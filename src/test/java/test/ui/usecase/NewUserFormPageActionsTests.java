package test.ui.usecase;

import dto.UserInfo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.AllUserPage;
import page.NewUserFormPage;
import util.DriverUtil;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewUserFormPageActionsTests {

    private static final String REQUIRED_ERROR_TEXT = "Required";
    private static final String INVALID_EMAIL_ERROR_TEXT = "Invalid email address";
    private static final String PASSWORD_MINIMUM_ERROR_TEXT = "Minimum size is 6";
    private static final String PASSWORD_NOT_SAME_ERROR_TEXT = "passwords are not the same";
    private static final String MUST_BE_UNIQUE_ERROR_TEXT = "Must be unique";


    private NewUserFormPage newUserFormPage;

    @BeforeAll
    public void openBrowser(){
        DriverUtil.openBrowser();
    }

    @AfterAll
    public void closeBrowser(){
        close();
    }

    @BeforeEach
    public void openNewUserPage(){
        newUserFormPage = NewUserFormPage.openNewUserFormPage();
    }


    @Test
    public void validAndUniqueDetailsAreAccepted(){
        String uniqueId = System.currentTimeMillis()+"";
        String name = "abc"+uniqueId;
        String email = "abc"+uniqueId+"@ymail.com";
        String password = "123456";

        AllUserPage allUserPage = newUserFormPage.submit(name, email, password);

        allUserPage.heading
                .shouldBe(visible)
                .shouldHave(text("All User"));

        List<UserInfo> users = allUserPage.getUsers();
        UserInfo userInfo = users.parallelStream().filter(u -> u.getName().equals(name)).findFirst().get();
        assertThat(userInfo)
                .as("Created User is present in All user list")
                .isNotNull();
        assertThat(userInfo.getEmail())
                .as("Created User Email is correct")
                .isEqualTo(email);
        assertThat(userInfo.getPassword())
                .as("Created User Password is correct")
                .isEqualTo(password);
    }

    @Test
    public void nameMustBeUnique(){
        String uniqueId = System.currentTimeMillis()+"";
        String name = "abc"+uniqueId;
        String email = "abc"+uniqueId+"@ymail.com";
        String password = "123456";

        AllUserPage allUserPage = newUserFormPage.submit(name, email, password);

        allUserPage.heading
                .shouldBe(visible)
                .shouldHave(text("All User"));
        allUserPage.newUser();

        email = "xyz"+email; //make email unique
        newUserFormPage.submit(name, email, password);
        newUserFormPage.nameErrorMsg
                .shouldBe(visible)
                .shouldHave(text(MUST_BE_UNIQUE_ERROR_TEXT));
    }

    @Test
    public void emailMustBeUnique(){
        String uniqueId = System.currentTimeMillis()+"";
        String name = "abc"+uniqueId;
        String email = "abc"+uniqueId+"@ymail.com";
        String password = "123456";

        AllUserPage allUserPage = newUserFormPage.submit(name, email, password);

        allUserPage.heading
                .shouldBe(visible)
                .shouldHave(text("All User"));
        allUserPage.newUser();

        name = "xyz"+name; //make name unique
        newUserFormPage.submit(name, email, password);
        newUserFormPage.emailErrorMsg
                .shouldBe(visible)
                .shouldHave(text(MUST_BE_UNIQUE_ERROR_TEXT));
    }

    @Test
    public void allUserButtonTakesUserToAllUserPage(){
        AllUserPage allUserPage = newUserFormPage.allUsers();
        allUserPage.heading
                .shouldBe(visible)
                .shouldHave(text("All User"));
    }

    @Test
    public void blankFormIsNotAccepted(){
        newUserFormPage.submit();
        newUserFormPage.nameErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
        newUserFormPage.emailErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
        newUserFormPage.emailErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
    }

    @ParameterizedTest(name = "invalidEmailId")
    @ValueSource(strings = {"xyz","x@yz","xy.z"})
    public void incorrectEmailFormatIsNotAccepted(String invalidEmailId){
        newUserFormPage.submit("abc", invalidEmailId, "123456");
        newUserFormPage.emailErrorMsg
                .shouldBe(visible)
                .shouldHave(text(INVALID_EMAIL_ERROR_TEXT));
    }

    @ParameterizedTest(name =  "shortPassowrds")
    @ValueSource(strings = {"1","12345"})
    public void passwordIsMinimumSixChars(String shortPassword){
        newUserFormPage.submit("abc", "x@y.z", shortPassword);
        newUserFormPage.passwordErrorMsg
                .shouldBe(visible)
                .shouldHave(text(PASSWORD_MINIMUM_ERROR_TEXT));
    }

    @Test
    public void passwordAndConfirmationPasswordShouldBeSame(){
        newUserFormPage.submit("abc", "x@y.z", "123456","123457");
        newUserFormPage.confirmationErrorMsg
                .shouldBe(visible)
                .shouldHave(text(PASSWORD_NOT_SAME_ERROR_TEXT));
    }

    @Test
    public void nameCannotBeEmpty(){
        newUserFormPage.submit("", "x@y.z", "123456");
        newUserFormPage.nameErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
    }

    @Test
    public void emailCannotBeEmpty(){
        newUserFormPage.submit("abc", "", "123456");
        newUserFormPage.emailErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
    }

    @Test
    public void passwordCannotBeEmpty(){
        newUserFormPage.submit("abc", "x@y.z", "","123456");
        newUserFormPage.passwordErrorMsg
                .shouldBe(visible)
                .shouldHave(text(REQUIRED_ERROR_TEXT));
    }

    @Test
    public void confirmationPasswordCannotBeEmpty(){
        newUserFormPage.submit("abc", "x@y.z", "123456","");
        newUserFormPage.confirmationErrorMsg
                .shouldBe(visible)
                .shouldHave(text(PASSWORD_NOT_SAME_ERROR_TEXT));
    }

}
