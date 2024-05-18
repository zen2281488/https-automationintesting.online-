package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;
import static utils.properties.ConfProperties.getPrivateProperty;

public class SelenideUtils {


    public static String getCookie() {

        Selenide.open("http://localhost/#/admin");
        $("#username").sendKeys(getPrivateProperty("login"));
        $("#password").sendKeys(getPrivateProperty("password"));
        $("#doLogin").click();
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("token").getValue();
    }


}
