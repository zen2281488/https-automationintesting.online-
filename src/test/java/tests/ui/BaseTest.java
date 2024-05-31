package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import utils.SelenideUtils;


public class BaseTest {
    protected static Selenide driver;
    protected static String token;
    protected static SelenideUtils selenideUtils;

    @AfterEach
    @Step("Очиска данных")
    public void baseAfter() {
        Selenide.closeWebDriver();
    }

    @BeforeAll
    @Step("Инициализация Драйвера")
    public static void baseBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        Configuration.browser = "chrome";
        selenideUtils = new SelenideUtils(driver);
        token = selenideUtils.auth().getCookie();
        Selenide.closeWebDriver();
        System.setProperty("selenide.holdBrowserOpen", "true");
    }
}
