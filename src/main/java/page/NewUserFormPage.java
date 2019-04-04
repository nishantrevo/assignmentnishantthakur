package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import util.DriverUtil;

import static com.codeborne.selenide.Selenide.*;

public class NewUserFormPage {

    public static final String RELATIVE_URL = "/user/new";

    public final SelenideElement nameInput = $(By.id("name"));
    public final SelenideElement emailInput = $(By.id("email"));
    public final SelenideElement passwordInput = $(By.id("password"));
    public final SelenideElement confirmPasswordInput = $(By.id("confirmationPassword"));

    public final SelenideElement nameErrorMsg = $(By.id("user.name.error"));
    public final SelenideElement emailErrorMsg = $(By.id("user.email.error"));
    public final SelenideElement passwordErrorMsg = $(By.id("user.password.error"));
    public final SelenideElement confirmationErrorMsg = $(By.id("user.confirmationPassword.error"));

    public final SelenideElement nameLabel = $(By.xpath("//label[@for='name']"));
    public final SelenideElement emailLabel = $(By.xpath("//label[@for='email']"));
    public final SelenideElement passwordLabel = $(By.xpath("//label[@for='password']"));
    public final SelenideElement confirmationPasswordLabel = $(By.xpath("//label[@for='confirmationPassword']"));

    public final SelenideElement newUserHeading = $(By.cssSelector(".container .page-header > h1"));
    public final SelenideElement submitButton = $(By.cssSelector("button[type='submit']"));
    public final SelenideElement allUserButton = $(By.xpath("//a[@href='/users/all']"));


    public static NewUserFormPage openNewUserFormPage(){
        open(RELATIVE_URL);
        return new NewUserFormPage();
    }

    public static boolean isOpen(){
        return WebDriverRunner.getWebDriver().getCurrentUrl().endsWith(RELATIVE_URL);
    }

    public NewUserFormPage setName(String name){
        nameInput.sendKeys(name);
        return this;
    }

    public NewUserFormPage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    public NewUserFormPage setPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public NewUserFormPage setConfirmationPassword(String confirmationPassword){
        confirmPasswordInput.sendKeys(confirmationPassword);
        return this;
    }

    public AllUserPage submit(){
        submitButton.click();
        return page(AllUserPage.class);
    }

    public AllUserPage submit(String name, String email, String password, String confirmationPassword){
        setName(name);
        setEmail(email);
        setPassword(password);
        setConfirmationPassword(confirmationPassword);
        return submit();
    }

    public AllUserPage submit(String name, String email, String password){
        return submit(name, email, password, password);
    }

    public AllUserPage allUsers(){
        allUserButton.click();
        return page(AllUserPage.class);
    }
}
