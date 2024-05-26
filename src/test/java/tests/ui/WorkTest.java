package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.AdminPanelPage;
import pageObjects.IndexPage;
import utils.ProxyUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.ApiUtils.*;
import static utils.properties.ConfProperties.getCommonProperty;

@Epic("Тесты работоспособности брони.")
public class WorkTest extends BaseTest {
    private IndexPage indexPage;
    private AdminPanelPage adminPanelPage;
    private BrowserMobProxy proxy;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        indexPage = new IndexPage(driver);
        adminPanelPage = new AdminPanelPage(driver);
        ProxyUtils proxyUtils = new ProxyUtils();
        proxy = proxyUtils.startProxy();
        Configuration.browserCapabilities = proxyUtils.getChromeOptionsWithProxy();

    }

    @Feature("Бронь комнаты")
    @Description("Тест бронирует комнату на определенную дату и проверяет текст в всплывающем пупапе на соответствие ожидаемому")
    @Severity(value = SeverityLevel.CRITICAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка возможности успешно забронировать комнату")
    @AllureId("UI-booking")
    public void bookingroom() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Selenide.open(getCommonProperty("url"));
        proxy.newHar("booking/");

        indexPage.clickBookRoomButton()
                .draganddropcalandar()
                .fillFirstnameInput()
                .fillLastnameInput()
                .fillEmailInput()
                .fillPhoneInput()
                .clickBookButton()
                .popup.shouldBe(Condition.visible);

        assertEquals("Booking Successful!", indexPage.getSuccessMessage());
        assertEquals("Congratulations! Your booking has been confirmed for:", indexPage.getInfoMessage());
        assertEquals(201, selenideUtils.getBookingResponceCode(proxy));

    }
    @Feature("Форма обратной связи")
    @Description("Тест отправляет сообщение с помощью формы на сайте, проверяет выдачу уведомлений и статус код отправки формы")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Тест работоспособности формы")
    @AllureId("UI-form")
    public void sendform() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Selenide.open(getCommonProperty("url"));
        proxy.newHar("message/");

        indexPage.fillFormName()
                .fillFormEmail()
                .fillFormPhone()
                .fillFormSubject()
                .fillFromMessage()
                .clickSubmitFormButton();

        assertEquals("Thanks for getting in touch " + getCommonProperty("testFullName") + "!", indexPage.getFormHeadMessage());
        assertEquals("We'll get back to you about", indexPage.getFormFirstRowMessage());
        assertEquals(getCommonProperty("testSubject"), indexPage.getFormSecondRowMessage());
        assertEquals("as soon as possible.", indexPage.getFormThirdRowMessage());
        assertEquals(201, selenideUtils.getFormResponceCode(proxy));
    }


    @AfterEach
    @Step("Очистка")
    public void after() {
        deleteAllBooking(getBookingid(token), token);
    }

}
