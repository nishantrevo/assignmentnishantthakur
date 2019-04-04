package test.ui.unit;

import org.junit.jupiter.api.*;
import page.AllUserPage;
import util.Constants;
import util.DriverUtil;
import util.PropertyReader;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AllUserPageUiElementsTests {

    AllUserPage allUserPage;

    @BeforeAll
    public void openAllUserPage() {
        DriverUtil.openBrowser();
        allUserPage = AllUserPage.openAllUserPage();
    }

    @AfterAll
    public void closeBrowser(){
        close();
    }

    @Test
    public void checkTitle(){
        assertThat(title()).isEqualTo("All Users");
    }

    @Test
    public void heading(){
        allUserPage.heading.shouldHave(text("All User"));
    }

    @Test
    public void tableColumnNames(){
        List<String> columnNames = allUserPage.columnNames.stream().map(col -> col.getText()).collect(Collectors.toList());
        assertThat(columnNames).containsOnlyOnce("Name","Email","Password");
    }

    @Test
    public void newUserButtonIsPresent(){
        allUserPage.newUserButton.shouldBe(visible);
    }

}
