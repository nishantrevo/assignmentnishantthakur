package util;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class DriverUtil {

    public static void openBrowser(){
        openBrowser(PropertyReader.getProperty(Constants.BASEURL_PROPERTY));
    }

    public static void openBrowser(String url){
        Configuration.browser = PropertyReader.getProperty(Constants.BROWSER_NAME_PROPERTY);
        Configuration.baseUrl = url;
        Selenide.open("");
    }
}
