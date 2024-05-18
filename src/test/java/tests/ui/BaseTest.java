package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static utils.SelenideUtils.getCookie;


public class BaseTest {
    protected Selenide driver;
    protected String token;
    @AfterEach
    @Step("Очиска данных")
    public void baseAfter() {

    }

    @BeforeEach
    @Step("Инициализация Драйвера")
    public void baseBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        Configuration.browser = "chrome";
        token = getCookie();
        Selenide.closeWebDriver();
    }
}
