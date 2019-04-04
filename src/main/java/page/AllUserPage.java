package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import dto.UserInfo;
import org.openqa.selenium.By;
import util.DriverUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class AllUserPage {

    public static final String RELATIVE_URL = "/users/all";

    public final SelenideElement heading = $(By.cssSelector(".container .page-header > h1"));
    public final ElementsCollection columnNames = $$(By.cssSelector("table#users > thead > tr > th"));
    public final ElementsCollection names = $$(By.cssSelector("table#users > tbody > tr > td:nth-child(1)"));
    public final ElementsCollection emails = $$(By.cssSelector("table#users > tbody > tr > td:nth-child(2)"));
    public final ElementsCollection passwords = $$(By.cssSelector("table#users > tbody > tr > td:nth-child(3)"));
    public final SelenideElement newUserButton = $(By.xpath("//a[@href='/user/new']"));

    public static AllUserPage openAllUserPage(){
        open(RELATIVE_URL);
        return new AllUserPage();
    }

    public static boolean isOpen(){
        return WebDriverRunner.getWebDriver().getCurrentUrl().endsWith(RELATIVE_URL);
    }

    public List<UserInfo> getUsers(){
        List<UserInfo> userList = new ArrayList<>();
        for(int i=0; i<names.size(); i++){
            UserInfo user = new UserInfo();
            user.setName(names.get(i).getText());
            user.setEmail(emails.get(i).getText());
            user.setPassword(passwords.get(i).getText());
            userList.add(user);
        }
        return userList;
    }
    
    public NewUserFormPage newUser(){
        newUserButton.click();
        return page(NewUserFormPage.class);
    }
    
}
