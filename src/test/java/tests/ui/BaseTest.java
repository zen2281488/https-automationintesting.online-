package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.SelenideUtils;



public class BaseTest {
    protected Selenide driver;
    protected String token;
    protected SelenideUtils selenideUtils;
    @AfterEach
    @Step("Очиска данных")
    public void baseAfter() {

    }

    @BeforeEach
    @Step("Инициализация Драйвера")
    public void baseBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        Configuration.browser = "chrome";
        selenideUtils = new SelenideUtils(driver);
        token = selenideUtils.auth().getCookie();
        Selenide.closeWebDriver();
    }
}
