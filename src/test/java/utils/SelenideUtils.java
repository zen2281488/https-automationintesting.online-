package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import pageObjects.AdminPanelPage;

import static com.codeborne.selenide.Selenide.$;
import static utils.ApiUtils.getIndexByName;
import static utils.properties.ConfProperties.getCommonProperty;
import static utils.properties.ConfProperties.getPrivateProperty;

public class SelenideUtils {

    protected Selenide browser;
    public SelenideUtils(Selenide browser) {
        this.browser = browser;
    }

    public String getCookie() {
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("token").getValue();
    }

    public SelenideUtils auth() {
        Selenide.open("http://localhost/#/admin");
        $("#username").sendKeys(getPrivateProperty("login"));
        $("#password").sendKeys(getPrivateProperty("password"));
        $("#doLogin").click();

        return this;
    }

    @Step("Клик по тестовому сообщению")
    public SelenideUtils clickTestMessage(String token) {
        $("#message"+getIndexByName(token,getCommonProperty("testFullName"))).click();
        return this;
    }
    public String getNameMessagePreview(String token) {
        return $("#message"+getIndexByName(token,getCommonProperty("testFullName"))+".col-sm-2  p").getText();
    }
    public String getSubjectMessagePreview(String token) {
        return $("#message"+getIndexByName(token,getCommonProperty("testFullName"))+".col-sm-9  p").getText();
    }
}
