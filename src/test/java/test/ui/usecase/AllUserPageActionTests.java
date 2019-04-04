package test.ui.usecase;


import dto.UserInfo;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import page.AllUserPage;
import page.NewUserFormPage;
import util.DriverUtil;
import util.Constants;
import util.PropertyReader;
import util.RestUtil;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AllUserPageActionTests {

    AllUserPage allUserPage;

    @BeforeAll
    public void openBrowser(){
        DriverUtil.openBrowser();
    }

    @AfterAll
    public void closeBrowser(){
        close();
    }

    @BeforeEach
    public void openAllUserPage(){
        allUserPage = AllUserPage.openAllUserPage();
    }

    @Test
    public void allUserAreDisplayed(){
        String url = PropertyReader.getProperty(Constants.BASEURL_PROPERTY) +  PropertyReader.getProperty(Constants.USER_ALL_JSON_PROPERTY);
        ValidatableResponse response = RestUtil.sendGet(url);
        response.statusCode(200);
        String jsonBody = response.extract().body().asString();

        List<UserInfo> expectedUsers = RestUtil.parseJsonToUserInfoList(jsonBody);
        List<String> expectedNames = expectedUsers.stream().map(u->u.getName()).collect(Collectors.toList());
        List<String> expectedEmails = expectedUsers.stream().map(u->u.getName()).collect(Collectors.toList());
        List<String> expectedPasswords = expectedUsers.stream().map(u->u.getName()).collect(Collectors.toList());

        List<UserInfo> actualUsers = allUserPage.getUsers();
        List<String> actualNames = actualUsers.stream().map(u->u.getName()).collect(Collectors.toList());
        List<String> actualEmails = actualUsers.stream().map(u->u.getName()).collect(Collectors.toList());
        List<String> actualPassword = actualUsers.stream().map(u->u.getName()).collect(Collectors.toList());

        assertThat(actualNames)
                .as("Names")
                .containsAll(expectedNames);

        assertThat(actualEmails)
                .as("Emails")
                .containsAll(expectedEmails);

        assertThat(actualPassword)
                .as("Passowrds")
                .containsAll(expectedPasswords);

    }

    @Test
    public void newUserButtonClickOpensNewUserFormPage(){
        allUserPage.newUser();
        assertThat(NewUserFormPage.isOpen())
                .as("New User page is not open");

    }
}
